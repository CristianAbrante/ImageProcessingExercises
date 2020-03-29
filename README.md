<h1 align="center">Image Processing Exercises</h1>
<h4 align="center">Simple image processing exercises using BoofCV</h4>

<p align="center">
  <img alt="UPM" src="https://img.shields.io/badge/EIT%20Digital-UPM-blue?style=flat-square">
</p>

### Description 

Exercises for the subject **Image processing, analysis and classification** of the EIT Digital 
Data Science Master at UPM (Technical University of Madrid).

Exercises have three parts:

* Exercises 0X: From exercise 2 to 9.
* Segmentation exercises.
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

### Execute exercises

In this section is explained how to execute each exercise:

> **NOTE**: For executing the exercise is supposed to be located in the parent directory of the project.

#### Exercises 0X

* Exercise 2a

```
assignment/exercises-0x/exercise-2/exercise_02a_thresh.sh exercise_02a_input_01.pgm value exercise_02a_output_01.pgm
```

* Exercise 2b

```
assignment/exercises-0x/exercise-2/exercise_02b_compare.sh exercise_02b_input_01.pgm exercise_02b_input_02.pgm exercise_02b_output.txt
```

* Exercise 2c

Check `exercise_02c.pdf` file.

* Exercise 2d inf

```
assignment/exercises-0x/exercise-2/exercise_02d_inf.sh exercise_02d_input_01.pgm exercise_0d_input_02.pgm exercise_02d_output_01.pgm
```

* Exercise 2d sup

```
assignment/exercises-0x/exercise-2/exercise_02d_sup.sh exercise_02d_input_01.pgm exercise_0d_input_02.pgm exercise_02d_output_01.pgm
```

* Exercise 3a

```
assignment/exercises-0x/exercise-3/exercise_03a_erosion.sh i exercise_03a_input_01.pgm exercise_03a_output_01.pgm
```

* Exercise 3b

```
assignment/exercises-0x/exercise-3/exercise_03b_dilation.sh i exercise_03b_input_01.pgm exercise_03b_output_01.pgm
```

* Exercise 4a

```
assignment/exercises-0x/exercise-4/exercise_04a_opening.sh i exercise_04a_input_01.pgm exercise_04a_output_01.pgm
```

* Exercise 4b

```
assignment/exercises-0x/exercise-4/exercise_04b_closing.sh i exercise_04b_input_01.pgm exercise_04b_output_01.pgm
```

* Exercise 5a

```
assignment/exercises-0x/exercise-5/exercise_05a_idempotence_opening.sh i exercise_05a_input_01.pgm
```

* Exercise 5b

```
assignment/exercises-0x/exercise-5/exercise_05b_idempotence_closing.sh i exercise_05b_input_01.pgm
```

* Exercise 6a

```
assignment/exercises-0x/exercise-6/exercise_06a_closing_opening.sh i exercise_06a_input_01.pgm exercise_06a_output_01.pgm
```

* Exercise 6b

```
assignment/exercises-0x/exercise-6/exercise_06b_opening_closing.sh i exercise_06b_input_01.pgm exercise_06b_output_01.pgm
```

* Exercise 7a

```
assignment/exercises-0x/exercise-7/exercise_07a_idempotence_clo_ope.sh i exercise_07a_input_01.pgm
```

* Exercise 7b

```
assignment/exercises-0x/exercise-7/exercise_07b_idempotence_ope_clo.sh i exercise_07b_input_01.pgm
```

* Exercise 8

```
assignment/exercises-0x/exercise-2/exercise_08_filter.sh exercise_08_input_01.pgm
```

* Exercise 9

Check `exercise_09.pdf` file.

#### Segmentation exercises

* Exercise 1a

```
assignment/segmentation/segmentation_1a.sh operation size input.png output.png
```

The arguments means:

   * `operation`: the type of operation could be erosion or dilation.
   * `size`: size of the structuring element used for operation.
   * `input.png`: input path for the image used.
   * `output.pgm`: output path for the image.
   
* Exercise 1b
   
```
assignment/segmentation/segmentation_1b.sh connectivity size input.png output.png
```

The arguments means:

  * `connectivity`: the type of connectivity could be 4-connectivity or 8-connectivity.
  * `size`: size of the structuring element used for operation.
  * `input.png`: input path for the image used.
  * `output.pgm`: output path for the image.

### Author

- **Cristian M. Abrante Dorta** - [CristianAbrante](https://github.com/CristianAbrante)