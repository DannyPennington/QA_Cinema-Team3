package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object FutureReleaseInfo {
  def apply(title: String,
            director: String,
            actors: List[String],
            releaseDate: String,
            url: String) = new FutureReleaseInfo(BSONObjectID.generate(), title, director, actors, releaseDate, url)
}

case class FutureReleaseInfo(
                              _id: BSONObjectID,
                              title: String,
                              director: String,
                              actors: List[String],
                              releaseDate: String,
                              url: String
                            )

