package utils.segmentation.fast;

import utils.segmentation.Position;

import java.util.ArrayList;
import java.util.List;

public class ImagePaths {
  private List<List<Path>> checkedPaths;

  public ImagePaths(int width, int height) {
    checkedPaths = new ArrayList<>();
    for (int x = 0; x < width; x++) {
      checkedPaths.add(new ArrayList<>());
      for (int y = 0; y < height; y++) {
        checkedPaths.get(y).add(null);
      }
    }
  }

  public boolean isChecked(int x, int y) {
    return this.isChecked(new Position(x, y));
  }

  public boolean isChecked(Position pos) {
    return this.checkedPaths.get(pos.getX()).get(pos.getY()) != null;
  }

  public void setPath(int x, int y, Path path) {
    this.setPath(new Position(x, y), path);
  }

  public void setPath(Position pos, Path path) {
    for (Position position : path) {
      if (!this.isChecked(position)) {
        Path subPath = path.getSubPath(position);
        this.setArrayPath(position, subPath);
      } else {
        break;
      }
    }
  }

  public void appendToPath(Position pos, Position lastPosition) {
    this.getPath(pos).addToPath(lastPosition);
  }

  public Path getPath(Position pos) {
    return this.checkedPaths.get(pos.getX()).get(pos.getY());
  }

  private void setArrayPath(Position pos, Path path) {
    this.checkedPaths.get(pos.getX()).set(pos.getY(), path);
  }
}
