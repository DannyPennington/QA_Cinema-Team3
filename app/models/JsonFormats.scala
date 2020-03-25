package models

import play.api.libs.json.{Json, OFormat}

object JsonFormats {
  implicit val paymentFormat: OFormat[paymentForm] = Json.format[paymentForm]
}