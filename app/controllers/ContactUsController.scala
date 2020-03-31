package controllers
import javax.inject._
import play.api.mvc._

class ContactUsController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def contact:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.Contact())
  }

}
