package controllers

import javax.inject.Inject
import models.{DiscussionEntry, MovieInfo, paymentForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents, _}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global


class DiscussionController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def discussion: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    mongoService.findDiscussions().map { listOfDiscussions =>
      Ok(views.html.discussion(currentMovieList, listOfDiscussions))
    }
  }

  def parseDetails: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val body = request.body.asFormUrlEncoded
    val title = body.get("film").head
    val rating = body.get("rating").head
    val review = body.get("review").head
    mongoService.createDiscussion(DiscussionEntry(title, rating.toInt, review))
    Redirect(routes.DiscussionController.discussion())
  }

  def currentMovieList: List[MovieInfo] = {
    Await.result(mongoService.findCurrentMovies(), Duration.Inf)
  }
}
