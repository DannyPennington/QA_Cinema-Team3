package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object DiscussionEntry {
  def apply(title: String,
            rating: Int,
            review: String) = new DiscussionEntry(BSONObjectID.generate(), title, rating, review)
}

case class DiscussionEntry(
                      _id: BSONObjectID,
                      title: String,
                      rating: Int,
                      review: String
                    )