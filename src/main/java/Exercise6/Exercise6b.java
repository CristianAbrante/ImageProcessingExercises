package Exercise6;

import boofcv.struct.image.GrayU8;
import utils.MorphologicalOperation;
import utils.MorphologicalOperator;
import utils.StructuringElement;
import utils.Utils;

import java.io.IOException;

public class Exercise6b {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Error: program expects three arguments");
            return;
        }
        try {
            int size = Integer.parseInt(args[0]);
            StructuringElement element = new StructuringElement(size);

            GrayU8 inputImage = Utils.readPGMImage(args[1]);
            GrayU8 resultImage = MorphologicalOperator.applyOperation(inputImage, MorphologicalOperation.OPENING_CLOSING, element);
            Utils.saveImage(resultImage, args[2]);
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
