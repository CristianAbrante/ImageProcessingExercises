package utils.segmentation.fast;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;
import utils.segmentation.Region;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement8;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FastWaterShed {
  ImagePaths paths;
  List<Position> localMinimums;
  List<Region> plateaus;

  public void compute(GrayU8 image) {
    localMinimums = new ArrayList<>();
    paths = new ImagePaths(image.getWidth(), image.getHeight());
    StructuringElement element = new StructuringElement8(1);

    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        processPixel(image, x, y, element);
      }
    }
  }

  public Position findBetterNeighbour(GrayU8 image, int x, int y, StructuringElement element) {
    AtomicReference<Position> minPosition = null;
    int currentGrayLevel = image.get(x, y);

    element.mapNeighbours(image, x, y, (x1, y1) -> {
      if (!(x == x1 && y == y1) && image.get(x1, y1) < currentGrayLevel) {
        minPosition.set(new Position(x1, y1));
      }
    });

    return minPosition.get();
  }

  public void processPixel(GrayU8 image, int x, int y, StructuringElement element) {
    Stack<Position> stack = new Stack<>();
    stack.push(new Position(x, y));
    Path path = new Path();

    while (!stack.empty()) {
      Position currentPosition = stack.pop();

      if (!paths.isChecked(currentPosition)) {
        path.addToPath(currentPosition);
        Position bestNeighbour = findBetterNeighbour(image, x, y, element);

        // in the case there is a better neighbour
        if (bestNeighbour != null) {
          stack.push(bestNeighbour);
        }
        // This is the difficult case, either is a minimum
        // or a plateau
        else {
          // in this case we are testing if it is a local minimum
          if (isLocalMinimum(image, currentPosition, element)) {
            localMinimums.add(currentPosition);
          } else {

          }
        }
      } else {
        path.appendPath(paths.getPath(currentPosition));
      }
    }
    paths.setPath(x, y, path);
  }


  public boolean isLocalMinimum(GrayU8 image, Position position, StructuringElement element) {
    AtomicBoolean allNeighboursAreGreater = new AtomicBoolean(true);
    int currentGrayLevel = image.get(position.getX(), position.getY());

    element.mapNeighbours(image, position.getX(), position.getY(), (x, y) -> {
      if (!(x == position.getX() && y == position.getY()) && image.get(x, y) <= currentGrayLevel) {
        allNeighboursAreGreater.set(false);
      }
    });
    return allNeighboursAreGreater.get();
  }

  public Path explorePlateau(GrayU8 image, Position position, StructuringElement element) {
    Queue<Path> queue = new ArrayDeque<>();
    List<Path> minimumPaths = new ArrayList<>();

    int plateauGrayLevel = image.get(position.getX(), position.getY());
    Path initialPath = new Path();
    initialPath.addToPath(position);
    queue.add(initialPath);

    while (!queue.isEmpty()) {
      Path currentPath = queue.remove();
      Position last = currentPath.last();
      int currentGrayLevel = image.get(last.getX(), last.getY());

      if (currentGrayLevel < plateauGrayLevel) {
        minimumPaths.add(currentPath);
      } else if (currentGrayLevel == plateauGrayLevel) {
        element.mapNeighbours(image, last.getX(), last.getY(), (x, y) -> {
          if (!(x == last.getX() && y == last.getY()) && !currentPath.havePosition(x, y)) {
            Path newPath = new Path();
            for (Position currentPathPosition : currentPath) {
              newPath.addToPath(currentPathPosition);
            }
            newPath.addToPath(new Position(x, y));
            queue.add(newPath);
          }
        });
      }
    }

    // in this case there is a minimum pixel in the edge of the
    // plateau
    if (minimumPaths.size() > 0) {
      int minElementIndex = 0;
      int minElementValue = -1;
      for (int i = 0; i < minimumPaths.size(); i++) {
        Path currentPath = minimumPaths.get(i);
        Position last = currentPath.last();
        int lastGrayValue = image.get(last.getX(), last.getY());
        if (lastGrayValue < minElementValue) {
          minElementIndex = i;
          minElementValue = lastGrayValue;
        }
      }
      return minimumPaths.get(minElementIndex);
    } else {

    }
    return null;
  }
}
