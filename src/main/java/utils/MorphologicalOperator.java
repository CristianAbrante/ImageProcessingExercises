package utils;

import boofcv.struct.image.GrayU8;
import utils.structuring.StructuringElement;

public class MorphologicalOperator {
  public static GrayU8 applyOperation(GrayU8 inputImage, MorphologicalOperation operation, StructuringElement element) {
    return operation.applyOperation(inputImage, element);
  }
}
