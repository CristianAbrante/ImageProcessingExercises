package exercises0x.exercise8;

import boofcv.struct.image.GrayU8;
import utils.MorphologicalOperation;
import utils.MorphologicalOperator;
import utils.Utils;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement8;

import java.io.IOException;

public class Exercise8 {
  public static void main(String[] args) {
    if (args.length != 1) {
      System.err.println("Error: program expects one arguments");
      return;
    }
    try {
      GrayU8 inputImage = Utils.readPGMImage(args[0]);
      StructuringElement element = new StructuringElement8(3, 3);

      // Apply first filter.
      GrayU8 firstFilterImage = MorphologicalOperator.applyOperation(inputImage, MorphologicalOperation.OPENING, element);
      Utils.saveImage(firstFilterImage, "out/exercise-8/exercise_08_output_01.pgm");

      // Apply second filter.
      GrayU8 secondFilterImage = MorphologicalOperator.applyOperation(inputImage, MorphologicalOperation.CLOSING, element);
      Utils.saveImage(secondFilterImage, "out/exercise-8/exercise_08_output_02.pgm");

      // Apply third filter.
      GrayU8 thirdFilterImage = MorphologicalOperator.applyOperation(inputImage, MorphologicalOperation.CLOSING_OPENING, element);
      Utils.saveImage(thirdFilterImage, "out/exercise-8/exercise_08_output_03.pgm");

      // Apply fourth filter.
      GrayU8 fouthFilterImage = MorphologicalOperator.applyOperation(inputImage, MorphologicalOperation.OPENING_CLOSING, element);
      Utils.saveImage(fouthFilterImage, "out/exercise-8/exercise_08_output_04.pgm");
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
