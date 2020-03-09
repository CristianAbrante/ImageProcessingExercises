package exercise4;

import boofcv.struct.image.GrayU8;
import org.jcodec.common.DictionaryCompressor;
import utils.*;

import java.io.IOException;

public class Exercise4 {
    public static void main(String[] args) {
        try {
            MorphologicalOperation operation = MorphologicalOperation.of(args[0]);
            int times = Integer.parseInt(args[1]);
            final int size = Integer.parseInt(args[2]);
            GrayU8 inputImage = Utils.readPGMImage(args[3]);

            Idempotence.proveIdempotence(inputImage, times, args[4],
                    (GrayU8 image) -> MorphologicalOperator.applyOperation(image, operation, new StructuringElement(size)))

            if (times <= 0) {
                System.err.println("Error: you have to repeat operation more than once");
            }
            for (int i = 0; i < times; i++) {
                Utils.saveImage(inputImage, re);
            }
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
