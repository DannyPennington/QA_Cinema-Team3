package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

class OpeningControllerTest extends PlaySpec with Results with MockitoSugar {

  "Opening page" should {
    "should have the following" in {
      val controller = new OpeningController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.opening().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("Opening Times")
      contentAsString(result) must include("QA Cinemas Opening Hours ")
      contentAsString(result) must include("Monday")
      contentAsString(result) must include("Tuesday")
      contentAsString(result) must include("Wednesday")
      contentAsString(result) must include("Thursday")
      contentAsString(result) must include("Friday")
      contentAsString(result) must include("Saturday")
      contentAsString(result) must include("Sunday")
      contentAsString(result) must include("The following times are the opening hours of QA Cinemas daily!")
      contentAsString(result) must include("Time")

    }
  }
}