package utils.segmentation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Region {
  private ArrayList<Position> positions;
  private Color color;

  public Region() {
    this.positions = new ArrayList<>();

    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    this.color = new Color(r, g, b);
  }

  public void addPosition(int x, int y) {
    this.addPosition(new Position(x, y));
  }

  public void addPosition(Position position) {
    this.positions.add(position);
  }

  public void mapPositions(RegionCallback callback) {
    for (Position position : this.positions) {
      callback.execute(position.getX(), position.getY());
    }
  }

  public Color getColor() {
    return this.color;
  }

  public boolean isInRegion(int x, int y) {
    return this.isInRegion(new Position(x, y));
  }

  public boolean isInRegion(Position position) {
    for (Position position1 : this.positions) {
      if (position1.equals(position)) {
        return true;
      }
    }
    return false;
  }
}
