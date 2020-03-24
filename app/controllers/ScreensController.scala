package controllers

import javax.inject.Inject
import play.api.mvc._

@Singleton
class ScreensController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc){

  def screens:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.screens())
  }

}
