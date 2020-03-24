package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class classifications @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def classifications:Action[AnyContent] = Action {
    Ok(views.html.classifications())
  }

}
