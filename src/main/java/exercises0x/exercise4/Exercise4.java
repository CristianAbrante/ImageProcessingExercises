package exercises0x.exercise4;

import boofcv.struct.image.GrayU8;
import utils.MorphologicalOperation;
import utils.MorphologicalOperator;
import utils.Utils;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement8;

import java.io.IOException;

public class Exercise4 {
  public static void main(String[] args) {
    if (args.length != 4) {
      System.err.println("Error: program expects four arguments");
      return;
    }
    try {
      MorphologicalOperation operation = MorphologicalOperation.of(args[0]);
      int size = Integer.parseInt(args[1]);
      StructuringElement element = new StructuringElement8(size);

      GrayU8 inputImage = Utils.readPGMImage(args[2]);
      GrayU8 resultImage = MorphologicalOperator.applyOperation(inputImage, operation, element);
      Utils.saveImage(resultImage, args[3]);
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
