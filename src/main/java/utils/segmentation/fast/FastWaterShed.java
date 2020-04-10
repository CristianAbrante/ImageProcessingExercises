package utils.segmentation.fast;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;
import utils.structuring.StructuringElement;
import utils.structuring.StructuringElement8;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class FastWaterShed {
  List<Position> localMinimums;
  DirectionMatrix directionMatrix;


  public List<WaterShed> compute(GrayU8 image) {
    this.directionMatrix = new DirectionMatrix(image);
    localMinimums = new ArrayList<>();
    StructuringElement element = new StructuringElement8(1);

    for (int x = 0; x < image.getWidth(); x++) {
      for (int y = 0; y < image.getHeight(); y++) {
        computePixelDirection(image, new Position(x, y), element);
      }
    }

    System.out.println("Direction matrix: > ");
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        System.out.print(this.directionMatrix.getDirection(j, i));
        System.out.print(" ");
      }
      System.out.println();
    }

    return WaterShed.constructWaterSheds(image, this.directionMatrix, this.localMinimums);
  }


  private void computePixelDirection(GrayU8 image, Position position, StructuringElement element) {
    // We continue if position has not been computed
    if (!this.directionMatrix.hasDirection(position)) {
      Position bestNeighbour = findBestNeighbour(image, position, element);

      // In this case, there is best neighbour, so we compute the direction of it.
      if (bestNeighbour != null) {
        int currentGrayLevel = image.get(position.getX(), position.getY());
        int bestNeighbourGrayLevel = image.get(bestNeighbour.getX(), bestNeighbour.getY());

        if (bestNeighbourGrayLevel == currentGrayLevel) {
          // In this case we should explore the plateau.
          explorePlateau(image, bestNeighbour, element);
        } else {
          // In this case we should add the direction to the direction matrix.
          Direction direction = Direction.computeDirection(position, bestNeighbour);
          this.directionMatrix.setDirection(position, direction);
        }
      }
      // In this case the position is a local minimum, so we add it to the list.
      else {
        this.directionMatrix.setDirection(position, Direction.CENTER);
        this.localMinimums.add(position);
      }
    }
  }

  private Position findBestNeighbour(GrayU8 image, Position position, StructuringElement element) {
    AtomicReference<Position> bestNeighbour = new AtomicReference<>(null);
    int currentGrayLevel = image.get(position.getX(), position.getY());
    AtomicInteger bestNeighbourGrayLevel = new AtomicInteger(currentGrayLevel);

    element.mapNeighbours(image, position.getX(), position.getY(), (x, y) -> {
      int neighbourGrayLevel = image.get(x, y);

      if (!(position.getX() == x && position.getY() == y)
              && neighbourGrayLevel <= currentGrayLevel
              && neighbourGrayLevel <= bestNeighbourGrayLevel.get()) {
        bestNeighbour.set(new Position(x, y));
        bestNeighbourGrayLevel.set(neighbourGrayLevel);
      }
    });

    return bestNeighbour.get();
  }

  private void explorePlateau(GrayU8 image, Position initialPosition, StructuringElement element) {
    AtomicReference<Position> bestNeighbour = new AtomicReference<>(null);
    AtomicInteger bestNeighbourGrayLevel = new AtomicInteger(Integer.MAX_VALUE);
    int plateauGrayLevel = image.get(initialPosition.getX(), initialPosition.getY());

    List<Position> plateauPositions = new ArrayList<>();
    List<Position> exploredPositions = new ArrayList<>();
    Queue<Position> queue = new ArrayDeque<>();

    exploredPositions.add(initialPosition);
    plateauPositions.add(initialPosition);
    queue.add(initialPosition);

    // The first part is the BFS used to explore the plateau.
    while (!queue.isEmpty()) {
      Position currentPosition = queue.remove();


      element.mapNeighbours(image, currentPosition, (x, y) -> {
        Position neighbour = new Position(x, y);
        int neighbourGrayLevel = image.get(x, y);

        // If neighbour is not already explored
        if (!currentPosition.equals(neighbour) && !exploredPositions.contains(neighbour)) {
          // This is the case of a neighbour with less gray level than current
          // which is promising.
          if (neighbourGrayLevel < plateauGrayLevel
                  && neighbourGrayLevel < bestNeighbourGrayLevel.get()) {
            bestNeighbour.set(neighbour);
            bestNeighbourGrayLevel.set(neighbourGrayLevel);
            // in this case, we have another member of the plateau.
          } else if (neighbourGrayLevel == plateauGrayLevel) {
            plateauPositions.add(neighbour);
            queue.add(neighbour);
          }

          exploredPositions.add(neighbour);
        }
      });
    }

    // In this case we have a non minimum plateau.
    if (bestNeighbour.get() != null) {
      setPlateauValue(image, element, plateauPositions, bestNeighbour.get());
    }
    // In this case we have a minimum plateau.
    else {
      this.localMinimums.add(initialPosition);
      setPlateauValue(image, element, plateauPositions, initialPosition);
    }
  }

  private void setPlateauValue(GrayU8 image, StructuringElement element, List<Position> plateau, Position minimum) {
    for (Position position : plateau) {
      if (!this.directionMatrix.hasDirection(position)) {
        BFS(image, element, position, minimum);
      }
    }
  }

  private void BFS(GrayU8 image, StructuringElement element, Position initialPosition, Position objective) {
    List<Path> paths = new ArrayList<>();
    List<Position> visitedPositions = new ArrayList<>();
    Path shortestPath = null;
    int plateauGrayLevel = image.get(initialPosition.getX(), initialPosition.getY());

    Path initialPath = new Path();
    initialPath.addToPath(initialPosition);
    paths.add(initialPath);
    Queue<Position> queue = new ArrayDeque<>();
    queue.add(initialPosition);
    visitedPositions.add(initialPosition);

    while (!queue.isEmpty() && shortestPath == null) {
      Position currentPosition = queue.remove();
      int currentGrayLevel = image.get(currentPosition.getX(), currentPosition.getY());

      if (currentPosition.equals(objective) ||
              this.directionMatrix.hasDirection(currentPosition)) {
        for (Path path : paths) {
          if (path.last().equals(currentPosition)) {
            shortestPath = path;
          }
        }
      } else {
        element.mapNeighbours(image, currentPosition, (x, y) -> {
          Position neighbour = new Position(x, y);
          int neighbourGrayLevel = image.get(neighbour.getX(), neighbour.getY());

          if ((neighbourGrayLevel == currentGrayLevel && !visitedPositions.contains(neighbour))
                  || neighbour.equals(objective)) {
            List<Path> newPaths = new ArrayList<>();
            for (Path path : paths) {
              if (path.last().equals(currentPosition)) {
                Path newPath = path.getSubPath(0);
                newPath.addToPath(neighbour);
                newPaths.add(newPath);
              }
            }
            paths.addAll(newPaths);
            queue.add(neighbour);
            visitedPositions.add(neighbour);
          }
        });
      }
    }

    if (shortestPath != null) {
      for (int i = 0; i < shortestPath.size(); i++) {
        Position position = shortestPath.get(i);
        int currentGrayLevel = image.get(position.getX(), position.getY());

        if (i + 1 == shortestPath.size()) {
          if (currentGrayLevel == plateauGrayLevel && !this.directionMatrix.hasDirection(position)) {
            this.directionMatrix.setDirection(position, Direction.CENTER);
          }
        } else {
          Position nextPosition = shortestPath.get(i + 1);
          Direction direction = Direction.computeDirection(position, nextPosition);
          this.directionMatrix.setDirection(position, direction);
        }
      }
    }
  }
}
