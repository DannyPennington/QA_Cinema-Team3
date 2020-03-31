package controllers
import javax.inject._
import models.EmailForm
import play.api.i18n.I18nSupport
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with I18nSupport {

  def index: Action[AnyContent] = Action { implicit request:Request[AnyContent] =>
    Ok(views.html.index())
  }


  def about: Action[AnyContent] = Action {
    Ok(views.html.about())
  }

  //def create: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
  //  Ok(views.html.payment(paymentForm.payments))
  //}

  def email: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.emailForm(EmailForm.Email))
  }



}
