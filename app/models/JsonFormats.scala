package models

import play.api.libs.json.OFormat

object JsonFormats {

  import play.api.libs.json.Json
  import reactivemongo.play.json._
  import reactivemongo.play.json.collection.JSONCollection


  implicit val movieInfoFormat: OFormat[MovieInfo] = Json.format[MovieInfo]
  implicit val futureReleaseInfoFormat: OFormat[NewReleaseInfo] = Json.format[NewReleaseInfo]
  implicit val paymentFormat: OFormat[paymentForm] = Json.format[paymentForm]
  implicit val emailFormat: OFormat[EmailForm] = Json.format[EmailForm]
  implicit val venueInfoFormat: OFormat[VenueInfo] = Json.format[VenueInfo]


}
