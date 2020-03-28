package utils.structuring;

import boofcv.struct.image.GrayU8;

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

  public abstract void map(GrayU8 image, int x, int y, StructuringElementCallback callback);

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
