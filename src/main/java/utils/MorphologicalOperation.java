package utils;

import boofcv.struct.image.GrayU8;

public enum MorphologicalOperation {
  OPENING,
  CLOSING,
  CLOSING_OPENING,
  OPENING_CLOSING;

  public static MorphologicalOperation of(String key) {
    for (MorphologicalOperation entry : MorphologicalOperation.values()) {
      if (entry.toString().equals(key.toUpperCase())) {
        return entry;
      }
    }
    throw new IllegalArgumentException("Error: key is not a morphological operation");
  }

  public GrayU8 applyOperation(GrayU8 inputImage, StructuringElement element) {
    if (this.name().equals("OPENING")) {
      GrayU8 erodedImage = Filter.applyFilter(inputImage, element, FilterOperation.EROSION);
      return Filter.applyFilter(erodedImage, element, FilterOperation.DILATION);
    } else if (this.name().equals("CLOSING")) {
      GrayU8 erodedImage = Filter.applyFilter(inputImage, element, FilterOperation.DILATION);
      return Filter.applyFilter(erodedImage, element, FilterOperation.EROSION);
    } else if (this.name().equals("CLOSING_OPENING")) {
      GrayU8 openingResult = MorphologicalOperation.OPENING.applyOperation(inputImage, element);
      return MorphologicalOperation.CLOSING.applyOperation(openingResult, element);
    } else if (this.name().equals("OPENING_CLOSING")) {
      GrayU8 closingResult = MorphologicalOperation.CLOSING.applyOperation(inputImage, element);
      return MorphologicalOperation.OPENING.applyOperation(closingResult, element);
    }
    return inputImage;
  }
}
