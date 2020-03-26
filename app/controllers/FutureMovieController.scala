package controllers

import javax.inject.{Inject, _}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class FutureMovieController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def movie(title: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    mongoService.findNewReleaseByTitle(title).map{ listOfMovie =>
      Ok(views.html.releaseMovie(listOfMovie.head))
    }
  }
}
