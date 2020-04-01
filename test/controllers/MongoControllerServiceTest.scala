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
    "take data from form, verify it's correct and store data in database and take you to nice thanks for booking screen" in {
      val controller = new MongoControllerService(Helpers.stubControllerComponents(),  reactiveMongoApi,  mongoService)
      val request = FakeRequest("POST", "/submitPayment").with
    }
  }
}
