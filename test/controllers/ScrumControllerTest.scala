package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

class ScrumControllerTest extends PlaySpec with Results with MockitoSugar {

  "scrum action" should {
    "display scrum page" in {
      val mongoService = mock[MongoService]
      val controller = new ScrumController(Helpers.stubControllerComponents(), mongoService)
      val result = controller.scrum().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("Scrum")
    }
  }



}
