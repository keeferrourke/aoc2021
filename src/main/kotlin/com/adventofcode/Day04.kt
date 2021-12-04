package com.adventofcode

import kotlin.properties.Delegates

class Bingo(private val numbers: List<Int>, private val boards: List<Board>) {
  private val boardsSortedByTimeToWin: List<Board> by lazy {
    val result = mutableListOf<Board>()
    val unScored = boards.toMutableSet()
    for (number in numbers) {
      for (board in unScored) {
        board.stamp(number)
        if (board.hasBingo) {
          board.score(number)
          result += board
          continue
        }
      }
      unScored -= result.toSet()
    }
    result
  }

  fun findAndScoreFirstWinner(): Int =
    boardsSortedByTimeToWin.firstOrNull()?.score ?: error("No winning boards!")

  fun findAndScoreLastWinner(): Int =
    boardsSortedByTimeToWin.lastOrNull()?.score ?: error("No winning boards!")

  companion object {
    fun parse(input: String): Bingo {
      var lines = input.lines()
      val numbers = lines.first().split(",").map { it.toInt() }
      lines = lines.drop(2)
      val boards = mutableListOf<Board>()
      while (lines.isNotEmpty()) {
        val rows = mutableListOf<List<Int>>()
        for (i in 0 until BOARD_SIZE) {
          val row = lines[i].trim().split("""\W+""".toRegex()).map { it.toInt() }
          rows += row
        }
        boards += Board(rows)
        lines = lines.drop(BOARD_SIZE + 1)
      }
      return Bingo(numbers, boards)
    }
  }
}

data class Board(val rows: List<List<Int>>) {
  private val rowMap = mutableMapOf<Int, Int>()
  private val colMap = mutableMapOf<Int, Int>()

  private val unMarkedNumbers = rows.flatten().toMutableSet()

  val hasBingo: Boolean
    get() = rowMap.values.any { it == BOARD_SIZE } || colMap.values.any { it == BOARD_SIZE }

  fun stamp(number: Int) {
    for ((i, row) in rows.withIndex()) {
      for ((j, n) in row.withIndex()) {
        if (n == number) {
          unMarkedNumbers -= n
          rowMap.merge(i, 1) { v, _ -> v + 1 }
          colMap.merge(j, 1) { v, _ -> v + 1 }
        }
      }
    }
  }

  var score by Delegates.notNull<Int>()
  fun score(lastCalled: Int) {
    score = unMarkedNumbers.sum() * lastCalled
  }
}

const val BOARD_SIZE: Int = 5

fun day04(input: String, args: List<String> = listOf("p1")) {
  val bingo = Bingo.parse(input)
  when (args.first().lowercase()) {
    "p1" -> println(bingo.findAndScoreFirstWinner())
    "p2" -> println(bingo.findAndScoreLastWinner())
  }
}