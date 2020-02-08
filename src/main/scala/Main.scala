import Main.Direction.Direction

object Main extends App {

  val rover = new Rover(Position(0, 0, Direction.NORTH))
  printf("Starting position %s \n", rover.position.toString)

  val roverAfterCommands = rover.move(rover, "MM")
  printf("Position after movement %s \n", roverAfterCommands.position.toString)

  case class Position(x: Int = 0, y: Int = 0, direction: Direction = Direction.NORTH)

  case class Plateau()

  object Direction extends Enumeration {
    type Direction = Value
    val NORTH, SOUTH, EAST, WEST = Value
  }

}
