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
* Exercises 1X: Exercise 11 and 13.

### Prerequisites

For running the exercises the prerequisites are:

* `Java` version `1.8`.
* `Maven`, if not installed visit [install Maven](https://maven.apache.org/install.html).
* `opencv version 4.3.0`, if not installed for java visit [this guide](https://opencv-java-tutorials.readthedocs.io/en/latest/01-installing-opencv-for-java.html) 

### Installation

For compiling the exercises, and generate the `jar` file containing all the exercises 
you have to follow this steps:

```
mvn package
chmod +x assignment/**/*.sh
```

If you want to execute **Segmentation exercise 2B (Wheel teeth count)**, you have to set the variable 
`OPENCV_PATH` in the script `assignment/utils.sh`.  

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

##### Exercise 1a

```
assignment/segmentation/segmentation_1a.sh operation size input.png output.png
```

The arguments means:

   * `operation`: the type of operation could be erosion or dilation.
   * `size`: size of the structuring element used for operation.
   * `input.png`: input path for the image used.
   * `output.pgm`: output path for the image.

As example:

```
assignment/segmentation/segmentation_1a.sh erosion 1 test-images/segmentation/hitchcock.png out/hitchcock.pgm
```

##### Exercise 1b
   
```
assignment/segmentation/segmentation_1b.sh connectivity size input.png output.png
```

The arguments means:

  * `connectivity`: the type of connectivity could be 4-connectivity or 8-connectivity.
  * `size`: size of the structuring element used for operation.
  * `input.png`: input path for the image used.
  * `output.pgm`: output path for the image.

As example:

```
assignment/segmentation/segmentation_1b.sh 4 1 test-images/segmentation/particles.png out/particles.pgm
```

##### Exercise 2a

This program does not have arguments, the output is the number of counted wheel tooth:

```
assignment/segmentation/segmentation_2a.sh
```

Expected output:

```
Number of teeth > 102
```

**Note**: For this exercise the path of the opencv installation should
be set in the `assignment/utils.sh` file. 
  
##### Exercise 2b

This exercise does not expect any argument:

```
assignment/segmentation/segmentation_2b.sh
```

Used images are: `test-images/segmentation/coffee_grains.jpg` and `test-images/segmentation/coffee_markers.png` 

##### Exercise 3a

Implementation of the watershed algorithm could be found on location `src/main/java/utils/segmentation/fast/FastWaterShed.java`

If you want to execute the example could be done in:

```
assignment/segmentation/segmentation_3a_example.sh
```

Also the pseudocode report could be found in

```
assignment/segmentation/segmentation_3a_pseudocode.pdf
```
 

#### Exercises 1X

* Exercise 11

```
assignment/exercises-1x/exercise_11a_flatzone.sh exercise_11a_input_01.txt exercise_11a_input_01.pgm exercise_11a_output_01.pgm
```

* Exercise 13a

```
assignment/exercises-1x/exercise_13a_minimum.sh exercise_13a_input_01.txt exercise_11a_input_01.pgm exercise_13a_output_01.txt
```

* Exercise 13b

```
assignment/exercises-1x/exercise_13b_maximum.sh exercise_13b_input_01.txt exercise_13b_input_01.pgm exercise_13b_output_01.txt
```

### Author

- **Cristian M. Abrante Dorta** - [CristianAbrante](https://github.com/CristianAbrante)