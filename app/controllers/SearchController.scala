package controllers
import javax.inject._
import models.MovieInfo
import play.api.i18n.I18nSupport
import play.api.mvc._

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

@Singleton
class SearchController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def showResults: Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
  Ok(views.html.searchResults("I feel you shouldn't be here if you didn't search for anything...", List.empty[MovieInfo]))
  }

  def search:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    val search = request.body.asFormUrlEncoded.get("search").head
    val currentMoviesFoundTitle = currentMovieSearchHelper(search, "title")
    val currentMoviesFoundActor = currentMovieSearchHelper(search, "actor")
    val currentMoviesFoundDirector = currentMovieSearchHelper(search, "director")
    val currentMoviesFoundTemp = currentMoviesFoundDirector.union(currentMoviesFoundActor)
    val currentMoviesFound = currentMoviesFoundTemp.union(currentMoviesFoundTitle).toList.sortWith(_.title < _.title)
    Ok(views.html.searchResults(search,currentMoviesFound))
  }

  def currentMovieSearchHelper(value: String, search: String): Set[MovieInfo] ={
    val finalMovies = ArrayBuffer.empty[MovieInfo]
    val movies = Await.result(mongoService.findCurrentMovies(),Duration.Inf)
    search match {
      case "title" => for (movie <- movies) {
        if (movie.title.toLowerCase.contains(value.toLowerCase) && !finalMovies.contains(movie)) {
          finalMovies.append(movie)
        }
      }
      case "actor" =>  for (movie <- movies) {
        if (movie.actors.exists(actor => actor.toLowerCase.contains(value.toLowerCase)) && !finalMovies.contains(movie)) {
          finalMovies.append(movie)
        }
      }
      case "director" =>  for (movie <- movies) {
        if (movie.director.toLowerCase.contains(value.toLowerCase) && !finalMovies.contains(movie)) {
          finalMovies.append(movie)
        }
      }
    }
    finalMovies.toSet
  }


}
