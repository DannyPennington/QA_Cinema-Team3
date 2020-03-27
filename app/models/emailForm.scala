package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.OFormat


case class emailForm(email: String, subject: String, message: String)

object emailForm {
  val email: Form[emailForm] = Form(
    mapping(
      "email" -> nonEmptyText,
      "subject" -> nonEmptyText,
      "message" -> nonEmptyText
    )(emailForm.apply)(emailForm.unapply)
  )
}