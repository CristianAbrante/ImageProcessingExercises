package utils;

import boofcv.struct.image.GrayU8;

public class LatticeOperator {
    public static GrayU8 applyOperation(GrayU8 image1, GrayU8 image2, LatticeOperation operation) {
        if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
            throw new IllegalArgumentException("Error: images must have the same size.");
        }
        int width = image1.getWidth(), height = image1.getHeight();
        GrayU8 resultImage = new GrayU8(width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                resultImage.set(i, j, operation.getOperationResult(image1.get(i, j), image2.get(i, j)));
            }
        }

        return resultImage;
    }
}
