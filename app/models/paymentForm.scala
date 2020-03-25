package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.OFormat


case class paymentForm(name :String, number :String, expDate :String, cvc :String )

object paymentForm
{
  val payments : Form[paymentForm] = Form(
    mapping(
      "name" -> nonEmptyText,
      "number" -> nonEmptyText,
      "expDate" -> nonEmptyText,
      "cvc" -> nonEmptyText,
    )(paymentForm.apply)(paymentForm.unapply)
  )

  object JsonFormats {

    import play.api.libs.json.Json
    import reactivemongo.play.json._
    import reactivemongo.play.json.collection.JSONCollection

    implicit val paymentFormat: OFormat[paymentForm] = Json.format[paymentForm]
  }


}