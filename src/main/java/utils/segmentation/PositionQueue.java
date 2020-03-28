package utils.segmentation;

import java.util.ArrayList;

public class PositionQueue {
  private ArrayList<Position> positions;

  public PositionQueue() {
    this.positions = new ArrayList<>();
  }

  public void push(Position position) {
    this.positions.add(position);
  }

  public void push(int x, int y) {
    this.push(new Position(x, y));
  }

  public Position pop() {
    if (this.positions.size() > 0) {
      return this.positions.remove(0);
    }
    throw new IllegalArgumentException("Trying to pop in an empty queue.");
  }

  public boolean isEmpty() {
    return this.positions.isEmpty();
  }

  public boolean isInQueue(Position pos) {
    for (Position position : this.positions) {
      if (position.equals(pos)) {
        return true;
      }
    }
    return false;
  }

  public boolean isInQueue(int x, int y) {
    return this.isInQueue(new Position(x, y));
  }
}
