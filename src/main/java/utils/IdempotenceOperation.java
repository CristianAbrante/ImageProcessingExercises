package utils;

import boofcv.struct.image.GrayU8;

public interface IdempotenceOperation {
    public GrayU8 operation(GrayU8 inputImage);
}
