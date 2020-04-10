package segmentation;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import utils.segmentation.fast.FastWaterShed;
import utils.segmentation.fast.WaterShed;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class Segmentation3A {
  public static void main(String[] args) {
    try {
      // Declare seeds (markers) and labels image
      GrayU8 inputImage = Utils.readImage(args[0]);

      FastWaterShed algorithm = new FastWaterShed();

      List<WaterShed> waterShedList = algorithm.compute(inputImage);

      BufferedImage image = WaterShed.getImageWithRegions(inputImage, waterShedList);
      Utils.saveImage(image, args[1]);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
