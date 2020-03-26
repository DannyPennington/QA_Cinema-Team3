package controllers

import authentication.AuthenticationAction
import javax.inject._
import play.api.mvc._

@Singleton
class ScreensController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction, val mongoService: MongoService) extends AbstractController(cc) {

  def screens: Action[AnyContent] = authAction { implicit request: Request[AnyContent] =>
    Ok(views.html.screens())
  }
}
