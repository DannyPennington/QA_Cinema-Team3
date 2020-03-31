package controllers

import java.time._

import javax.inject.{Inject, _}
import models.{NewReleaseInfo, MovieInfo}
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class ReleaseController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) {

  def releaseGallery(): Action[AnyContent] = Action.async { implicit request:Request[AnyContent] =>
    mongoService.findNewReleases().map(listOfMovieInfo =>
      Ok(views.html.releaseGallery(listOfMovieInfo)))
  }

  def createRelease(): Action[AnyContent] = Action.async { implicit request:Request[AnyContent] =>
    val actors = List("Mongo", "Noobs", "Orange")
    val futureResult = mongoService.createNewRelease(NewReleaseInfo("Kingsman III", "Me", actors, "22/04/2020", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg/1200px-Heraldic_Royal_Crown_of_the_King_of_the_Romans_%281486-c.1700%29.svg.png"))
    futureResult.map(_ => Ok("Release created"))
  }

  def reInnit(): Action[AnyContent] = Action.async { implicit request:Request[AnyContent] =>
    mongoService.releaseReInnit().map(_ => Ok("ReInnit listing gallery"))
  }
}
