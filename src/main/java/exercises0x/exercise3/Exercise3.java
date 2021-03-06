package exercises0x.exercise3;

import boofcv.struct.image.GrayU8;
import utils.Filter;
import utils.FilterOperation;
import utils.Utils;

import java.io.IOException;

public class Exercise3 {
  public static void main(String[] args) {
    if (args.length != 4) {
      System.err.println("Error: program expects four arguments");
      return;
    }
    try {
      FilterOperation operation = FilterOperation.of(args[0]);
      int size = Integer.parseInt(args[1]);
      GrayU8 inputImage = Utils.readPGMImage(args[2]);

      GrayU8 resultImage = Filter.applyFilterUsingElementSize(inputImage, size, operation);
      Utils.saveImage(resultImage, args[3]);
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
