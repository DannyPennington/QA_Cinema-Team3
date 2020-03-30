package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.OFormat


case class EmailForm(email: String, subject: String, message: String)

object EmailForm {
  val email: Form[EmailForm] = Form(
    mapping(
      "email" -> nonEmptyText,
      "subject" -> nonEmptyText,
      "message" -> nonEmptyText
    )(EmailForm.apply)(EmailForm.unapply)
  )
}
