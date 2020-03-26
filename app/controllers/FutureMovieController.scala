package controllers

import authentication.AuthenticationAction
import javax.inject.{Inject, _}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class FutureMovieController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction, val mongoService: MongoService) extends AbstractController(cc) {

  def movie(title: String): Action[AnyContent] = authAction.async { implicit request: Request[AnyContent] =>
    mongoService.findByFutureTitle(title).map { listOfMovie =>
      Ok(views.html.releaseMovie(listOfMovie.head))
    }
  }
}
