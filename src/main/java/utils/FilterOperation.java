package utils;

import java.util.ArrayList;
import java.util.Collections;

public enum FilterOperation {
    EROSION("erosion"), DILATION("dilation");

    private String key;

    private FilterOperation(String key) {
        this.key = key;
    }

    public int computeOperation(ArrayList<Integer> neighbours) {
        if (this.key.equals("erosion")) {
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
