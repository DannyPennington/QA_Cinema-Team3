package controllers

import javax.inject.Inject
import play.api.mvc._
import reactivemongo.play.json.collection.JSONCollection
import reactivemongo.play.json.collection.{JSONCollection, _}

import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.play.json._
import collection._
import models.{JsonFormats, paymentForm}
import models.JsonFormats._
import play.api.libs.json.{JsValue, Json}
import reactivemongo.api.Cursor
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global

class MongoService @Inject()(
                              val reactiveMongoApi: ReactiveMongoApi
                            ) extends ReactiveMongoComponents  {

  def paymentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("payments"))

//  def createPayment(name: String, number: String,expDate: String,cvc:String)   = {
//    collection.flatMap(_.insert.one())
//  }

  def createPayment(paymentInfo: paymentForm): Future[WriteResult] = {
    paymentCollection.flatMap(_.insert.one(paymentInfo))
  }






//  def createUser(user: User): Future[WriteResult] = {
//    collection.flatMap(_.insert.one(user))
//  }
//
//  def findAll(): Future[List[User]] = {
//    collection.map {
//      _.find(Json.obj())
//        .sort(Json.obj("created" -> -1))
//        .cursor[User]()
//    }.flatMap(
//      _.collect[List](
//        -1,
//        Cursor.FailOnError[List[User]]()
//      )
//    )
//  }
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
