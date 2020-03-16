package exercise2;

import boofcv.struct.image.GrayU8;
import utils.LatticeOperation;
import utils.LatticeOperator;
import utils.Utils;

import java.io.IOException;

public class Exercise2dInf {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("Error: program expects three arguments");
      return;
    }
    try {
      GrayU8 firstImage = Utils.readPGMImage(args[0]);
      GrayU8 secondImage = Utils.readPGMImage(args[1]);
      GrayU8 outputImage = LatticeOperator.applyOperation(firstImage, secondImage, LatticeOperation.INFIMUM);

      Utils.saveImage(outputImage, args[2]);
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
