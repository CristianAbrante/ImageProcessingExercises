package exercises0x.exercise2;

import boofcv.struct.image.GrayU8;
import utils.LatticeOperation;
import utils.LatticeOperator;
import utils.Utils;

import java.io.IOException;

public class Exercise2d {
  public static void main(String[] args) {
    if (args.length != 4) {
      System.err.println("Error: program expects four arguments");
      return;
    }
    try {
      LatticeOperation operation = LatticeOperation.of(args[0]);
      GrayU8 firstImage = Utils.readPGMImage(args[1]);
      GrayU8 secondImage = Utils.readPGMImage(args[2]);
      GrayU8 outputImage = LatticeOperator.applyOperation(firstImage, secondImage, operation);

      Utils.saveImage(outputImage, args[3]);
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
