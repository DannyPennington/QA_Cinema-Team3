package controllers
import javax.inject._
import play.api.mvc._

class OpeningController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def opening:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.opening())
  }

}
