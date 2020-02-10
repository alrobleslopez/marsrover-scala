import Main.Position

trait Command {

  def execute(position: Position): Position

}

object MoveForward extends Command {
  override def execute(position: Position): Position = {

    position.direction match {
      case Direction.NORTH => Position(position.x + 1, position.y, Direction.NORTH)
      case Direction.SOUTH => Position(position.x - 1, position.y, Direction.SOUTH)
      case Direction.EAST => Position(position.x, position.y + 1, Direction.EAST)
      case Direction.WEST => Position(position.x, position.y - 1, Direction.WEST)
    }
  }
}

object TurnRight extends Command {
  override def execute(position: Position): Position = {
    position.direction match {
      case Direction.NORTH => Position(position.x, position.y, Direction.EAST)
      case Direction.SOUTH => Position(position.x, position.y, Direction.WEST)
      case Direction.EAST => Position(position.x, position.y, Direction.SOUTH)
      case Direction.WEST => Position(position.x, position.y, Direction.NORTH)
    }
  }
}

object TurnLeft extends Command {
  override def execute(position: Position): Position = {
    position.direction match {
      case Direction.NORTH => Position(position.x, position.y, Direction.WEST)
      case Direction.SOUTH => Position(position.x, position.y, Direction.EAST)
      case Direction.EAST => Position(position.x, position.y, Direction.NORTH)
      case Direction.WEST => Position(position.x, position.y, Direction.SOUTH)
    }
  }
}
