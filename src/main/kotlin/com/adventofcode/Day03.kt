package com.adventofcode

data class Diagnostic(val rowView: List<String>) {
  private val colView: List<String> by lazy { readColumns(rowView) }

  private fun readColumns(rows: List<String>): List<String> {
    val columns = mutableMapOf<Int, String>()
    for (row in rows) {
      for (j in row.indices) {
        columns.merge(j, "${row[j]}") { v, _ -> v + row[j] }
      }
    }
    return columns.entries.map { it.value }
  }

  private fun mostCommonBit(bitString: String): Char? {
    val counts = bitString.groupingBy { it }.eachCount()
    return when {
      counts['1']!! > counts['0']!! -> '1'
      counts['0']!! > counts['1']!! -> '0'
      else -> null
    }
  }

  private fun negateBit(bit: Char) = when (bit) {
    '1' -> '0'
    '0' -> '1'
    else -> error("Expected bit")
  }

  val powerConsumption: Int
    get() {
      val (gamma, epsilon) = colView.fold("" to "") { (gamma, epsilon), column ->
        val most = mostCommonBit(column) ?: '1'
        (gamma + most) to (epsilon + negateBit(most))
      }

      return gamma.toInt(2) * epsilon.toInt(2)
    }

  private fun o2RatingStrategy(bitString: String): Char =
    mostCommonBit(bitString) ?: '1'

  private fun co2RatingStrategy(bitString: String): Char =
    negateBit(o2RatingStrategy(bitString))

  private fun getRating(strategy: (String) -> Char): Int {
    var readings = rowView
    var step = 0
    var columns = readColumns(readings)
    do {
      val bit = strategy(columns[step])
      readings = readings.filter { it[step] == bit }
      columns = readColumns(readings)
      step += 1
    } while (readings.size > 1)
    return readings.first().toInt(2)
  }

  val o2Rating: Int get() = getRating(this::o2RatingStrategy)

  val co2Rating: Int get() = getRating(this::co2RatingStrategy)

  val lifeSupportRating: Int get() = o2Rating * co2Rating
}

fun day03(input: String, args: List<String> = listOf("p1")) {
  when (args.first().lowercase()) {
    "p1" -> println(Diagnostic(input.lines()).powerConsumption)
    "p2" -> println(Diagnostic(input.lines()).lifeSupportRating)
  }
}