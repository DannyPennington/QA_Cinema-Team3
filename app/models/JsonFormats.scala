package models

import play.api.libs.json.OFormat

object JsonFormats {

  import play.api.libs.json.Json
  import reactivemongo.play.json._
  import reactivemongo.play.json.collection.JSONCollection


  implicit val movieInfoFormat: OFormat[MovieInfo] = Json.format[MovieInfo]
  implicit val futureReleaseInfoFormat: OFormat[FutureReleaseInfo] = Json.format[FutureReleaseInfo]
  implicit val paymentFormat: OFormat[paymentForm] = Json.format[paymentForm]
  implicit val userFormat: OFormat[User] = Json.format[User]

}
