package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class DirectionsController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def directions:Action[AnyContent] = Action {
    Ok(views.html.gettingThere())
  }

}
