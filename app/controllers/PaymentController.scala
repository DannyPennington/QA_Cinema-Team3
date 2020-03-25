package controllers

import javax.inject._
import models.paymentForm
import play.api.mvc._

@Singleton
class PaymentController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def payment: Action[AnyContent] = Action {
     implicit request: Request[AnyContent] =>
      Ok(views.html.payment(paymentForm.payments))
    }

  }


