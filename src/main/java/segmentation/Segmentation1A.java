package segmentation;

import boofcv.alg.misc.PixelMath;
import boofcv.core.image.ConvertImage;
import boofcv.struct.image.GrayU16;
import boofcv.struct.image.GrayU8;
import utils.Filter;
import utils.FilterOperation;
import utils.Utils;

import java.io.IOException;

public class Segmentation1A {
  /**
   * Performs boundary extraction
   *
   * @param args are the arguments like this:
   *             - erosion/dilation: the technique used for computing the boundaries.
   *             - size: integer specifying the size of the 8-connectivity structuring element.
   *             - input: path of the input image.
   *             - output: path of the output image.
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
