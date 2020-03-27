package models

import play.api.libs.json.OFormat
import reactivemongo.bson.BSONObjectID

object VenueInfo {
  def apply(name: String,
            category: String,
            description: String,
            openingTimes: List[String],
            contactDetails: List[String],
            offers: List[String],
            url: String) = new VenueInfo(BSONObjectID.generate(), name, category, description, openingTimes, contactDetails, offers, url)
}

case class VenueInfo(
                      _id: BSONObjectID,
                      name: String,
                      category: String,
                      description: String,
                      openingTimes: List[String],
                      contactDetails: List[String],
                      offers: List[String],
                      url: String
                    )
