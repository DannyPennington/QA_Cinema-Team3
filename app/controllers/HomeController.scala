package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def index:Action[AnyContent] = Action {
    Ok(views.html.index("Your new application is ready."))
  }


}
