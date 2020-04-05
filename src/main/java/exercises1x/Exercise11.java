package exercises1x;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import utils.flatzone.FlatZone;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement4;
import utils.structuring.StructuringElement8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercise11 {
  /**
   * Exercises that computes the flat zone of a given pixel.
   * <p>
   * Parameters are:
   * config.txt -> configuration file with the initial pixel.
   * input.pgm -> input image.
   * output.pgm -> output image with the flat zone.
   */
  public static void main(String[] args) {
    try {
      if (args.length != 3) {
        throw new IllegalArgumentException("Error: number of arguments is 3.");
      }

      BufferedReader reader = new BufferedReader(new FileReader(args[0]));

      // Configuration file is read with the desired format.
      int x = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
      int y = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
      int connectivity = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));

      // flat zone label is initialized and checked.
      int flatZoneLabel = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
      if (flatZoneLabel <= 0 || flatZoneLabel > 255) {
        throw new IllegalArgumentException("Flat zone label must be greater than zero and less than 255.");
      }

      // Structuring element is initialized.
      StructuringElement element;
      if (connectivity == 4) {
        element = new StructuringElement4(1);
      } else if (connectivity == 8) {
        element = new StructuringElement8(1);
      } else {
        throw new IllegalArgumentException("Connectivity can only be 4 or 8");
      }

      GrayU8 inputImage = Utils.readPGMImage(args[1]);
      // Flat zone is computed with input image.
      FlatZone flatZone = new FlatZone(flatZoneLabel);
      GrayU8 outputImage = flatZone.compute(inputImage, x, y, element);
      Utils.saveImage(outputImage, args[2]);

    } catch (IOException e) {
      System.err.println("Error: error reading the images or configuration file.");
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
