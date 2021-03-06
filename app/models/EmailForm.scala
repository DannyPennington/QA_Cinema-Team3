package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.OFormat


case class EmailForm(email: String, subject: String, message: String)

object EmailForm {
  val Email: Form[EmailForm] = Form(
    mapping(
      "email" -> email,
      "subject" -> nonEmptyText,
      "message" -> nonEmptyText
    )(EmailForm.apply)(EmailForm.unapply)
  )
}
