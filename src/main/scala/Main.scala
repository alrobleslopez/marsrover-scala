import Main.Direction.Direction

object Main extends App {

  //investigate about default constructor
  val rover = Rover(Position(0, 0, Direction.NORTH))
  printf("Starting position %s \n", rover.position.toString)

  val roverAfterCommands = move(rover, "MM")
  printf("Position after movement %s \n", roverAfterCommands.position.toString)

  def move(rover: Rover, commands: String): Rover = {

    def moveForward(position: Position) = {
      position.direction match {
        case Direction.NORTH => Position(position.x + 1, position.y)
        case Direction.SOUTH => Position(position.x - 1, position.y)
        case Direction.EAST => Position(position.x, position.y + 1)
        case Direction.WEST => Position(position.x, position.y - 1)
      }
    }

    def turnRight(position: Position) = {
      val newDirection = position.direction match {
        case Direction.NORTH => Direction.EAST
        case Direction.SOUTH => Direction.WEST
        case Direction.EAST => Direction.SOUTH
        case Direction.WEST => Direction.NORTH
      }
      Position(position.x, position.y, newDirection)
    }

    def turnLeft(position: Position) = {
      val newDirection = position.direction match {
        case Direction.NORTH => Direction.WEST
        case Direction.SOUTH => Direction.EAST
        case Direction.EAST => Direction.NORTH
        case Direction.WEST => Direction.SOUTH
      }
      Position(position.x, position.y, newDirection)
    }

    var position = rover.position

    commands.split("").foreach {
      case "M" => position = moveForward(position)
      case "R" => position = turnRight(position)
      case "L" => position = turnLeft(position)
      case _ => position
    }
    Rover(position)
  }

  case class Rover(position: Position)
  case class Position(x: Int = 0, y: Int = 0, direction: Direction = Direction.NORTH)
  case class Plateau()


  object Direction extends Enumeration {
    type Direction = Value
    val NORTH, SOUTH, EAST, WEST = Value
  }

}
