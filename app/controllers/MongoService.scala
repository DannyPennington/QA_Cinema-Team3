package controllers

import javax.inject.Inject
import models._
import play.api.mvc._
import reactivemongo.play.json.collection.{JSONCollection, _}
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.play.json._
import collection._
import models.JsonFormats._
import play.api.libs.json.{JsValue, Json}
import org.joda.time.LocalDateTime
import reactivemongo.api.Cursor
import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global

class MongoService @Inject()(
                              val reactiveMongoApi: ReactiveMongoApi
                            ) extends ReactiveMongoComponents {

  def currentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("current"))

  def releaseCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("releases"))

  def paymentCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("payments"))

  def userCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("users"))

  def emailCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("email"))

  def venueCollection: Future[JSONCollection] = reactiveMongoApi.database.map(_.collection[JSONCollection]("venues"))


  def createCurrentMovie(movieInfo: MovieInfo): Future[WriteResult] = {

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

  def createEmailDetails(user: EmailForm): Future[WriteResult] = {
    emailCollection.flatMap(_.insert.one(user))
  }


  def createNewRelease(futureReleaseInfo: NewReleaseInfo): Future[WriteResult] = {
    releaseCollection.flatMap(_.insert.one(futureReleaseInfo))
  }


  def findNewReleases(): Future[List[NewReleaseInfo]] = {
    releaseCollection.map {
      _.find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[NewReleaseInfo]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[NewReleaseInfo]]()
      )
    )
  }

  def findCurrentMovieByTitle(title: String): Future[List[MovieInfo]] = {
    val cursor: Future[Cursor[MovieInfo]] = currentCollection.map {
      _.find(Json.obj("title" -> title)).
        sort(Json.obj("created" -> -1)).
        cursor[MovieInfo]()
    }

    val movieFuturesList: Future[List[MovieInfo]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[MovieInfo]]()
        )
      )
    movieFuturesList
  }

  def findNewReleaseByTitle(title: String): Future[List[NewReleaseInfo]] = {
    val cursor: Future[Cursor[NewReleaseInfo]] = releaseCollection.map {
      _.find(Json.obj("title" -> title)).
        sort(Json.obj("created" -> -1)).
        cursor[NewReleaseInfo]()
    }

    val releaseFuturesList: Future[List[NewReleaseInfo]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[NewReleaseInfo]]()
        )
      )

    releaseFuturesList
  }

  def createVenue(venueInfo: VenueInfo): Future[WriteResult] = {
    venueCollection.flatMap(_.insert.one(venueInfo))
  }


  def findVenues(): Future[List[VenueInfo]] = {
    venueCollection.map {
      _.find(Json.obj())
        .sort(Json.obj("created" -> -1))
        .cursor[VenueInfo]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[VenueInfo]]()
      )
    )
  }

  def findVenueByName(name: String): Future[List[VenueInfo]] = {
    val cursor: Future[Cursor[VenueInfo]] = venueCollection.map {
      _.find(Json.obj("name" -> name)).
        sort(Json.obj("created" -> -1)).
        cursor[VenueInfo]()
    }

    val venueFuturesList: Future[List[VenueInfo]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[VenueInfo]]()
        )
      )
    venueFuturesList
  }


  def currentMoviesReInnit(): Future[WriteResult] = {
    currentCollection.map {
      _.drop
    }

    createCurrentMovie(MovieInfo("Mulan","Bob",List("A","B","C"),List(LocalDateTime.now().toString(),"11","54","1509"),"https://m.media-amazon.com/images/M/MV5BODkxNGQ1NWYtNzg0Ny00Yjg3LThmZTItMjE2YjhmZTQ0ODY5XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg"))
    createCurrentMovie(MovieInfo("Pocahontas","Romeo",List("D","E","F"),List(LocalDateTime.now().plusHours(1).toString(),"17"),"https://lh3.googleusercontent.com/proxy/OJIQ61YFf7Pr1JOdo1fey-DFRHj1NEhin1aizcJJpQLWIqMuiieyXeHjpukcHxiVynaED7TUrlrql9NjhLzu7ap2cHJwfJUPy8KnGFxNArk"))
    createCurrentMovie(MovieInfo("Pinocchio","Denice",List("G","H"),List(LocalDateTime.now().plusHours(2).toString()),"https://i.etsystatic.com/18324742/r/il/1109e0/1918068271/il_570xN.1918068271_atcj.jpg"))
    createCurrentMovie(MovieInfo("Brother Bear","Jarvis",List("I"),List(LocalDateTime.now().plusHours(3).toString()),"https://images.wolfgangsvault.com/m/xlarge/ZZZ060321-PO/brother-bear-poster-oct-31-2003.webp"))
    createCurrentMovie(MovieInfo("Ice Age","The Legend 27",List("J","K","L"),List(LocalDateTime.now().plusHours(4).toString()),"https://www.iceposter.com/thumbs/MOV_wmtybram_b.jpg"))

  }

  def releaseReInnit(): Future[WriteResult] = {
    releaseCollection.map {
      _.drop
    }


    createNewRelease(NewReleaseInfo("Star Wars: Episode 1", "Me", List("Qui-Gon Jinn","Double Saber Dude", "Jar Jar"),"01/04/2020","https://m.media-amazon.com/images/M/MV5BYTRhNjcwNWQtMGJmMi00NmQyLWE2YzItODVmMTdjNWI0ZDA2XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg"))
    createNewRelease(NewReleaseInfo("Star Wars: Episode 2", "You", List("Boba Fett","Count Dooku"),"02/04/2020","https://m.media-amazon.com/images/M/MV5BMDAzM2M0Y2UtZjRmZi00MzVlLTg4MjEtOTE3NzU5ZDVlMTU5XkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_.jpg"))
    createNewRelease(NewReleaseInfo("Star Wars: Episode 3", "Them", List("The Senate","Mace Windu", "Dewit"),"03/04/2020","https://images-na.ssl-images-amazon.com/images/I/71MKj4j-isL._SL1200_.jpg"))
    createNewRelease(NewReleaseInfo("Star Wars: Episode 4", "Us", List("Darth Vadar","Han Solo"),"04/04/2020","https://m.media-amazon.com/images/M/MV5BNzVlY2MwMjktM2E4OS00Y2Y3LWE3ZjctYzhkZGM3YzA1ZWM2XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg"))
    createNewRelease(NewReleaseInfo("Star Wars: Episode 5", "Daenerys of the House Targaryen, the First of Her Name, The Unburnt, Queen of the Andals, the Rhoynar and the First Men, Queen of Meereen, Khaleesi of the Great Grass Sea, Protector of the Realm, Lady Regent of the Seven Kingdoms, Breaker of Chains and Mother of Dragons", List("Yoda","R2-D2", "C-3PO"),"05/04/2020","https://images-na.ssl-images-amazon.com/images/I/71tglII26nL._AC_SY679_.jpg"))
    createNewRelease(NewReleaseInfo("Star Wars: Episode 6", "Tadas", List("Luke Skywalker","Chewie", "Slave Leia"),"06/04/2020","https://images-na.ssl-images-amazon.com/images/I/71fiCHdViHL._AC_SY879_.jpg"))

  }

  def venuesReInnit(): Future[WriteResult] =  {
    venueCollection.map {
      _.drop
    }

    createVenue(VenueInfo("Some Restaurant", "Restaurant", "Nice family restaurant with affordable prices within walking distance of the cinema", List("Sun-Thur 5pm-10.30pm, Fri&Sa 4pm - 12am"), List("user@testmail.ch", "0123456789"), List("50% off cinema tickets if you have a 2-course set menu", "10% off vegetarian main dishes on Fridays"), "https://images.reference.com/reference-production-images/question/aq/example-intangible-service-restaurant_dd58bcadec92f5a0.jpg?width=760&height=411&fit=crop"))
  }

  def createUser(user: User): Future[WriteResult] = {
    userCollection.flatMap(_.insert.one(user))
  }

  def usersReInnit(): Future[WriteResult] = {
    userCollection.map {
      _.drop(false)
    }
    createUser(User("admin", "admin@admin.com", "admin"))
  }

  def findAllUsers(): Future[List[User]] = {
    userCollection.map {
      _.find(Json.obj())
        .sort(Json.obj("email" -> -1))
        .cursor[User]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[User]]()
      )
    )
  }

  def findUserByUsername(username: String): Future[List[User]] = {
    userCollection.map {
      _.find(Json.obj("username" -> username))
        .sort(Json.obj("email" -> -1))
        .cursor[User]()
    }.flatMap(
      _.collect[List](
        -1,
        Cursor.FailOnError[List[User]]()
      )
    )
  }
}
