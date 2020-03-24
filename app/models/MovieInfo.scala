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

