package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object MovieInfo {
  def apply(title: String,
            url: String
           ) = new MovieInfo(BSONObjectID.generate(), title, url)
}

case class MovieInfo(
                 _id: BSONObjectID,
                 title: String,
                 url: String,
               )

object JsonFormats {

  import play.api.libs.json.Json
  import reactivemongo.play.json._
  import reactivemongo.play.json.collection.JSONCollection

  implicit val movieInfoFormat: OFormat[MovieInfo] = Json.format[MovieInfo]
  implicit val futureReleaseInfoFormat: OFormat[FutureReleaseInfo] = Json.format[FutureReleaseInfo]
}
