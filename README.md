<h1 align="center">Image Processing Exercises</h1>
<h4 align="center">Simple image processing exercises using BoofCV</h4>

<p align="center">
  <img alt="UPM" src="https://img.shields.io/badge/EIT%20Digital-UPM-blue?style=flat-square">
</p>

### Description 

Exercises for the subject **Image processing, analysis and classification** of the EIT Digital 
Data Science Master at UPM (Technical University of Madrid).

Exercises have two parts:

* Exercises 0X: From exercise 2 to 9.
* Exercises 1X: *Work in progress*.

### Prerequisites

For running the exercises the prerequisites are:

* `Java` version `1.8`.
* `Maven`, if not installed visit [install Maven](https://maven.apache.org/install.html).

### Installation

For compiling the exercises, and generate the `jar` file containing all the exercises 
you have to follow this steps:

```
mvn package
chmod +x assignment/**/*.sh
```

### Project structure

The project is divided in the following folders:

* `assignment/`:  Is the folder containing all the responses for the assignments. Is where all the scripts of the exercises and pdfs are located.
* `report/`: Is where the source code (`.tex`) for the reports is located.
* `src/`: Folder containing the `Java` source code of the exercises.
* `test-images/`: Images for testing each exercise.

### Execute exercise

In this section is explained how to execute each exercise:

> **NOTE**: For executing the exercise is supposed to be located in the parent directory of the project.

* Exercise 2a

```
assignment/exercise-2/exercise_02a_thresh.sh exercise_02a_input_01.pgm value exercise_02a_output_01.pgm
```

* Exercise 2b

```
assignment/exercise-2/exercise_02b_compare.sh exercise_02b_input_01.pgm exercise_02b_input_02.pgm exercise_02b_output.txt
```

* Exercise 2c: 

Check `exercise_02c.pdf` file.

* Exercise 2d inf: 

```
assignment/exercise-2/exercise_02d_inf.sh exercise_02d_input_01.pgm exercise_0d_input_02.pgm exercise_02d_output_01.pgm
```

* Exercise 2d sup: 

```
assignment/exercise-2/exercise_02d_sup.sh exercise_02d_input_01.pgm exercise_0d_input_02.pgm exercise_02d_output_01.pgm
```

### Author

- **Cristian Abrante Dorta** - [CristianAbrante](https://github.com/CristianAbrante)