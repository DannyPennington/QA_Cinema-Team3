package controllers

import javax.inject._
import models.paymentForm
import models.JsonFormats
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.mvc._
import scala.concurrent.{Await, ExecutionContext, Future}

import scala.concurrent.Future

@Singleton
class PaymentController @Inject()(cc: ControllerComponents, val mongoService: MongoService) extends AbstractController(cc) with play.api.i18n.I18nSupport {

//  def payment: Action[AnyContent] = Action {
//    implicit request: Request[AnyContent] =>
//      Ok(views.html.payment(paymentForm.payments))
//  }


//  def createPayment(name: String, number: String, expDate :String, cvc :String): Action[AnyContent] = Action.async {
//    val futureResult = mongoService.createPayment(paymentForm(name, number, expDate, cvc))
//    futureResult.map(_ => Ok("Payment created"))
//  }


//  def createPayment( name :String, number :String, expDate :String, cvc :String )
//  :Action[AnyContent] = Action.async { implicit request :Request[AnyContent] =>
//
//    val payment = JsonFormats(name, number, expDate, cvc)
//
//    val futureResult = paymentCollection().map(_.insert.one(payment))
//    futureResult.map( _ => Ok("submitted") )
//  }
//}

//
//  def createSong = Action.async { implicit request: Request[AnyContent] =>
//    paymentForm.payments.bindFromRequest.fold({ formWithErrors =>
//        BadRequest(views.html.payment(formWithErrors))
//      }
//    }


}