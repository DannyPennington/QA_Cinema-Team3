package controllers
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import javax.inject._
import models.{emailForm, paymentForm}
import play.api.i18n.I18nSupport
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def index: Action[AnyContent] = Action {
    Ok(views.html.index())
  }

  def email: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.emailForm(emailForm.email))
  }



}
