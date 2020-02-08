import Direction.Direction

object Main extends App {

  val rover = new Rover(Position(0, 0, Direction.NORTH))
  printf("Starting position %s \n", rover.position.toString)

  val roverAfterCommands = rover.move(rover, "MMRM")
  printf("Position after movement %s \n", roverAfterCommands.position.toString)

  case class Position(x: Int = 0, y: Int = 0, direction: Direction)

  case class Plateau()

}
