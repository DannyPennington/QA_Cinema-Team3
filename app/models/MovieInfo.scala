package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object MovieInfo {
  def apply(title: String,
            director: String,
            actors: List[String],
            showTimes: List[String],
            url: String) = new MovieInfo(BSONObjectID.generate(), title, director, actors, showTimes, url)
}

case class MovieInfo(
                      _id: BSONObjectID,
                      title: String,
                      director: String,
                      actors: List[String],
                      showTimes: List[String],
                      url: String
                    )

object JsonFormats {

  import play.api.libs.json.Json
  import reactivemongo.play.json._
  import reactivemongo.play.json.collection.JSONCollection


  implicit val movieInfoFormat: OFormat[MovieInfo] = Json.format[MovieInfo]
}
