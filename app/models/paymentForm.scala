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
}