package com.adventofcode

import java.io.File

fun readFile(filename: String) : String {
  val url = Thread.currentThread().contextClassLoader.getResource(filename) ?: error("")
  return File(url.path).readText()
}
