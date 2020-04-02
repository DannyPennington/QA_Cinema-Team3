package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

class AboutUsControllerTest extends PlaySpec with Results with MockitoSugar {

  "About Us page" should {
    "should have the following" in {
      val mongoService = mock[MongoService]
      val controller = new HomeController(Helpers.stubControllerComponents(), mongoService)
      val result: Future[Result] = controller.about().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("Why Choose Us")
      contentAsString(result) must include("Danny Pennington (Team Leader) ")
      contentAsString(result) must include("Ahmed Ibrahim ")
      contentAsString(result) must include("Johannes Bull ")
      contentAsString(result) must include("Sam White")
      contentAsString(result) must include("Taiwo Oluwatolafun Aina-Badejo")
      contentAsString(result) must include("For more information a link to the contact page can be found below")
      contentAsString(result) must include("We provide a variety of up-to-date films with different classifications to choose from which are shown on state of the art screens")
      contentAsString(result) must include("We also have Parking, Hotels and Cafes nearby")
      contentAsString(result) must include(" Also, bookings can be made through our brand new website")
      contentAsString(result) must include("Our values are centered around trust, fun, quality service and accountability")

    }
  }
}