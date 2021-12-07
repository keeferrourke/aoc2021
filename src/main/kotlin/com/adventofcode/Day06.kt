package com.adventofcode

private const val DAYS_TO_REPRODUCE = 7
private const val DAYS_TO_MATURE = 2

data class Generation(val distribution: Map<Int, Long>) {
  private fun fillGaps(sparseMap: Map<Int, Long>): Map<Int, Long> {
    val result = mutableMapOf<Int, Long>()
    for (i in 0..DAYS_TO_MATURE + DAYS_TO_REPRODUCE) {
      result[i] = sparseMap[i] ?: 0
    }
    return result
  }

  val size: Long = distribution.values.sum()

  fun next(): Generation {
    val generation = fillGaps(distribution).toSortedMap().values.toList()
    val toReproduce = generation[0]
    val nextGeneration = generation.drop(1).toMutableList()
    nextGeneration[DAYS_TO_REPRODUCE - 1] += toReproduce
    nextGeneration[DAYS_TO_REPRODUCE + DAYS_TO_MATURE - 1] = toReproduce
    return Generation(nextGeneration.mapIndexed { idx, it -> idx to it }.toMap())
  }
}

fun computeFishPopulation(input: String, afterDays: Int = 80) =
  computeFishPopulation(parse(input), afterDays)

private fun computeFishPopulation(initialPopulation: List<Int>, afterDays: Int = 80): Long {
  val populationFreqMap = initialPopulation.groupingBy { it }
    .eachCount()
    .map { (k, v) -> k to v.toLong() }
    .toMap()
  var generation = Generation(populationFreqMap)
  for (i in 1..afterDays) {
    generation = generation.next()
  }
  return generation.size
}

private fun parse(input: String): List<Int> =
  input.lineSequence()
    .flatMap { it.split(",") }
    .map { it.toInt() }
    .toList()

fun day06(input: String, args: List<String> = listOf()) {
  when (args[0].lowercase()) {
    "p1" -> computeFishPopulation(input, 80)
    "p2" -> computeFishPopulation(input, 256)
  }
}