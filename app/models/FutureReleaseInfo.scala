package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object FutureReleaseInfo {
  def apply(title: String,
            url: String
           ) = new MovieInfo(BSONObjectID.generate(), title, url)
}

case class FutureReleaseInfo(
                              _id: BSONObjectID,
                              title: String,
                              url: String,
                            )

//object JsonFormats {
//
//  import play.api.libs.json.Json
//  import reactivemongo.play.json._
//  import reactivemongo.play.json.collection.JSONCollection


//}
