package controllers

import javax.inject.Inject
import models.{DiscussionEntry, MovieInfo, paymentForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents, _}

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global


class DiscussionController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def discussion: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.discussion(currentMovieList, discussionList))
  }

  def parseDetails: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val body = request.body.asFormUrlEncoded
    mongoService.createDiscussion(DiscussionEntry(body.get("film").head, body.get("rating").head.toInt, body.get("review").head)).map(
      _ => Redirect(routes.DiscussionController.discussion())
    )
  }

  def currentMovieList: List[MovieInfo] = {
    Await.result(mongoService.findCurrentMovies(), Duration.Inf)
  }

  def discussionList: List[DiscussionEntry] = {
    Await.result(mongoService.findDiscussions(), Duration.Inf)
  }
}
