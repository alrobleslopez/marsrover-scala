import Main.Position
import org.scalatest.flatspec.AnyFlatSpec

class CommandSpec extends AnyFlatSpec {

  "A MoveForward command" should "move the rover forward in the Grid" in {
    val rover = new Rover(Position(0, 0, Direction.NORTH))

    val roverAfterMoved = rover.move(rover, "MMM")

    assert(roverAfterMoved.position == Position(0, 3, Direction.NORTH))
  }

  "A TurnLeft command" should "turns the rover left in the Grid" in {
    val rover = new Rover(Position(0, 0, Direction.NORTH))

    val roverAfterMoved = rover.move(rover, "L")

    assert(roverAfterMoved.position == Position(0, 0, Direction.WEST))
  }

  "A TurnRight command" should "turns the rover right in the Grid" in {
    val rover = new Rover(Position(0, 0, Direction.NORTH))

    val roverAfterMoved = rover.move(rover, "R")

    assert(roverAfterMoved.position == Position(0, 0, Direction.EAST))
  }
}
