package utils.structuring;

import boofcv.struct.image.GrayU8;

public class StructuringElement4 extends StructuringElement {
  public StructuringElement4(int size) {
    super(size);
  }

  public StructuringElement4(int width, int height) {
    super(width, height);
  }

  @Override
  public void map(GrayU8 image, int x, int y, StructuringElementCallback callback) {
    for (int i = 0; i < this.getWidth(); i++) {
      int computedX = i <= this.getCenterX() ? x - (this.getCenterX() - i) : x + (i - this.getCenterX());
      if (this.testIfPositionIsCorrect(image, computedX, y)) {
        callback.execute(computedX, y);
      }
    }

    for (int j = 0; j < this.getHeight(); j++) {
      int computedY = j <= this.getCenterY() ? y - (this.getCenterY() - j) : y + (j - this.getCenterY());
      if (this.testIfPositionIsCorrect(image, x, computedY)) {
        callback.execute(x, computedY);
      }
    }
  }
}
