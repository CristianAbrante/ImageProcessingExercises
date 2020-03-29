package segmentation;

import boofcv.alg.filter.binary.BinaryImageOps;
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
  public static String SEEDS_PATH = "test-images/segmentation/coffee_markers.png";

  public static void main(String[] args) {
    try {
      // Declare input images
      BufferedImage inputImage = ImageIO.read(new File(IMAGE_PATH));
      GrayU8 grayInputImage = ConvertBufferedImage.convertFromSingle(inputImage, null, GrayU8.class);

      // Declare seeds (markers) and labels image
      GrayU8 markersImage = Utils.readBinaryImage(SEEDS_PATH);
      GrayS32 markers = new GrayS32(inputImage.getWidth(), inputImage.getHeight());

      // Calculate the number of regions.
      int numberOfRegions = BinaryImageOps.contour(markersImage, ConnectRule.EIGHT, markers).size() + 1;
      System.out.println("Number of regions > " + numberOfRegions);

      // Use the watershed segmentation
      WatershedVincentSoille1991 watershed = FactorySegmentationAlg.watershed(ConnectRule.FOUR);
      watershed.process(grayInputImage, markers);

      // Output image
      GrayS32 output = watershed.getOutput();

      BufferedImage outLabeled = VisualizeBinaryData.renderLabeledBG(markers, numberOfRegions, null);
      VisualizeRegions.watersheds(output, inputImage, 1);

      watershed.removeWatersheds();
      numberOfRegions -= 1;
      BufferedImage outRegions = VisualizeRegions.regions(output, numberOfRegions, null);

      ListDisplayPanel gui = new ListDisplayPanel();
      gui.addImage(inputImage, "Watersheds");
      gui.addImage(outRegions, "Regions");
      gui.addImage(outLabeled, "Seeds");
      ShowImages.showWindow(gui, "Watershed", true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
