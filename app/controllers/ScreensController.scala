package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class ScreensController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def screens: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.screens())
  }
}
