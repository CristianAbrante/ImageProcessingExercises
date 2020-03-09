package utils;

import boofcv.struct.image.GrayU8;

import java.io.IOException;

public class Idempotence {
    public static boolean proveIdempotence(GrayU8 image, int times, String outputPath, IdempotenceOperation operation) {
        if (times < 0 || times > 10) {
            System.err.println("Error: times should be between 0 and 10");
        }
        GrayU8 prevImage = image;
        GrayU8 nextImage = null;

        int currentTime = 0;
        try {
            while (currentTime < times) {
                nextImage = operation.operation(prevImage);
                Utils.saveImage(nextImage, outputPath + "-" + times + ".pgm");
                if (!Utils.areEqual(prevImage, nextImage)) {
                    return false;
                }
                prevImage = nextImage;
                currentTime += 1;
            }
            return true;
        }catch (IOException e) {
            System.err.println("Error: error opening the output folder");
            return false;
        }
    }
}
