package exercise3;

import boofcv.struct.image.GrayU8;
import utils.Filter;
import utils.FilterOperation;
import utils.Utils;

import java.io.IOException;

public class Exercise3b {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error: program expects three arguments");
            return;
        }
        try {
            int size = Integer.parseInt(args[0]);
            GrayU8 inputImage = Utils.readPGMImage(args[1]);

            GrayU8 resultImage = Filter.applyFilterUsingElementSize(inputImage, size, FilterOperation.DILATION);
            Utils.saveImage(resultImage, args[2]);
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
