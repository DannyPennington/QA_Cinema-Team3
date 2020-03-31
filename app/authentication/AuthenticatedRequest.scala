package authentication

import com.google.inject.Inject
import controllers.{MongoService, routes}
import models.LoginDetails
import play.api.mvc._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContext, Future}

class AuthenticatedRequest[A](val username: String, request: Request[A]) extends WrappedRequest[A](request)

class AuthenticationAction @Inject()(val parser: BodyParsers.Default, val mongoService: MongoService)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[AuthenticatedRequest, AnyContent] {

//  override def invokeBlock[A](request: Request[A], block: AuthenticatedRequest[A] => Future[Result]): Future[Result] = {
//    request.session.get("username")
//      .flatMap(username => LoginDetails.getUsername(username))
//      .map(user => block(new AuthenticatedRequest(user.username, request)))
//      .getOrElse(Future.successful(Results.Redirect("/login")))
//  }

  override def invokeBlock[A](request: Request[A], block: AuthenticatedRequest[A] => Future[Result]): Future[Result] = {
    if (request.cookies.get("logged_in").isDefined) {
      request.cookies.get("logged_in").get.value
        .flatMap(username => Await.result(mongoService.findUserByUsername(username.toString), Duration.Inf)
          .map(user => block(new AuthenticatedRequest(user.username, request)))
        ).head
    }
    else {
      Future.successful(Results.Redirect(routes.LoginController.login()).flashing("authenticateFail" -> "fail"))
    }


  }
}