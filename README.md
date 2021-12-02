# Advent of Code 2021

ACHTUNG! THERE MAY BE SPOILERS IN THIS REPOSITORY! 

My solutions to AOC2021, written in Kotlin.

Code is released under the ISC license. See LICENSE for details.

## Building

This project uses [Hermit](https://cashapp.github.io/hermit) to manage the build toolchain.

Run `./bin/activate-hermit` to set up the environment and download the dependencies.

Build the main puzzle runner with `./gradlew jar`.

## Running

Run the main program with `java -jar ./build/libs/aoc.jar <args>`

All inputs are organized under `src/test/resources/day#`.

## Testing

This project has tests for each solution under `src/test/kotlin`.
Tests use the [Kotest](https://kotest.io) framework with a JUnit 5 runner.
