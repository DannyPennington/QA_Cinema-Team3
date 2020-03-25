package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class Classifications @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def createClassifications:Action[AnyContent] = Action {
    Ok(views.html.classifications())
  }

}
