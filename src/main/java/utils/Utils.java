package utils;

import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utils {

    public static GrayU8 readPGMImage(String path) throws IOException {
        BufferedImage img = UtilImageIO.loadImage(path);
        GrayU8 grayImage = new GrayU8(img.getWidth(), img.getHeight());
        UtilImageIO.loadPGM_U8(path, grayImage);
        return grayImage;
    }

    public static void saveImage(GrayU8 image, String path) throws IOException {
        UtilImageIO.savePGM(image, path);
    }

    public static boolean areEqual(GrayU8 firstImage, GrayU8 secondImage) {
        // If images do not have the same size they are not equal.
        if (firstImage.height != secondImage.height && firstImage.width != secondImage.width) {
            return false;
        }
        int width = firstImage.width, height = firstImage.height;

        // Loop over the entire image to see if they are equal.
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (firstImage.get(i, j) != secondImage.get(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
