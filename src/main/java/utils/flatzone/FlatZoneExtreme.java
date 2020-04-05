package utils.flatzone;

interface FlatZoneOperationCallback {
  boolean call(int flatZoneGrayLevel, int currentGrayLevel);
}

public enum FlatZoneExtreme {
  MAXIMUM((flatZoneGrayLevel, currentGrayLevel) -> currentGrayLevel <= flatZoneGrayLevel),
  MINIMUM((flatZoneGrayLevel, currentGrayLevel) -> currentGrayLevel >= flatZoneGrayLevel);

  private FlatZoneOperationCallback callback;

  FlatZoneExtreme(FlatZoneOperationCallback callback) {
    this.callback = callback;
  }

  public static FlatZoneExtreme of(String key) throws IllegalArgumentException {
    for (FlatZoneExtreme operation : FlatZoneExtreme.values()) {
      if (operation.toString().equals(key.toUpperCase())) {
        return operation;
      }
    }
    throw new IllegalArgumentException("Error: " + key + " is not a valid operation.");
  }


  public boolean isRegionExtreme(int flatZoneGrayLevel, int currentGrayLevel) {
    return this.callback.call(flatZoneGrayLevel, currentGrayLevel);
  }
}