package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.mvc._

class BookingController@Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def booking:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.booking())
  }

  def parseDetails:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    val body = request.body.asFormUrlEncoded
    val film = body.get("film").head
    val date = body.get("date").head
    val time = body.get("time").head
    val user = body.get("user").head
    Ok(views.html.details(film,user,time,date))

  }
}
