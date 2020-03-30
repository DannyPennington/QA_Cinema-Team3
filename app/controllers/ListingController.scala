package controllers

import java.time._

import javax.inject.{Inject, _}
import models.{NewReleaseInfo, MovieInfo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ListingController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

 def listingGallery(): Action[AnyContent] = Action.async {
    mongoService.findCurrentMovies().map(listOfMovieInfo =>
      Ok(views.html.listingGallery(listOfMovieInfo))
    )
  }

  def createMovie(): Action[AnyContent] = Action.async {
    val actors = List("James", "Karen", "Ligma")
    val showtimes = List(LocalDateTime.now().toString, LocalDateTime.now().toString)
    val futureResult = mongoService.createCurrentMovie(MovieInfo("Distant Sun Wars", "Lord Voldemort", actors, showtimes, "https://i.pinimg.com/originals/dd/c4/1a/ddc41ad6bb9725d050cbcd08984c5fa1.jpg"))
     futureResult.map(_ => Ok("Movie created"))
  }

  def reInnit(): Action[AnyContent] = Action.async {
    mongoService.currentMoviesReInnit().map(_ => Ok("ReInnit listing gallery"))
  }
}
