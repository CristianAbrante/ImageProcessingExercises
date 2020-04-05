package utils.flatzone;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;
import utils.structuring.StructuringElement;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

interface ComputationCallback {
  public void call(GrayU8 inputImage, GrayU8 outputImage, Queue<Position> queue, Position position);
}

interface ExtremeZoneCallback {
  public boolean call(int flatZoneGrayLevel, int currentPositionGrayLevel);
}

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
    return this.computeFlatZoneWithOptions(image, pos, element, null);
  }

  public boolean isMinimumZone(GrayU8 image, int x, int y, StructuringElement element) {
    return this.isMinimumZone(image, new Position(x, y), element);
  }

  public boolean isMinimumZone(GrayU8 image, Position pos, StructuringElement element) {
    return this.isExtreme(image, pos, element, FlatZoneExtreme.MINIMUM);
  }

  public boolean isMaximumZone(GrayU8 image, int x, int y, StructuringElement element) {
    return this.isMaximumZone(image, new Position(x, y), element);
  }

  public boolean isMaximumZone(GrayU8 image, Position pos, StructuringElement element) {
    return this.isExtreme(image, pos, element, FlatZoneExtreme.MAXIMUM);
  }

  public boolean isExtreme(GrayU8 image, int x, int y, StructuringElement element, FlatZoneExtreme extremeType) {
    return this.isExtreme(image, new Position(x, y), element, extremeType);
  }

  public boolean isExtreme(GrayU8 image, Position pos, StructuringElement element, FlatZoneExtreme extremeType) {
    int flatZoneGrayLevel = image.get(pos.getX(), pos.getY());
    AtomicBoolean extreme = new AtomicBoolean(true);

    this.computeFlatZoneWithOptions(image, pos, element, (inputImage, outputImage, queue, position) -> {
      if (!extremeType.isRegionExtreme(flatZoneGrayLevel, image.get(position.getX(), position.getY()))) {
        extreme.set(false);
      }
    });
    return extreme.get();
  }

  private GrayU8 computeFlatZoneWithOptions(GrayU8 image, Position pos, StructuringElement element, ComputationCallback callback) {
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
        // It means that the element have not been explored
        if (outputImage.get(x, y) != this.getOutputFlatZoneValue()) {
          if (image.get(x, y) == image.get(currentPosition.getX(), currentPosition.getY())) {
            outputImage.set(x, y, this.getOutputFlatZoneValue());
            flatZone.add(new Position(x, y));
          }
          if (callback != null) {
            callback.call(image, outputImage, flatZone, new Position(x, y));
          }
        }
      });
    }

    return outputImage;
  }
}
