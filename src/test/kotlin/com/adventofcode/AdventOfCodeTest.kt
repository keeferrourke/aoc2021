package com.adventofcode

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave
import io.kotest.matchers.shouldNot

class AdventOfCodeTest : FunSpec({
  val day1TestInput = readFile("day01/test.txt").trim().lineSequence().map { it.toInt() }
  val day1RealInput = readFile("day01/input.txt").trim().lineSequence().map { it.toInt() }

  test("day 1 part 1") {
    sonarSweep(day1TestInput) shouldBe 7
    sonarSweep(day1RealInput) shouldBe 1709
  }

  test("day 1 part 2") {
    sonarSweep(day1TestInput, 3) shouldBe 5
    sonarSweep(day1RealInput, 3) shouldBe 1761
  }

  val day2TestInput = commands(readFile("day02/test.txt"))
  val day2RealInput = commands(readFile("day02/input.txt"))

  test("day 2 part 1") {
    dive(day2TestInput, strategy = Strategy.BADLY) shouldBe 150
    dive(day2RealInput, strategy = Strategy.BADLY) shouldBe 1936494
  }

  test("day 2 part 2") {
    dive(day2TestInput, strategy = Strategy.CORRECTLY) shouldBe 900
    dive(day2RealInput, strategy = Strategy.CORRECTLY) shouldBe 1997106066
  }

  val day3TestInput = readFile("day03/test.txt").lines()
  val day3RealInput = readFile("day03/input.txt").lines()

  test("day 3 part 1") {
    Diagnostic(day3TestInput).powerConsumption shouldBe 198
    Diagnostic(day3RealInput).powerConsumption shouldBe 2743844
  }

  test("day 3 part 2") {
    Diagnostic(day3TestInput).o2Rating shouldBe 23
    Diagnostic(day3TestInput).co2Rating shouldBe 10
    Diagnostic(day3TestInput).lifeSupportRating shouldBe 230
    Diagnostic(day3RealInput).lifeSupportRating shouldBe 6677951
  }

  val day4TestInput = readFile("day04/test.txt")
  val day4RealInput = readFile("day04/input.txt")

  test("day 4 part 1") {
    Bingo.parse(day4TestInput).findAndScoreFirstWinner() shouldBe 4512
    Bingo.parse(day4RealInput).findAndScoreFirstWinner() shouldBe 28082
  }

  test("day 4 part 2") {
    Bingo.parse(day4TestInput).findAndScoreLastWinner() shouldBe 1924
    Bingo.parse(day4RealInput).findAndScoreLastWinner() shouldBe 8224
  }

  val day5TestInput = readFile("day05/test.txt")
  val day5RealInput = readFile("day05/input.txt")

  test("day 5 part 1") {
    findVents(day5TestInput, excludeDiagonals = true) shouldBe 5
    findVents(day5RealInput, excludeDiagonals = true) shouldBe 4993
  }

  test("day 5 part 2") {
    findVents(day5TestInput, excludeDiagonals = false) shouldBe 12
    findVents(day5RealInput, excludeDiagonals = false) shouldBe 21101
  }
})
