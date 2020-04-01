package controllers

import javax.inject.{Inject, Singleton}
import models.{LoginDetails, User}
import play.api.mvc._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@Singleton
class LoginController @Inject()(cc: ControllerComponents, val mongoService: MongoService ) extends AbstractController(cc) with play.api.i18n.I18nSupport {


  def login(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    if (request.flash.get("invalid").isDefined) {
      Ok(views.html.login(LoginDetails.loginForm, "Invalid credentials"))
    }
    else if (request.flash.get("authenticateFail").isDefined) {
      Ok(views.html.login(LoginDetails.loginForm, "Please log in to view this page"))
    }
    else {
      Ok(views.html.login(LoginDetails.loginForm, ""))
    }
  }

  def logout(): Action[AnyContent] = Action { implicit request:Request[AnyContent] =>
    Redirect(routes.HomeController.index()).withNewSession
  }

  def loginSubmit(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    LoginDetails.loginForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.login(formWithErrors,""))
    }, { loginDetails =>
      val user = Await.result(mongoService.findUserByUsername(loginDetails.username),Duration.Inf)
      if (user.isEmpty) {
        Redirect(routes.RegistrationController.showRegistration()).flashing("exists" -> "no")
      }
      else if (user.head.password == loginDetails.password && request.flash.get("authenticateFail").isEmpty) {
        Redirect(routes.HomeController.index()).withSession("username" -> loginDetails.username)
      }
      else if (user.head.password == loginDetails.password && request.flash.get("authenticateFail").isDefined) {
        Redirect(routes.DiscussionController.discussion()).withSession("username" -> loginDetails.username)
      }
      else
        Redirect(routes.LoginController.login()).flashing("invalid" -> "yes")
    })
  }

}