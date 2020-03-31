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
    val currentMoviesFound = searchCurrentMovies(search)
    Ok(views.html.searchResults(search,currentMoviesFound))
  }

  def searchCurrentMovies(title: String): List[MovieInfo] = {
    val finalMovies = ArrayBuffer.empty[MovieInfo]
    val movies = Await.result(mongoService.findCurrentMovies(),Duration.Inf)
    for (movie <- movies) {
      if (movie.title.contains(title)) {
        finalMovies.append(movie)
      }
    }
    finalMovies.toList.sortWith(_.title < _.title)
  }

}
