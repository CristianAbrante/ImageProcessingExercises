package utils;

import boofcv.struct.image.GrayU8;

public class MorphologicalOperator {
    public static GrayU8 applyOperation(GrayU8 inputImage, MorphologicalOperation operation, StructuringElement element) {
        switch (operation) {
            case OPENING:
                GrayU8 erodedImage = Filter.applyFilter(inputImage, element, FilterOperation.EROSION);
                return Filter.applyFilter(erodedImage, element, FilterOperation.DILATION);
            case CLOSING:
                GrayU8 dilatedImage = Filter.applyFilter(inputImage, element, FilterOperation.DILATION);
                return Filter.applyFilter(dilatedImage, element, FilterOperation.EROSION);
        }
        return inputImage;
    }
}
