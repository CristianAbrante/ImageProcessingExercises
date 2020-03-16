import exercise2.Exercise2a;
import exercise2.Exercise2b;
import exercise2.Exercise2d;
import exercise3.Exercise3;
import exercise4.Exercise4;
import exercise6.Exercise6;

interface ExerciseCallback {
  void executeExercise(String[] args);
}

enum Exercise {
  EX2A(Exercise2a::main),
  EX2B(Exercise2b::main),
  EX2D(Exercise2d::main),
  EX3(Exercise3::main),
  EX4(Exercise4::main),
  EX6(Exercise6::main);

  ExerciseCallback callback;

  Exercise(ExerciseCallback callback) {
    this.callback = callback;
  }

  public static Exercise get(String key) {
    for (Exercise exercise : Exercise.values()) {
      if (exercise.toString().equals(key.toUpperCase())) {
        return exercise;
      }
    }
    throw new IllegalArgumentException("Error: Exercise key " + key + " not specified");
  }

  public void executeExercise(String[] args) {
    this.callback.executeExercise(args);
  }
}

public class ExercisesExecutor {
  /**
   * First argument is the key of the exercise to be
   * executed, and the other arguments are the particular
   * arguments of the exercise.
   *
   * @param args of the exercise.
   */
  public static void main(String[] args) {
    if (args.length < 1) {
      System.err.println("Error: program expects the key of the exercise.");
      return;
    }
    String exerciseKey = args[0];
    Exercise exercise = Exercise.get(exerciseKey);
    String[] exerciseArgs = new String[args.length - 1];
    System.arraycopy(args, 1, exerciseArgs, 0, args.length - 1);
    exercise.executeExercise(exerciseArgs);
  }
}
