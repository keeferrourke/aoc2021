#!/usr/bin/env bash

# $1 = day number
# $2 = args list
# $3 = test input
# $4 = expected result
function do_test() {
  result=$(kotlinc -script "./main/kotlin/Day$1.kts" "${2}" < "./main/resources/day$1/$3")
  if ! test "$result" -eq "$4"; then
    echo "failed day$1 $2 $3, expected $4, but got $result"
  else
    echo "ok - day $1 $2 $3"
  fi
}

# Day 01. Sonar Sweep.
do_test 01 1 test.txt 7
do_test 01 1 input.txt 1709
do_test 01 3 test.txt 5
do_test 01 3 input.txt 1761

# Day 02. Dive!
do_test 02 1 test.txt 150
do_test 02 1 input.txt 1936494
do_test 02 2 test.txt 900
do_test 02 2 input.txt 1997106066

if [ -n "$?" ] ; then
  exit
fi

echo "passed"