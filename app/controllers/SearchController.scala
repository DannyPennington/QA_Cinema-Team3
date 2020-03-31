package controllers
import javax.inject._
import play.api.i18n.I18nSupport
import play.api.mvc._

@Singleton
class SearchController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def showResults: Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
  Ok(views.html.searchResults("I feel you shouldn't be here if you didn't search for anything..."))
  }

  def search:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    val body = request.body.asFormUrlEncoded
    val search = body.get("search").head
    Ok(views.html.searchResults(search))
  }


}
