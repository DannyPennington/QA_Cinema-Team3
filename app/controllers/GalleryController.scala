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
}
