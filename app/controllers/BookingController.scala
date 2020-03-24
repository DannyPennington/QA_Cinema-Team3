package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.mvc._

class BookingController@Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def booking:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.booking())
  }
}
