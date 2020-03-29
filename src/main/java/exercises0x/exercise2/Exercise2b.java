package exercises0x.exercise2;

import boofcv.struct.image.GrayU8;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;

public class Exercise2b {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.err.println("Error: program expects three arguments");
      return;
    }
    try {
      GrayU8 firstImage = Utils.readPGMImage(args[0]);
      GrayU8 secondImage = Utils.readPGMImage(args[1]);

      FileWriter writer = new FileWriter(args[2]);

      if (Utils.areEqual(firstImage, secondImage)) {
        writer.write("=");
        System.out.println("=");
      } else {
        writer.write("!=");
        System.out.println("!=");
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      System.err.println("Error reading the images.");
      e.printStackTrace();
    }
  }
}
