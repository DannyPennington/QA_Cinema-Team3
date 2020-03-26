package controllers

import java.time._

import javax.inject.{Inject, _}
import models.{MovieInfo, VenueInfo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class VenueGalleryController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def venueGallery(): Action[AnyContent] = Action.async {
    mongoService.findVenues().map(listOfVenueInfo =>
      Ok(views.html.venueGallery(listOfVenueInfo))
    )
  }

  def createVenue(): Action[AnyContent] = Action.async {
    val name = "Test Restaurant"
    val openingTimes = List("Mon-Sun, 5.30pm - 11.30pm")
    val contactDetails = List("myemail@emailprovider.com", "0987654321")
    val offers = List("70% off starters and 25% off drinks each year on 30th February")
    val futureResult = mongoService.createVenue(VenueInfo(name, "Restaurant", "The right place for everyone who loves food poisoning", openingTimes, contactDetails, offers, "https://lh4.ggpht.com/CIy6xGX12gaSayMAlKi4VA6a-N8v6dqRlWN7kHy-vUFg1mFtY8wbwkvW3qonZZFvjMg=w300"))
    futureResult.map(_ => Ok("Venue created"))
  }

  def reInnit(): Action[AnyContent] = Action.async {
    mongoService.venuesReInnit().map(_ => Ok("ReInnit venue gallery"))
  }

}
