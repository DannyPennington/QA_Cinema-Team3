package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object NewReleaseInfo{
  def apply(title: String,
            director: String,
            actors: List[String],
            releaseDate: String,
            url: String) = new NewReleaseInfo(BSONObjectID.generate(), title, director, actors, releaseDate, url)
}

case class NewReleaseInfo(
                               _id: BSONObjectID,
                               title: String,
                               director: String,
                               actors: List[String],
                               releaseDate: String,
                               url: String
                            )

