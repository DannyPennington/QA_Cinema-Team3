package controllers

import javax.inject.Inject
import models.MovieInfo
import play.api.mvc.{AbstractController, ControllerComponents, _}
import models.paymentForm
import play.api.i18n.I18nSupport

import scala.concurrent.Await
import scala.concurrent.duration.Duration

class BookingController@Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport{

  def booking:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    Ok(views.html.booking(currentMovieList))
  }

  def parseDetails:Action[AnyContent] = Action {implicit request:Request[AnyContent] =>
    val body = request.body.asFormUrlEncoded
    val film = body.get("film").head
    val date = body.get("date").head
    val time = body.get("time").head
    val user = body.get("user").head
    val screen = body.get("screen_type").head
    val adult = body.get("adultFinal").head
    val children = body.get("childrenFinal").head
    val concession = body.get("concessionFinal").head
    Ok(views.html.payment(paymentForm.payments,film,user,time,date,screen,adult,children,concession))
  }

  def currentMovieList: List[MovieInfo] = {
    Await.result(mongoService.findCurrentMovies(),Duration.Inf)
  }
}
