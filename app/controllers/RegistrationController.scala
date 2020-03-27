package controllers

import javax.inject._
import play.api.mvc._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json._
import scala.concurrent.{Await, ExecutionContext, Future}
import models.{Registration, User}
import models.JsonFormats._
import play.api.libs.json._
import reactivemongo.api.Cursor
import scala.concurrent.duration.Duration


class RegistrationController @Inject()(
                                    components: ControllerComponents,
                                    val mongoService: MongoService
                                  ) extends AbstractController(components) with play.api.i18n.I18nSupport {

  implicit def ec: ExecutionContext = components.executionContext

  val collection: Future[JSONCollection] = mongoService.userCollection

  def searchHelper(category: String, value: String): Future[List[User]] = {
    val cursor: Future[Cursor[User]] = collection.map {
      _.find(Json.obj(category -> value)).
        sort(Json.obj(category -> -1)).
        cursor[User]()
    }
    val futureUsersList: Future[List[User]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[User]]()
        )
      )
    futureUsersList
  }

  def addUser(username: String, email: String, password: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val user = User(username, email, password)
    val exists = Await.result(emailExists(email), Duration.Inf)
    val usernameExists = Await.result(userExists(username), Duration.Inf)
    if (exists) {
      Future(Redirect(routes.RegistrationController.showRegistration()).flashing("emailInUse" -> "yes"))
    }
    else if (usernameExists) {
      Future(Redirect(routes.RegistrationController.showRegistration()).flashing("usernameInUse" -> "yes"))
    }
    else {
      val futureResult = collection.flatMap(_.insert.one(user))
      futureResult.map(_ => Redirect(routes.RegistrationController.success()).withSession(request.session + ("user" -> user.email)))
    }
  }

  def showRegistration: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    if (request.flash.get("emailInUse").isDefined) {
      Ok(views.html.registration(Registration.RegistrationForm, "Email address already registered with account!"))
    }
    else if (request.flash.get("usernameInUse").isDefined) {
      Ok(views.html.registration(Registration.RegistrationForm, "Username is already in use!"))
    }
    else {
      Ok(views.html.registration(Registration.RegistrationForm, ""))
    }
  }

  def registerUser: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Registration.RegistrationForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.registration(formWithErrors, ""))
    }, { register =>
      Redirect(routes.RegistrationController.addUser(register.username, register.email, register.password))

    })
  }

  def findByEmail(email: String): Action[AnyContent] = Action.async {
    val futureUsersList = searchHelper("email", email)
    futureUsersList.map { persons =>
      Ok(persons.toString)
    }
  }

  def emailExists(email: String): Future[Boolean] = {
    val futureUsersList = searchHelper("email", email)
    futureUsersList.map { person =>
      if (person.isEmpty) {
        false
      }
      else {
        true
      }
    }
  }

  def userExists(username: String): Future[Boolean] = {
    val futureUsersList = searchHelper("username", username)
    futureUsersList.map { person =>
      if (person.isEmpty) {
        false
      }
      else {
        true
      }
    }
  }

  def success(): Action[AnyContent] = Action { implicit request:Request[AnyContent] =>
    Ok(views.html.message("Thanks for registering! " + request.session.get("user")))
  }

  def reInnit(): Action[AnyContent] = Action.async {
    mongoService.usersReInnit().map(_ => Ok("Reinitialised collection with admin user"))
  }
}
