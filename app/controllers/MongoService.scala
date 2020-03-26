package controllers

import javax.inject.Inject

import models.{FutureReleaseInfo, MovieInfo}

import play.api.mvc._
import reactivemongo.play.json.collection.{JSONCollection, _}
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.play.json._
import collection._
import models.paymentForm
import models.JsonFormats._
import play.api.libs.json.{JsValue, Json}
import reactivemongo.api.Cursor
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global

class MongoService @Inject()(
                              val reactiveMongoApi: ReactiveMongoApi
                            ) extends ReactiveMongoComponents {

  def currentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("gallery"))

  def releaseCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("releases"))

  def paymentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("payments"))

  def createMovie(movieInfo: MovieInfo): Future[WriteResult] = {
    currentCollection.flatMap(_.insert.one(movieInfo))
  }


  def findCurrentMovies(): Future[List[MovieInfo]] = {
    currentCollection.map {
      _.find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[MovieInfo]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[MovieInfo]]()
      )
    )
  }

  def createPaymentDetails(user: paymentForm): Future[WriteResult] = {
    paymentCollection.flatMap(_.insert.one(user))
  }


  def createFuture(futureReleaseInfo: FutureReleaseInfo): Future[WriteResult] = {
    releaseCollection.flatMap(_.insert.one(futureReleaseInfo))
  }


  def findFutureMovies(): Future[List[FutureReleaseInfo]] = {
    releaseCollection.map {
      _.find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[FutureReleaseInfo]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[FutureReleaseInfo]]()
      )
    )
  }

  def findByTitle(title: String): Future[List[MovieInfo]] = {
    val cursor: Future[Cursor[MovieInfo]] = currentCollection.map {
      _.find(Json.obj("title" -> title)).
        sort(Json.obj("created" -> -1)).
        cursor[MovieInfo]()
    }

    val futureUsersList: Future[List[MovieInfo]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[MovieInfo]]()
        )
      )
    futureUsersList
  }

  def findByFutureTitle(title: String): Future[List[FutureReleaseInfo]] = {
    val cursor: Future[Cursor[FutureReleaseInfo]] = releaseCollection.map {
      _.find(Json.obj("title" -> title)).
        sort(Json.obj("created" -> -1)).
        cursor[FutureReleaseInfo]()
    }

    val futureUsersList: Future[List[FutureReleaseInfo]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[FutureReleaseInfo]]()
        )
      )

    futureUsersList
  }
}

