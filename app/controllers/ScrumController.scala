package controllers

import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ScrumController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def scrum: Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.scrum())
  }

}
