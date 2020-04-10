package segmentation;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import utils.segmentation.fast.FastWaterShed;

import java.io.IOException;

public class Segmentation3A {
  public static void main(String[] args) {
    try {
      // Declare seeds (markers) and labels image
      GrayU8 markersImage = Utils.readImage(args[0]);

      FastWaterShed algorithm = new FastWaterShed();

      algorithm.compute(markersImage);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
