package utils;

import java.util.ArrayList;
import java.util.Collections;

public enum FilterOperation {
  EROSION, DILATION;

  public int computeOperation(ArrayList<Integer> neighbours) {
    if (this.toString().equals("EROSION")) {
      return Collections.min(neighbours);
    } else {
      return Collections.max(neighbours);
    }
  }

  public static FilterOperation of(String key) {
    for (FilterOperation entry : FilterOperation.values()) {
      if (entry.toString().equals(key.toUpperCase())) {
        return entry;
      }
    }
    throw new IllegalArgumentException("Error: unrecognized operation code.");
  }
}
