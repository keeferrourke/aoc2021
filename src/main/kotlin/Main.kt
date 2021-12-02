import com.adventofcode.day01
import com.adventofcode.day02
import java.lang.System.err
import kotlin.system.exitProcess

fun main(args: Array<String>) {
  if (args.isEmpty()) {
    exit(2, "usage: aoc <day number> -- <args>")
  }

  val day = args[0]
  val dayArgs = args.drop(2)

  val input = generateSequence(::readLine).joinToString("\n")

  when (day.toIntOrNull()) {
    1 -> day01(input, dayArgs)
    2 -> day02(input, dayArgs)
    else -> exit(1, "Unknown Advent of Code day")
  }
}

fun exit(code: Int, message: String? = null) : Nothing {
  message?.let {  err.println(message) }
  exitProcess(code)
}
