package utils.structuring;

import boofcv.struct.image.GrayU8;

public class StructuringElement8 extends StructuringElement {
  public StructuringElement8(int size) {
    super(size);
  }

  public StructuringElement8(int width, int height) {
    super(width, height);
  }

  @Override
  public void mapNeighbours(GrayU8 image, int x, int y, StructuringElementCallback callback) {
    for (int i = 0; i < this.getWidth(); i++) {
      for (int j = 0; j < this.getHeight(); j++) {
        int computedX = i <= this.getCenterX() ? x - (this.getCenterX() - i) : x + (i - this.getCenterX());
        int computedY = j <= this.getCenterY() ? y - (this.getCenterY() - j) : y + (j - this.getCenterY());

        if (this.testIfPositionIsCorrect(image, computedX, computedY)) {
          callback.execute(computedX, computedY);
        }
      }
    }
  }
}
