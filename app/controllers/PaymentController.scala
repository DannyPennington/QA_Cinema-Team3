package controllers

import javax.inject._
import models.paymentForm
import play.api.mvc._
import scala.concurrent.{Await, ExecutionContext, Future}

import scala.concurrent.Future

@Singleton
class PaymentController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with play.api.i18n.I18nSupport {

  def payment: Action[AnyContent] = Action {
    implicit request: Request[AnyContent] =>
      Ok(views.html.payment(paymentForm.payments))
  }

//
//  def createSong = Action.async { implicit request: Request[AnyContent] =>
//    paymentForm.payments.bindFromRequest.fold({ formWithErrors =>
//        BadRequest(views.html.payment(formWithErrors))
//      }
//    }


}