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

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

class BookingControllerTest extends PlaySpec with Results with MockitoSugar {

  implicit val ec: ExecutionContextExecutor = ExecutionContext.global
  val mongoService = mock[MongoService]

  "Booking page" should {
    "get data from the database to display in the html" in {
      val currentMovieList = Future[List[MovieInfo]](List(MovieInfo("Mulan","Tony Bancroft",List("Ming-Na Wen","Eddie Murphy","BD Wong"),List("10:00","11:30","14:00","17:30"),"https://m.media-amazon.com/images/M/MV5BODkxNGQ1NWYtNzg0Ny00Yjg3LThmZTItMjE2YjhmZTQ0ODY5XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg")))
      val mongoService = mock[MongoService]
      when(mongoService.findCurrentMovies()).thenReturn(currentMovieList)
      val controller = new BookingController(Helpers.stubControllerComponents(), mongoService)
      val result: Future[Result] = controller.booking().apply(FakeRequest())
      contentType(result) mustBe Some("text/html")

    }
  }

  "Parse details" should {
    "parse the http body for form information and send it to the payment page" in {
      val controller = new BookingController(Helpers.stubControllerComponents(), mongoService)
      val request = FakeRequest(POST, "/booking").withFormUrlEncodedBody("film" -> "Mulan", "date" -> "Thu+Apr+02+2020", "screen_type" -> "Standard", "time" -> "11%3A30", "adultFinal" -> "3", "childrenFinal" -> "0", "concessionFinal" -> "0", "user" -> "Jason")
      val result: Future[Result] = controller.parseDetails.apply(request)
      contentType(result) mustBe Some("text/html")
    }
  }

}
