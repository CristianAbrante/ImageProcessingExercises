package utils;

import boofcv.struct.image.GrayU8;
import java.util.ArrayList;

public class Filter {
    public static GrayU8 applyFilter(GrayU8 image, StructuringElement element, FilterOperation operation) {
        GrayU8 resultImage = new GrayU8(image.getWidth(), image.getHeight());
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                ArrayList<Integer> neighbours = computeNeighbours(image, i, j, element);
                int value = operation.computeOperation(neighbours);
                resultImage.set(i, j, value);
            }
        }
        return resultImage;
    }

    private static ArrayList<Integer> computeNeighbours(GrayU8 image, int x, int y, StructuringElement element) {
        ArrayList<Integer> neighbours = new ArrayList<Integer>();

        int elementCenterX = element.getCenterX();
        int elementCenterY = element.getCenterY();

        for (int i = 0; i < element.getWidth(); i++) {
            for (int j = 0; j < element.getHeight(); j++) {
                int computedX = i <= elementCenterX ? x - (elementCenterX - i) : x + (i - elementCenterX);
                int computedY = j <= elementCenterY ? y - (elementCenterY - j) : y + (j - elementCenterY);

                if (computedX >= 0 && computedX < image.getWidth() && computedY >= 0 && computedY < image.getHeight()) {
                    neighbours.add(image.get(computedX, computedY));
                }
            }
        }
        return neighbours;
    }
}
