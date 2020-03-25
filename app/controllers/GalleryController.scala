package controllers

import javax.inject._
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter
import javax.inject.Inject
import models.MovieInfo
import play.api.http.MimeTypes
import play.api.mvc._
import play.api.routing._
import java.time._

import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class GalleryController @Inject()(cc: ControllerComponents, val mongoService: CurrentMovieMongoService) extends AbstractController(cc) {

  def index:Action[AnyContent] = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def listingGallery: Action[AnyContent] = Action.async {
    mongoService.findCurrentMovies().map(listOfMovieInfo =>
      Ok(views.html.listingGallery(listOfMovieInfo))
    )
  }

  def create(): Action[AnyContent] = Action.async {
    val actors = List("Derek", "Edna", "Freddy")
    val showtimes = List(LocalDateTime.now().toString, LocalDateTime.now().toString)
    val futureResult = mongoService.createUser(MovieInfo("Lord of the Necklaces", "Boris Johnson", actors, showtimes, "www.banter.com"))
  }
  def releaseGallery: Action[AnyContent] = Action.async {
    mongoService.findFutureMovies().map(listOfMovieInfo =>
      Ok(views.html.releaseGallery(listOfMovieInfo)))
  }

}
