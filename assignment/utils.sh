# !/bin/bash

OPENCV_PATH=/usr/local/Cellar/opencv/4.3.0/share/java/opencv4/
JAR_FILE=target/ImageProcessingExercises0x.jar

if
  [ ! -f "$JAR_FILE" ]
then
  echo "Error: Project not compiled. Use mvn package for compile the project."
  exit 1
else
  java -Djava.library.path=$OPENCV_PATH -jar $JAR_FILE "$@"
fi
