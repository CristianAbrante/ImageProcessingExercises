package exercise2;

import boofcv.struct.image.GrayU8;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Exercise2b {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Error: program expects two arguments");
            return;
        }
        try {
            GrayU8 firstImage = Utils.readPGMImage(args[0]);
            GrayU8 secondImage = Utils.readPGMImage(args[1]);

            FileWriter fstream = new FileWriter("src/out/exercise-2/exercise_02b_output.txt");
            PrintWriter out = new PrintWriter(fstream);

            if (Utils.areEqual(firstImage, secondImage)) {
                out.write("=");
                System.out.println("=");
            } else {
                out.write("!=");
                System.out.println("!=");
            }

            out.close();
        }
        catch (IOException e) {
            System.err.println("Error reading the images.");
            e.printStackTrace();
        }
    }
}
