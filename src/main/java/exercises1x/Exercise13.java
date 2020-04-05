package exercises1x;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import utils.flatzone.FlatZone;
import utils.flatzone.FlatZoneExtreme;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement4;
import utils.structuring.StructuringElement8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Exercise13 {
  /**
   * Exercises that computes if the flat zone is an extreme (maximum or minimum).
   * <p>
   * Parameters are:
   * minimum/maximum -> test if operation is a maximum or minimum.
   * config.txt -> configuration file with the initial pixel.
   * input.pgm -> input image.
   * output.txt -> file where there will be a 1 if flat zone is extreme and 0 otherwise.
   */
  public static void main(String[] args) {
    try {
      if (args.length != 4) {
        throw new IllegalArgumentException("Error: number of arguments is 3.");
      }

      FlatZoneExtreme extreme = FlatZoneExtreme.of(args[0]);

      BufferedReader reader = new BufferedReader(new FileReader(args[1]));

      // Configuration file is read with the desired format.
      int x = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
      int y = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));
      int connectivity = Integer.parseInt(reader.readLine().replaceAll("\\s+", ""));

      // Structuring element is initialized.
      StructuringElement element;
      if (connectivity == 4) {
        element = new StructuringElement4(1);
      } else if (connectivity == 8) {
        element = new StructuringElement8(1);
      } else {
        throw new IllegalArgumentException("Connectivity can only be 4 or 8");
      }

      GrayU8 inputImage = Utils.readPGMImage(args[2]);
      // Flat zone is computed with input image.
      FlatZone flatZone = new FlatZone();

      boolean regionIsExtreme = flatZone.isExtreme(inputImage, x, y, element, extreme);
      FileWriter writer = new FileWriter(args[3]);

      if (regionIsExtreme) {
        writer.write("1");
        System.out.println("1");
      } else {
        writer.write("0");
        System.out.println("0");
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      System.err.println("Error: error reading the images or configuration file.");
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
