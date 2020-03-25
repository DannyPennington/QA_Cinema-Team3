package controllers

import java.time._

import javax.inject.{Inject, _}
import models.{FutureReleaseInfo, MovieInfo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class GalleryController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def listingGallery(): Action[AnyContent] = Action.async {
    mongoService.findCurrentMovies().map(listOfMovieInfo =>
      Ok(views.html.listingGallery(listOfMovieInfo))
    )
  }

  def createMovie(): Action[AnyContent] = Action {
    val actors = List("James", "Karen", "Ligma")
    val showtimes = List(LocalDateTime.now().toString, LocalDateTime.now().toString)
    val futureResult = mongoService.createMovie(MovieInfo("Distant Sun Wars", "Lord Voldemort", actors, showtimes, "https://i.pinimg.com/originals/dd/c4/1a/ddc41ad6bb9725d050cbcd08984c5fa1.jpg"))
    Ok("Movie created")
  }

  def releaseGallery(): Action[AnyContent] = Action.async {
    mongoService.findFutureMovies().map(listOfMovieInfo =>
      Ok(views.html.releaseGallery(listOfMovieInfo)))
  }

  def createRelease(): Action[AnyContent] = Action {
    val actors = List("Mongo", "Noobs", "Orange")
    val futureResult = mongoService.createFuture(FutureReleaseInfo("Kingsman III", "Me", actors, "22/04/2020", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg/1200px-Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg.png"))
    Ok("Future created")
  }
}
