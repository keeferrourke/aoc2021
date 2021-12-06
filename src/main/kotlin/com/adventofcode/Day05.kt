package com.adventofcode

import kotlin.math.roundToInt

data class Point(val x: Int, val y: Int) : Comparable<Point> {
  override fun compareTo(other: Point): Int {
    val xeq = x.compareTo(other.x)
    return if (xeq != 0) xeq else y.compareTo(other.y)
  }
}

data class Line(val start: Point, val end: Point) {
  val isHorizontal: Boolean = start.y == end.y
  val isVertical: Boolean = start.x == end.x
  private val slope: Double
    get() =
      if (isVertical) Double.NaN
      else (start.y - end.y).toDouble() / (start.x - end.x)

  val points: List<Point> by lazy {
    when {
      isVertical -> (minOf(start.y, end.y)..maxOf(start.y, end.y)).map { y -> Point(start.x, y) }
      isHorizontal -> (minOf(start.x, end.x)..maxOf(start.x, end.x)).map { x -> Point(x, start.y) }
      else -> interpolate()
    }
  }

  private fun interpolate(): List<Point> {
    val (x1, y1) = minOf(start, end)
    val (x2, _) = maxOf(start, end)
    return (x1..x2).map { x ->
      Point(x, (y1 + (x - x1) * slope).roundToInt())
    }
  }
}

fun parse(input: String): List<Line> = input.lines()
  .map { line ->
    val (a, _, b) = line.split(" ")
    val (x1, y1) = a.split(",").map { it.toInt() }
    val (x2, y2) = b.split(",").map { it.toInt() }
    Line(Point(x1, y1), Point(x2, y2))
  }

fun findVents(input: String, excludeDiagonals: Boolean) = when (excludeDiagonals) {
  true -> findVents(input) { it.isVertical || it.isHorizontal }
  false -> findVents(input) { true }
}

private fun findVents(input: String, filter: (Line) -> Boolean): Int = parse(input)
  .filter(filter)
  .flatMap { it.points }
  .fold(mutableMapOf<Point, Int>()) { acc, point ->
    acc.apply { merge(point, 1) { v, _ -> v + 1 } }
  }
  .count { it.value > 1 }

fun day05(input: String, args: List<String> = listOf("p1")) {
  when (args.first().lowercase()) {
    "p1" -> println(findVents(input, excludeDiagonals = true))
    "p2" -> println(findVents(input, excludeDiagonals = false))
  }
}