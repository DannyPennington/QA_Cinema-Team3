package models
import play.api.data.Form
import play.api.data.Forms._

case class Registration(forename:String, surname:String, email: String, password: String)

object Registration {
  val RegistrationForm:Form[Registration] = Form(
    mapping(
      "forename" -> nonEmptyText,
      "surname" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText
    )(Registration.apply)(Registration.unapply)
  )

}