package exercise1;

import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import utils.Utils;

import java.io.IOException;

public class Exercise1a {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error: program expects three arguments");
            return;
        }
        try {
            GrayU8 inputImage = Utils.readPGMImage(args[0]);
            int threshold = Integer.parseInt(args[1]);

            if (threshold < 0 || threshold > 255) {
                System.err.println("Error: Threshold value should be between 0 and 255.");
                return;
            }

            GrayU8 outputImage = new GrayU8(inputImage.getWidth(), inputImage.getHeight());

            for (int i = 0; i < inputImage.getWidth(); i++) {
                for (int j = 0; j < inputImage.getHeight(); j++) {
                    if (inputImage.get(i, j) >= threshold) {
                        outputImage.set(i, j, 255);
                    } else {
                        outputImage.set(i, j, 0);
                    }
                }
            }

            Utils.saveImage(outputImage, args[2]);
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
