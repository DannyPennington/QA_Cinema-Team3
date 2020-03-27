package controllers

import javax.inject.{Inject, _}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class VenueController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def venue(name: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    mongoService.findVenueByName(name).map{ listOfVenue =>
      Ok(views.html.venue(listOfVenue.head))
    }
  }

}
