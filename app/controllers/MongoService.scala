package controllers

import javax.inject.Inject
import models.JsonFormats._
import models.{FutureReleaseInfo, MovieInfo}
import play.api.libs.json.Json
import play.modules.reactivemongo.{ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.Cursor
import reactivemongo.api.commands.WriteResult
import reactivemongo.play.json._
import reactivemongo.play.json.collection.{JSONCollection, _}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MongoService @Inject()(
                              val reactiveMongoApi: ReactiveMongoApi
                            ) extends ReactiveMongoComponents {

  def currentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("gallery"))
  def releaseCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("releases"))

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

//
//  def doesNotExist(username: String): Future[Boolean] = {
//    findByUsername(username).map(user => user.isEmpty)
//  }
//
//  def updateHighscore(username: String, highscore: Int): Future[Any] = {
//    findByUsername(username).map{a =>
////      println(a.head.highscore)
//      val current = a.head.highscore
//      if (highscore > current) {
//        var user = a.head
//        user.highscore = highscore
//        collection.map(_.update.one(Json.obj("username" -> username), user))
//      }
//    }
//  }
//
//  def findByUsername(username: String): Future[List[User]] =  {
//    val cursor: Future[Cursor[User]] = collection.map {
//      _.find(Json.obj("username" -> username)).
//        sort(Json.obj("created" -> -1)).
//        cursor[User]()
//    }
//
//    val futureUsersList: Future[List[User]] =
//      cursor.flatMap(
//        _.collect[List](
//          -1,
//          Cursor.FailOnError[List[User]]()
//        )
//      )
//
//    futureUsersList
//  }
}
