package segmentation;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import utils.segmentation.Region;
import utils.segmentation.Segmentation;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement4;
import utils.structuring.StructuringElement8;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Segmentation1B {
  /**
   * Expects a binary image with white parts as the objects
   * and black parts as the background. Produces the same image
   * but with different colors with the found regions.
   *
   * @param args arguments of the program:
   *             - connectivity: could be 4 or 8 connectivity.
   *             - size: size of the structuring element.
   *             - input: input image path.
   *             - output: output image path.
   */
  public static void main(String[] args) {
    if (args.length != 4) {
      System.err.println("Error: program expects four arguments");
      return;
    }
    try {
      int connectivity = Integer.parseInt(args[0]);
      if (connectivity != 4 && connectivity != 8) {
        throw new IllegalArgumentException("Connectivity should be 4 or 8");
      }
      int size = Integer.parseInt(args[1]);
      StructuringElement element = connectivity == 4 ? new StructuringElement4(size) : new StructuringElement8(size);
      GrayU8 inputImage = Utils.readImage(args[2]);

      ArrayList<Region> regions = Segmentation.grassFireSegmentation(inputImage, element);
      BufferedImage outputImage = Segmentation.getImageWithRegions(inputImage, regions);

      Utils.saveImage(outputImage, args[3]);
    } catch (IOException e) {
      System.err.println("Error parsing the arguments.");
      e.printStackTrace();
    }
  }
}
