package controllers

import akka.stream.Materializer
import models.{MovieInfo, paymentForm}
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest._
import org.mockito.Mockito._
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.modules.reactivemongo.ReactiveMongoApi

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class MongoControllerServiceTest extends PlaySpec with Results with MockitoSugar {
  val mongoService = mock[MongoService]
  val reactiveMongoApi = mock[ReactiveMongoApi]

  "createPay method" should {
    "re-display the form with errors if filled in incorrectly" in {
      val controller = new MongoControllerService(Helpers.stubControllerComponents(),  reactiveMongoApi,  mongoService)
      val request = FakeRequest(POST, "/submitPayment")
      val result: Future[Result] = controller.createPay().apply(request)
      contentType(result) mustBe Some("text/html")
    }
  }

  "createPay method" should {
    "send data to payment form if form is filled in correctly" in {
      val controller = new MongoControllerService(Helpers.stubControllerComponents(),  reactiveMongoApi,  mongoService)
      val request = FakeRequest(POST, "/submitPayment").withFormUrlEncodedBody("name" -> "James", "number" -> "500", "expDate" -> "15", "cvc" -> "400")
      val result: Future[Result] = controller.createPay().apply(request)
      contentType(result) mustBe Some("text/html")

    }
  }
}
