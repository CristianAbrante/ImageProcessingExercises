# !/bin/bash

OUT_DIR=out/
EXERCISE_DIR=out/exercise-8

if [ ! -d "$OUT_DIR" ]; then
  mkdir $OUT_DIR
fi

if [ ! -d "$EXERCISE_DIR" ]; then
  mkdir $EXERCISE_DIR
fi

assignment/utils.sh ex8 "$@"
