package controllers
import models.{MovieInfo, VenueInfo, paymentForm}
import org.scalatestplus.mockito.MockitoSugar
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._
import org.scalatest._
import org.mockito.Mockito._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class VenueControllerTest extends PlaySpec with Results with MockitoSugar {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.global
  val mongoService = mock[MongoService]

  "Venues page" should {
    "load data from the database to display on screen" in {
      val venueList = Future[List[VenueInfo]](List(VenueInfo("PizzaHut", "food", "food", List("food", "extra"),List("072938","no"),List("Great offer here"), "www.food.com")))
      when(mongoService.findVenueByName("PizzaHut")).thenReturn(venueList)
      val controller = new VenueController(Helpers.stubControllerComponents(), mongoService)
      val result: Future[Result] = controller.venue("PizzaHut").apply(FakeRequest())
      contentType(result) mustBe Some("text/html")

    }
  }


}
