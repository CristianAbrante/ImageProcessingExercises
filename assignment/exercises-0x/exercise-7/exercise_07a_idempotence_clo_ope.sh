# !/bin/bash

OUT_DIR=out/
EXERCISE_DIR=out/exercise-7
EXERCISE_OUT=out/exercise-7/exercise_07a_output

if [ ! -d "$OUT_DIR" ]; then
  mkdir $OUT_DIR
fi

if [ ! -d "$EXERCISE_DIR" ]; then
  mkdir $EXERCISE_DIR
fi

assignment/exercises-0x/exercise-6/exercise_06a_closing_opening.sh "$@" "$EXERCISE_OUT-01.pgm"
assignment/exercises-0x/exercise-6/exercise_06a_closing_opening.sh "$1" "$EXERCISE_OUT-01.pgm" "$EXERCISE_OUT-02.pgm"
assignment/exercises-0x/exercise-2/exercise_02b_compare.sh "$EXERCISE_OUT-01.pgm" "$EXERCISE_OUT-02.pgm" "$EXERCISE_DIR/exercise_02b_output_01.txt"
