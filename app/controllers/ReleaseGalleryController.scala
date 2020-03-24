package controllers

import javax.inject._
import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter
import javax.inject.Inject
import models.FutureReleaseInfo
import play.api.http.MimeTypes
import play.api.mvc._
import play.api.routing._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

//@Singleton
//class ReleaseGalleryController @Inject()(cc: ControllerComponents, val mongoService: CurrentMovieMongoService) extends AbstractController(cc) {
//
//  def releaseGallery: Action[AnyContent] = Action.async {
//    mongoService.findFutureMovies().map(listOfMovieInfo =>
//      Ok(views.html.releaseGallery(listOfMovieInfo)))
//  }
//
//}
