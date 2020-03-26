package controllers

import javax.inject.Inject
import models.paymentForm
import models.JsonFormats
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.play.json._
import reactivemongo.play.json.collection.{JSONCollection, _}
import models.JsonFormats._
import scala.concurrent.{Await, ExecutionContext, Future}

class MongoControllerService @Inject()(
                                                    components: ControllerComponents,
                                                    val reactiveMongoApi: ReactiveMongoApi,
                                                    val mongoService: MongoService
                                                  ) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents with play.api.i18n.I18nSupport {

  implicit def ec: ExecutionContext = components.executionContext

  def collection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("payments"))


  def createPay = Action.async { implicit request: Request[AnyContent] =>
    paymentForm.payments.bindFromRequest.fold({ formWithErrors =>
      Future {
        BadRequest(views.html.payment(formWithErrors,"film","","","","","","",""))
      }
    }, { payDetails: paymentForm => {
      mongoService.createPaymentDetails(payDetails).map {
        _ => Ok(views.html.message("Thanks for booking"))
      }
    }

    })

  }

}