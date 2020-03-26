package controllers

import javax.inject.{Inject, _}
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class MovieController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def movie(title: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    mongoService.findByTitle(title).map{listOfMovie =>
      Ok(views.html.movie(listOfMovie.head))
    }
  }
}
