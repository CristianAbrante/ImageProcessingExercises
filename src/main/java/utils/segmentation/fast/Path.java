package utils.segmentation.fast;

import utils.segmentation.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Path implements Iterable<Position> {
  ArrayList<Position> path;

  public Path() {
    path = new ArrayList<>();
  }

  public void addToPath(int x, int y) {
    this.addToPath(new Position(x, y));
  }

  public void addToPath(Position position) {
    path.add(position);
  }

  public void appendPath(Path other) {
    for (Position position : other) {
      this.addToPath(position);
    }
  }

  public Path getSubPath(Position position) {
    int currentIndex = -1;
    for (Position thisPosition : this) {
      currentIndex += 1;
      if (thisPosition.equals(position)) {
        break;
      }
    }
    return this.getSubPath(currentIndex);
  }

  public Path getSubPath(int index) {
    List<Position> positions = this.path.subList(index, this.path.size());
    Path newPath = new Path();
    for (Position position : positions) {
      newPath.addToPath(position);
    }
    return newPath;
  }

  public Position last() {
    return this.path.get(this.path.size() - 1);
  }

  public Position first() {
    return this.path.get(0);
  }

  public boolean havePosition(Position position) {
    for (Position pos : this) {
      if (pos.equals(position)) {
        return true;
      }
    }
    return false;
  }

  public boolean havePosition(int x, int y) {
    return this.havePosition(new Position(x, y));
  }

  @Override
  public Iterator<Position> iterator() {
    return this.path.iterator();
  }

  @Override
  public void forEach(Consumer<? super Position> action) {
    this.path.forEach(action);
  }

  @Override
  public Spliterator<Position> spliterator() {
    return this.path.spliterator();
  }
}
