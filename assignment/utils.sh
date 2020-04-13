# !/bin/bash

JAR_FILE=target/ImageProcessingExercises0x.jar

if
  [ ! -f "$JAR_FILE" ]
then
  echo "Error: Project not compiled. Use mvn package for compile the project."
  exit 1
else
  java -jar $JAR_FILE "$@"
fi
