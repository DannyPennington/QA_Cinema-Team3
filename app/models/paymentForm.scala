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
      "cvc" -> nonEmptyText
    )(paymentForm.apply)(paymentForm.unapply)
  )



}