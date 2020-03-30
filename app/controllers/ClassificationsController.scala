package controllers

import authentication.AuthenticationAction
import javax.inject._
import play.api.mvc._

@Singleton
class ClassificationsController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction) extends AbstractController(cc) {

  def classifications: Action[AnyContent] = Action { implicit request:Request[AnyContent] =>
    Ok(views.html.classifications())
  }

}
