package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.OFormat

case class paymentForm( name :String, number :Int, expDate :Int, cvc :Int )

object paymentForm
{
  val payform :Form[paymentForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "number" -> number,
      "expDate" -> number,
      "cvc" -> number
    )(paymentForm.apply)(paymentForm.unapply)
  )

  object JsonFormats {

    import play.api.libs.json.Json
    import reactivemongo.play.json._
    import reactivemongo.play.json.collection.JSONCollection

    implicit val paymentInfoFormat: OFormat[paymentForm] = Json.format[paymentForm]
  }

}