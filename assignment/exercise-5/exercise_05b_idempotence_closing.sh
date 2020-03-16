# !/bin/bash

OUT_DIR=out/
EXERCISE_DIR=out/exercise-5
EXERCISE_OUT=out/exercise-5/exercise_05b_output

if [ ! -d "$OUT_DIR" ]; then
  mkdir $OUT_DIR
fi

if [ ! -d "$EXERCISE_DIR" ]; then
  mkdir $EXERCISE_DIR
fi

assignment/exercise-4/exercise_04b_closing.sh "$@" "$EXERCISE_OUT-01.pgm"
assignment/exercise-4/exercise_04b_closing.sh "$1" "$EXERCISE_OUT-01.pgm" "$EXERCISE_OUT-02.pgm"
assignment/exercise-2/exercise_02b_compare.sh "$EXERCISE_OUT-01.pgm" "$EXERCISE_OUT-02.pgm" "$EXERCISE_DIR/exercise_02b_output_02.txt"
