package segmentation;

import boofcv.alg.misc.PixelMath;
import boofcv.core.image.ConvertImage;
import boofcv.struct.image.GrayU16;
import boofcv.struct.image.GrayU8;
import utils.Filter;
import utils.FilterOperation;
import utils.Utils;

import java.io.IOException;

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
      FilterOperation operation = FilterOperation.of(args[0]);
      System.out.println(args[1]);
      int size = Integer.parseInt(args[1]);
      GrayU8 inputImage = Utils.readImage(args[2]);

      GrayU8 filteredImage = Filter.applyFilterUsingElementSize(inputImage, size, operation);

      GrayU16 outputImage16B = new GrayU16(filteredImage.getWidth(), filteredImage.getHeight());
      GrayU8 outputImage8B = new GrayU8(filteredImage.getWidth(), filteredImage.getHeight());

      PixelMath.subtract(inputImage, filteredImage, outputImage16B);

      ConvertImage.convert(outputImage16B, outputImage8B);

      Utils.saveImage(outputImage8B, args[3]);

    } catch (IOException e) {
      System.err.println("Error parsing the arguments.");
      e.printStackTrace();
    }
  }
}
