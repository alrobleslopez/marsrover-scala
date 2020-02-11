import Main.Position

import scala.annotation.tailrec

class Rover(var position: Position) {
  def move(commands: String): Rover = {

    val commandsList = commands.split("").map {
      case "M" => MoveForward
      case "R" => TurnRight
      case "L" => TurnLeft
    }

    val finalPosition = receive(position, commandsList)

    new Rover(finalPosition)
  }

  @tailrec
  private def receive(position: Position, commands: Seq[Command]): Position = {
    commands.length match {
      case 0 => position
      case _ =>
        receive(executeCommand(position, commands.head), commands.tail)
    }
  }

  private def executeCommand(position: Position, command: Command): Position = {
    command.execute(position)
  }
}