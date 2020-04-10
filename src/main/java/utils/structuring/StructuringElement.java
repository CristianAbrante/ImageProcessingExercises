package utils.structuring;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;

import java.util.ArrayList;

abstract public class StructuringElement {
  int width;
  int height;

  public StructuringElement(int size) {
    if (size < 0) {
      throw new IllegalArgumentException("Error: size must be zero or greater.");
    }
    int side = 2 * size + 1;
    setWidth(side);
    setHeight(side);
  }

  public StructuringElement(int width, int height) {
    setWidth(width);
    setHeight(height);
  }

  public int getCenterX() {
    return this.getCenter(this.widthIsEven(), this.getWidth());
  }

  public int getCenterY() {
    return this.getCenter(this.heightIsEven(), this.getHeight());
  }

  private boolean widthIsEven() {
    return this.measureIsEven(this.getWidth());
  }

  private boolean heightIsEven() {
    return this.measureIsEven(this.getHeight());
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    if (width <= 0) {
      throw new IllegalArgumentException("Error: width must be greater than zero.");
    }
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    if (height <= 0) {
      throw new IllegalArgumentException("Error: height must be greater than zero.");
    }
    this.height = height;
  }

  public ArrayList<Integer> getNeighboursValues(GrayU8 image, int x, int y) {
    ArrayList<Integer> neighbourValues = new ArrayList<>();
    this.mapNeighbours(image, x, y, (x1, y1) -> neighbourValues.add(image.get(x1, y1)));
    return neighbourValues;
  }

  public ArrayList<Position> getNeighboursPositions(GrayU8 image, int x, int y) {
    ArrayList<Position> neighbourValues = new ArrayList<>();
    this.mapNeighbours(image, x, y, (x1, y1) -> neighbourValues.add(new Position(x1, y1)));
    return neighbourValues;
  }

  public void mapNeighbours(GrayU8 image, Position position, StructuringElementCallback callback) {
    this.mapNeighbours(image, position.getX(), position.getY(), callback);
  }

  public abstract void mapNeighbours(GrayU8 image, int x, int y, StructuringElementCallback callback);

  protected boolean testIfPositionIsCorrect(GrayU8 image, int x, int y) {
    return x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight();
  }

  private boolean measureIsEven(int measure) {
    return measure % 2 == 0;
  }

  private int getCenter(boolean even, int measure) {
    return even ? (measure / 2) - 1 : (measure - 1) / 2;
  }
}
