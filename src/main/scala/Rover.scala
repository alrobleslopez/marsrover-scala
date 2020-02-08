import Main.Position

class Rover(var position: Position) {
  def move(rover: Rover, commands: String): Rover = {

    def moveForward(position: Position) = {
      position.direction match {
        case Direction.NORTH => Position(position.x + 1, position.y, Direction.NORTH)
        case Direction.SOUTH => Position(position.x - 1, position.y, Direction.SOUTH)
        case Direction.EAST => Position(position.x, position.y + 1, Direction.EAST)
        case Direction.WEST => Position(position.x, position.y - 1, Direction.WEST)
      }
    }

    def turnRight(position: Position) = {
      position.direction match {
        case Direction.NORTH => Position(position.x, position.y, Direction.EAST)
        case Direction.SOUTH => Position(position.x, position.y, Direction.WEST)
        case Direction.EAST => Position(position.x, position.y, Direction.SOUTH)
        case Direction.WEST => Position(position.x, position.y, Direction.NORTH)
      }
    }

    def turnLeft(position: Position) = {
      position.direction match {
        case Direction.NORTH => Position(position.x, position.y, Direction.WEST)
        case Direction.SOUTH => Position(position.x, position.y, Direction.EAST)
        case Direction.EAST => Position(position.x, position.y, Direction.NORTH)
        case Direction.WEST => Position(position.x, position.y, Direction.SOUTH)
      }
    }

    commands.split("").foreach {
      case "M" => rover.position = moveForward(rover.position)
      case "R" => rover.position = turnRight(rover.position)
      case "L" => rover.position = turnLeft(rover.position)
      case _ => rover.position
    }
    new Rover(rover.position)
  }
}