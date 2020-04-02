package controllers

import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.mvc.Results
import org.scalatestplus.play._
import play.api.mvc._
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.Future

class ClassificationsControllerTest extends PlaySpec with Results with MockitoSugar {

  "Classifications page" should {
    "should have the following" in {
      val controller = new ClassificationsController(Helpers.stubControllerComponents())
      val result: Future[Result] = controller.classifications().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("QA Cinemas Classifications")
      contentAsString(result) must include("To be confirmed")
      contentAsString(result) must include("Universal")
      contentAsString(result) must include("Parental Guidance")
      contentAsString(result) must include("12A with Parental Guidance")
      contentAsString(result) must include("15")
      contentAsString(result) must include("18")
      contentAsString(result) must include("In some instances films had not been certified before the website was uploaded. Child and Family tickets purchased before a film is certified as 15 or over can be refunded. Please contact Guest Services before the performance for a refund, as admission may be refused on the day. ")
      contentAsString(result) must include("Suitable for audiences aged 4 years and over. Check with consumer advice before allowing under 4's to view this film.")
      contentAsString(result) must include("General Viewing, but some scenes may be unsuitable for young children. ")
      contentAsString(result) must include("Suitable only for persons of 15 years and older. Please note: proof of age may be required. ")
      contentAsString(result) must include("Suitable only for persons of 18 years and older. Please note: proof of age may be required. ")

    }
  }
}