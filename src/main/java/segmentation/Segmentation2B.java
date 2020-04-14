package segmentation;

import boofcv.alg.filter.binary.BinaryImageOps;
import boofcv.alg.filter.binary.ThresholdImageOps;
import boofcv.alg.misc.ImageStatistics;
import boofcv.alg.segmentation.watershed.WatershedVincentSoille1991;
import boofcv.factory.segmentation.FactorySegmentationAlg;
import boofcv.gui.ListDisplayPanel;
import boofcv.gui.binary.VisualizeBinaryData;
import boofcv.gui.feature.VisualizeRegions;
import boofcv.gui.image.ShowImages;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.ConnectRule;
import boofcv.struct.image.GrayS32;
import boofcv.struct.image.GrayU8;
import utils.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Segmentation2B {
  public static String IMAGE_PATH = "test-images/segmentation/coffee_grains.jpg";
  public static String SEEDS_PATH = "out/markers.pgm";

  public static void main(String[] args) {
    try {
      // Declare input images
      BufferedImage inputImage = ImageIO.read(new File(IMAGE_PATH));
      GrayU8 grayInputImage = ConvertBufferedImage.convertFromSingle(inputImage, null, GrayU8.class);

      // Declare seeds (markers) and labels image
      GrayU8 binary = new GrayU8(grayInputImage.width, grayInputImage.height);
      GrayS32 label = new GrayS32(grayInputImage.width, grayInputImage.height);

      // operations applied to find the markers
      double mean = ImageStatistics.mean(grayInputImage);
      ThresholdImageOps.threshold(grayInputImage, binary, (int) 150, true);
      GrayU8 filtered = BinaryImageOps.erode8(binary, 6, null);
      filtered = BinaryImageOps.dilate8(filtered, 2, null);

      GrayU8 binaryShow = new GrayU8(binary.width, binary.height);
      for (int i = 0; i < binary.width; i++) {
        for (int j = 0; j < binary.height; j++) {
          int value = filtered.get(i, j) == 1 ? 255 : 0;
          binaryShow.set(i, j, value);
        }
      }

      // Calculate the number of regions.
      int numberOfRegions = BinaryImageOps.contour(filtered, ConnectRule.EIGHT, label).size() + 1;
      System.out.println("Number of regions > " + numberOfRegions);

      // Use the watershed segmentation
      WatershedVincentSoille1991 watershed = FactorySegmentationAlg.watershed(ConnectRule.FOUR);
      watershed.process(grayInputImage, label);

      // Output image
      GrayS32 output = watershed.getOutput();

      BufferedImage outLabeled = VisualizeBinaryData.renderLabeledBG(label, numberOfRegions, null);
      VisualizeRegions.watersheds(output, inputImage, 1);

      watershed.removeWatersheds();
      numberOfRegions -= 1;
      BufferedImage outRegions = VisualizeRegions.regions(output, numberOfRegions, null);

      ListDisplayPanel gui = new ListDisplayPanel();
      gui.addImage(inputImage, "Watersheds");
      gui.addImage(outRegions, "Regions");
      gui.addImage(outLabeled, "Seeds");
      ShowImages.showWindow(gui, "Watershed", true);

      Utils.saveImage(binaryShow, SEEDS_PATH);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
