package utils.segmentation.fast;

import utils.segmentation.Position;

public enum Direction {
  LEFT_TOP(-1, -1, "LT"),
  LEFT_CENTER(-1, 0, "<-"),
  LEFT_BOTTOM(-1, 1, "LD"),
  CENTER_TOP(0, -1, "CT"),
  CENTER(0, 0, "o"),
  CENTER_BOTTOM(0, 1, "CD"),
  RIGHT_TOP(1, -1, "RT"),
  RIGHT_CENTER(1, 0, "->"),
  RIGHT_BOTTOM(1, 1, "RD");

  private int xDifference;
  private int yDifference;
  private String representation;

  Direction(int xDifference, int yDifference, String representation) {
    this.xDifference = xDifference;
    this.yDifference = yDifference;
    this.representation = representation;
  }

  public boolean isDirection(int currentXDifference, int currentYDifference) {
    return this.xDifference == currentXDifference && this.yDifference == currentYDifference;
  }

  public Position followDirection(Position currentPosition) {
    return new Position(currentPosition.getX() + this.xDifference, currentPosition.getY() + this.yDifference);
  }

  public static Direction computeDirection(int pixelX, int pixelY, int neighbourX, int neighbourY) {
    return Direction.computeDirection(new Position(pixelX, pixelY), new Position(neighbourX, neighbourY));
  }

  @Override
  public String toString() {
    return this.representation;
  }

  public static Direction computeDirection(Position pixelPosition, Position neighbourPosition) {
    int currentXDifference = neighbourPosition.getX() - pixelPosition.getX();
    int currentYDifference = neighbourPosition.getY() - pixelPosition.getY();

    for (Direction direction : Direction.values()) {
      if (direction.isDirection(currentXDifference, currentYDifference)) {
        return direction;
      }
    }
    return Direction.CENTER;
  }
}