package controllers

import authentication.AuthenticationAction
import javax.inject.Inject
import models.{DiscussionEntry, MovieInfo, paymentForm}
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, ControllerComponents, _}

import scala.collection.mutable.ListBuffer
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.Source


class DiscussionController @Inject()(cc: ControllerComponents, val mongoService: MongoService, val authAction: AuthenticationAction) extends AbstractController(cc) with I18nSupport {

  def discussion: Action[AnyContent] = authAction { implicit request: Request[AnyContent] =>
    Ok(views.html.discussion(currentMovieList, discussionList))
  }

  def parseDetails: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val bufferedSource = Source.fromFile("public/profanityList.txt")
    val badWords = new ListBuffer[String]()
    for (line <- bufferedSource.getLines) badWords += line
    bufferedSource.close()

    val body = request.body.asFormUrlEncoded
    val review = body.get("review").head.split(" ")
    var safeReview = new ListBuffer[String]()
    review.foreach(word => if (badWords.contains(word.toLowerCase)) safeReview += (word.replaceAll("[a-zA-Z]","*")) else safeReview += word)

    mongoService.createDiscussion(DiscussionEntry(body.get("film").head, body.get("rating").head.toInt, safeReview.toList.mkString(" "))).map(
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
