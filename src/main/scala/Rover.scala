import Main.{Direction, Position}

class Rover(var position: Position) {
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
    new Rover(position)
  }
}