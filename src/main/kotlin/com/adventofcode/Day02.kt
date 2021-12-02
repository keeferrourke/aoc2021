package com.adventofcode

/**
 * [https://adventofcode.com/2021/day/2]
 */
enum class Command {
  FORWARD, DOWN, UP;

  companion object {
    fun of(string: String) = when (string) {
      "forward" -> FORWARD
      "down" -> DOWN
      "up" -> UP
      else -> error("Unknown command")
    }
  }
}

private data class Position(val x: Int, val y: Int, val aim: Int)

fun commands(input: String): List<Pair<Command, Int>> = input.trim().lines()
  .map {
    val (first, second) = it.trim().split(" ", "\t")
    Command.of(first) to second.toInt()
  }

private fun diveBadly(pos: Position, command: Command, magnitude: Int): Position {
  return when (command) {
    Command.FORWARD -> pos.copy(x = pos.x + magnitude)
    Command.UP -> pos.copy(y = pos.y - magnitude)
    Command.DOWN -> pos.copy(y = pos.y + magnitude)
  }
}

private fun diveCorrectly(pos: Position, command: Command, magnitude: Int): Position {
  return when (command) {
    Command.FORWARD -> pos.copy(x = pos.x + magnitude, y = pos.y + magnitude * pos.aim)
    Command.UP -> pos.copy(aim = pos.aim - magnitude)
    Command.DOWN -> pos.copy(aim = pos.aim + magnitude)
  }
}

fun dive(commands: List<Pair<Command, Int>>, strategy: Strategy): Int = commands
  .fold(Position(0, 0, 0)) { pos, (command, magnitude) ->
    when (strategy) {
      Strategy.BADLY -> diveBadly(pos, command, magnitude)
      Strategy.CORRECTLY -> diveCorrectly(pos, command, magnitude)
    }
  }
  .run { x * y }

enum class Strategy { BADLY, CORRECTLY }

fun day02(input: String, args: List<String> = listOf("p1")) {
  when (args.first().lowercase()) {
    "p1" -> println(dive(commands(input), Strategy.BADLY))
    "p2" -> println(dive(commands(input), Strategy.CORRECTLY))
  }
}
