package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object FutureReleaseInfo{
  def apply(title: String,
            url: String
           ) = new FutureReleaseInfo(BSONObjectID.generate(), title, url)
}

case class FutureReleaseInfo (
                              _id: BSONObjectID,
                              title: String,
                              url: String,
                            )

