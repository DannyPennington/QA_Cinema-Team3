package controllers

import javax.inject._
import models.paymentForm
import play.api.i18n.I18nSupport
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def index:Action[AnyContent] = Action {
    Ok(views.html.index("Your new application is ready."))
  }



}
