package utils.segmentation;

import boofcv.struct.image.GrayU8;
import utils.structuring.StructuringElement;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Segmentation {
  public static int BACKGROUND_VALUE = 0;
  public static int REGION_VALUE = 255;

  public static ArrayList<Region> grassFireSegmentation(GrayU8 image, StructuringElement element) {
    ArrayList<Region> regions = new ArrayList<>();
    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        if (positionIsRegion(image, x, y) && !positionIsInRegions(x, y, regions)) {
          PositionQueue queue = new PositionQueue();
          Region region = new Region();
          queue.push(x, y);

          while (!queue.isEmpty()) {
            Position currentPosition = queue.pop();
            region.addPosition(currentPosition);

            element.mapNeighbours(image, currentPosition.getX(), currentPosition.getY(), (x1, y1) -> {
              if (!(x1 == currentPosition.getX() && y1 == currentPosition.getY())
                      && positionIsRegion(image, x1, y1)
                      && !queue.isInQueue(x1, y1)
                      && !region.isInRegion(x1, y1)) {
                queue.push(x1, y1);
              }
            });
          }
          regions.add(region);
        }
      }
    }
    return regions;
  }

  public static BufferedImage getImageWithRegions(GrayU8 inputImage, ArrayList<Region> regions) {
    BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (Region region : regions) {
      region.mapPositions((x, y) -> {
        outputImage.setRGB(x, y, region.getColor().getRGB());
      });
    }
    return outputImage;
  }

  public static boolean positionIsInRegions(int x, int y, ArrayList<Region> regions) {
    return positionIsInRegions(new Position(x, y), regions);
  }

  public static boolean positionIsInRegions(Position position, ArrayList<Region> regions) {
    AtomicBoolean isInRegions = new AtomicBoolean(false);
    for (Region region : regions) {
      region.mapPositions(((x, y) -> {
        if (x == position.getX() && y == position.getY()) {
          isInRegions.set(true);
        }
      }));
      if (isInRegions.get()) {
        break;
      }
    }
    return isInRegions.get();
  }

  public static boolean positionIsBackground(GrayU8 image, int x, int y) {
    return image.get(x, y) == BACKGROUND_VALUE;
  }

  public static boolean positionIsRegion(GrayU8 image, int x, int y) {
    return !positionIsBackground(image, x, y);
  }
}
