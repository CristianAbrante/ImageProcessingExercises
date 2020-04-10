package segmentation;

import boofcv.struct.image.GrayU8;
import utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class imageGenerator {
  public static void main(String[] args) throws IOException {
    GrayU8 image = new GrayU8(10, 10);

    BufferedReader reader = new BufferedReader(new FileReader("test-images/segmentation/test-image.txt"));

    for (int i = 0; i < 10; i++) {
      String line = reader.readLine();
      String[] pixels = line.split("\\s+");
      for (int j = 0; j < pixels.length; j++) {
        System.out.print(pixels[j]);
        System.out.print(" ");
        if (pixels[j].length() != 0) {
          image.set(j, i, Integer.parseInt(pixels[j]));
        }
      }
      System.out.println("\n");
    }

    for (int i = 0; i < image.width; i++) {
      for (int j = 0; j < image.height; j++) {
        System.out.print(image.get(i, j));
        System.out.print(" ");
      }
      System.out.println();
    }

    Utils.saveImage(image, "out/test.pgm");
  }
}
