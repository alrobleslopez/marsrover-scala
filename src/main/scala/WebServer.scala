import Main.Position
import akka.Done
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import spray.json.DefaultJsonProtocol._

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.io.StdIn

object WebServer {

  // needed to run the route
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future map/flatmap in the end and future in fetchItem and saveOrder
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  var orders: List[Item] = List(Item("1", 1))
  val rover = new Rover(Position(0, 0, Direction.NORTH))

  // domain model
  final case class Item(name: String, id: Long)

  final case class Order(items: List[Item])

  // formats for unmarshalling and marshalling
  implicit val itemFormat = jsonFormat2(Item)
  implicit val orderFormat = jsonFormat1(Order)

  // (fake) async database query api
  def fetchItem(command: String): Future[Option[Rover]] = Future {
    Some(rover.move(command))
  }

  def saveOrder(order: Order): Future[Done] = {
    orders = order match {
      case Order(items) => items ::: orders
      case _ => orders
    }
    Future {
      Done
    }
  }

  def main(args: Array[String]) {

    val route: Route =
      concat(
        get {
          path("item") {
            // there might be no item for a given id
            parameters(Symbol("command").as[String]) { (command) => {
              val maybeItem: Future[Option[Rover]] = fetchItem(command)

              onSuccess(maybeItem) {
                case Some(item) => complete(item)
                case None => complete(StatusCodes.NotFound)
              }
            }
            }

          }
        },
        post {
          path("create-order") {
            entity(as[Order]) { order =>
              val saved: Future[Done] = saveOrder(order)
              onComplete(saved) { done =>
                complete("order created")
              }
            }
          }
        }
      )

    val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done

  }
}