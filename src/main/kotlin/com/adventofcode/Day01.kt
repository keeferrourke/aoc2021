package com.adventofcode

/**
 * [https://adventofcode.com/2021/day/1]
 */
fun sonarSweep(readings: Sequence<Int>, window: Int = 1): Int = readings
  .windowed(window)
  .map { it.sum() }
  .fold(listOf<Pair<Int, DepthChange>>()) { acc, windowedSum ->
    acc + (acc.lastOrNull()?.let { windowedSum to DepthChange.of(it.first, windowedSum) }
      ?: (windowedSum to DepthChange.NONE))
  }
  .count { it.second == DepthChange.INCREASE }

enum class DepthChange {
  NONE, INCREASE, DECREASE;

  companion object {
    fun of(prev: Int, next: Int): DepthChange {
      val change = next - prev
      return when {
        change > 0 -> INCREASE
        change < 0 -> DECREASE
        else -> NONE
      }
    }
  }
}

fun day01(input: String, args: List<String> = listOf("1")) {
  val readings = input.lineSequence().map { it.toInt() }
  val window = args.first().toInt()
  println(sonarSweep(readings, window))
}
