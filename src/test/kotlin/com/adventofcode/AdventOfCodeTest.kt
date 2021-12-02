package com.adventofcode

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

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

  test ("day 2 part 2") {
    dive(day2TestInput, strategy = Strategy.CORRECTLY) shouldBe 900
    dive(day2RealInput, strategy = Strategy.CORRECTLY) shouldBe 1997106066
  }
})
