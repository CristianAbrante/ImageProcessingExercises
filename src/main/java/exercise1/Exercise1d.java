package exercise1;

import boofcv.struct.image.GrayU8;
import utils.Utils;

import java.io.IOException;

enum OperationType {
    SUPREMUM, INFIMUM;

    public static OperationType of(String code) {
        for (OperationType type : OperationType.values()) {
            if (type.toString().equals(code.toUpperCase())) {
                return type;
            }
        }
        throw new IllegalArgumentException("Error: code is not a valid operation type.");
    }
}

public class Exercise1d {

    public static int getOperationResult(GrayU8 image1, GrayU8 image2, int x, int y, OperationType operationType) {
        switch (operationType) {
            case INFIMUM:
                return Math.min(image1.get(x, y), image2.get(x, y));
            case SUPREMUM:
                return Math.max(image1.get(x, y), image2.get(x, y));
        }
        return 0;
    }

    public static GrayU8 computeOperation(GrayU8 image1, GrayU8 image2, OperationType operationType) {
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("Error: images must have the same size.");
        }
        int width = image1.getWidth(),height = image1.getHeight();
        GrayU8 resultImage = new GrayU8(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                resultImage.set(i, j, getOperationResult(image1, image2, i, j, operationType));
            }
        }

        return resultImage;
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            System.err.println("Error: program expects four arguments");
            return;
        }
        try {
            GrayU8 firstImage = Utils.readPGMImage(args[1]);
            GrayU8 secondImage = Utils.readPGMImage(args[2]);
            GrayU8 outputImage = computeOperation(firstImage,secondImage, OperationType.of(args[0]));

            Utils.saveImage(outputImage, args[3]);
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
