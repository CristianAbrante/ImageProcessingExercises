package utils.segmentation.fast;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;

import java.util.ArrayList;
import java.util.List;

public class DirectionMatrix {
  private List<List<Direction>> directions;

  public DirectionMatrix(GrayU8 image) {
    this.directions = new ArrayList<>();

    for (int i = 0; i < image.getWidth(); i++) {
      this.directions.add(new ArrayList<>());
      for (int j = 0; j < image.getHeight(); j++) {
        this.directions.get(i).add(null);
      }
    }
  }

  public boolean hasDirection(int x, int y) {
    return this.hasDirection(new Position(x, y));
  }

  public boolean hasDirection(Position position) {
    return this.getDirection(position) != null;
  }

  public void setDirection(int x, int y, Direction direction) {
    this.setDirection(new Position(x, y), direction);
  }

  public void setDirection(Position pos, Direction direction) {
    this.directions.get(pos.getX()).set(pos.getY(), direction);
  }

  public Direction getDirection(int x, int y) {
    return this.getDirection(new Position(x, y));
  }

  public Direction getDirection(Position pos) {
    return this.directions.get(pos.getX()).get(pos.getY());
  }
}
