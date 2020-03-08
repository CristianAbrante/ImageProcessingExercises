package exercise1;

import boofcv.struct.image.GrayU8;
import utils.Utils;
import java.io.IOException;

public class Exercise1b {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: program expects two arguments");
            return;
        }
        try {
            GrayU8 firstImage = Utils.readPGMImage(args[0]);
            GrayU8 secondImage = Utils.readPGMImage(args[1]);

            if (Utils.areEqual(firstImage, secondImage)) {
                System.out.println("=");
            } else {
                System.out.println("!=");
            }
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
