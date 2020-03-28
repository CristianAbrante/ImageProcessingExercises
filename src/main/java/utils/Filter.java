package utils;

import boofcv.struct.image.GrayU8;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement8;

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

  public static GrayU8 applyFilterUsingElementSize(GrayU8 image, int size, FilterOperation operation) {
    StructuringElement element = new StructuringElement8(1);
    GrayU8 outputImage = image;
    for (int i = 0; i < size; i++) {
      outputImage = applyFilter(outputImage, element, operation);
    }
    return outputImage;
  }

  private static ArrayList<Integer> computeNeighbours(GrayU8 image, int x, int y, StructuringElement element) {
    ArrayList<Integer> neighbours = new ArrayList<>();
    element.mapNeighbours(image, x, y, (x1, y1) ->
            neighbours.add(image.get(x1, y1)));
    return neighbours;
  }
}
