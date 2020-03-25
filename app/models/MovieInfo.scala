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