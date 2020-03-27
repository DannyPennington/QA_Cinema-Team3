package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

class HomeControllerTest extends PlaySpec with Results with MockitoSugar {

  "Example Page#index with template" should {
    "should be valid" in {
      val mongoService = mock[MongoService]
      val controller = new HomeController(Helpers.stubControllerComponents(), mongoService)
      val result: Future[Result] = controller.index().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("About")
      contentAsString(result) must include("Latest Releases")
      contentAsString(result) must include("Unique")
      contentAsString(result) must include("Architecture")
      contentAsString(result) must include("Quality Service")
      contentAsString(result) must include("Listings")
      contentAsString(result) must include("Home")
      contentAsString(result) must include("Opening times")
      contentAsString(result) must include("New Releases")
      contentAsString(result) must include("Screens")
      contentAsString(result) must include("Bookings")

    }
  }

}
