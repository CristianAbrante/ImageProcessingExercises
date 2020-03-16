package utils;

public enum LatticeOperation {
  SUPREMUM("supremum"), INFIMUM("infimum");

  private String key;

  LatticeOperation(String key) {
    this.key = key;
  }

  public static LatticeOperation of(String key) {
    for (LatticeOperation operation : LatticeOperation.values()) {
      if (operation.toString().equals(key.toUpperCase())) {
        return operation;
      }
    }
    throw new IllegalArgumentException("Error: operation " + key + " not defined.");
  }

  public int getOperationResult(int a, int b) {
    if (this.key.equals("supremum")) {
      return Math.max(a, b);
    } else {
      return Math.min(a, b);
    }
  }
}
