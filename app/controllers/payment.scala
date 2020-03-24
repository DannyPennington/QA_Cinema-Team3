package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class payment @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def payment:Action[AnyContent] = Action {
    Ok(views.html.payment())
  }



}