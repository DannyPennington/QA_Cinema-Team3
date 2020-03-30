package controllers

import authentication.AuthenticationAction
import javax.inject._
import play.api.mvc._

class OpeningController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction) extends AbstractController(cc) {

  def opening: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.opening())
  }

}
