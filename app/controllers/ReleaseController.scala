package controllers

import java.time._

import authentication.AuthenticationAction
import javax.inject.{Inject, _}
import models.{FutureReleaseInfo, MovieInfo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ReleaseController @Inject()(cc: ControllerComponents,authAction: AuthenticationAction ,val mongoService: MongoService) extends AbstractController(cc) {

  def releaseGallery(): Action[AnyContent] = authAction.async {
    mongoService.findFutureMovies().map(listOfMovieInfo =>
      Ok(views.html.releaseGallery(listOfMovieInfo)))
  }

  def createRelease(): Action[AnyContent] = authAction.async {
    val actors = List("Mongo", "Noobs", "Orange")
    val futureResult = mongoService.createFuture(FutureReleaseInfo("Kingsman III", "Me", actors, "22/04/2020", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg/1200px-Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg.png"))
    futureResult.map(_ => Ok("Release created"))
  }
}
