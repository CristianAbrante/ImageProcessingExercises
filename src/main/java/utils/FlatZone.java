package utils;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;
import utils.structuring.StructuringElement;

import java.util.ArrayDeque;
import java.util.Queue;

public class FlatZone {
  public static final int DEFAULT_BACKGROUND_VALUE = 0;
  public static final int DEFAULT_FLAT_ZONE_VALUE = 255;
  private final int outputFlatZoneValue;

  public FlatZone(int outputFlatZoneValue) {
    this.outputFlatZoneValue = outputFlatZoneValue;
  }

  public FlatZone() {
    this(DEFAULT_FLAT_ZONE_VALUE);
  }

  public int getOutputFlatZoneValue() {
    return outputFlatZoneValue;
  }

  public GrayU8 compute(GrayU8 image, int x, int y, StructuringElement element) {
    return this.compute(image, new Position(x, y), element);
  }

  public GrayU8 compute(GrayU8 image, Position pos, StructuringElement element) {
    GrayU8 outputImage = new GrayU8(image.getWidth(), image.getHeight());

    // Output image is set with the background value
    for (int x = 0; x < pos.getX(); x++) {
      for (int y = 0; y < pos.getY(); y++) {
        outputImage.set(x, y, DEFAULT_BACKGROUND_VALUE);
      }
    }
    // Position is set as flat zone in the output image
    outputImage.set(pos.getX(), pos.getY(), this.getOutputFlatZoneValue());

    Queue<Position> flatZone = new ArrayDeque<>();
    flatZone.add(pos);

    while (!flatZone.isEmpty()) {
      Position currentPosition = flatZone.remove();
      element.mapNeighbours(image, currentPosition.getX(), currentPosition.getY(), (x, y) -> {
        // if neighbour have the same gray value as the current position and
        // is not already in the output image it is added to the queue.
        if ((image.get(x, y) == image.get(currentPosition.getX(), currentPosition.getY()))
                && (outputImage.get(x, y) != this.getOutputFlatZoneValue())) {
          outputImage.set(x, y, this.getOutputFlatZoneValue());
          flatZone.add(new Position(x, y));
        }
      });
    }

    return outputImage;
  }
}
