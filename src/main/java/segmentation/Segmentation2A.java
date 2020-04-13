package segmentation;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Segmentation2A {
  public static String WHEEL_IMAGE_PATH = "test-images/segmentation/wheel.png";

  public static void main(String[] args) {
    //Loading the OpenCV core library
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    //Reading the Image from the file
    Mat wheelImage = Imgcodecs.imread(WHEEL_IMAGE_PATH);

    // Aditional images used to store operation results
    Mat resizeResult = new Mat();
    Mat erosionResult = new Mat();
    Mat cannyResult = new Mat();
    Mat preprocessedImage = new Mat();

    // Resize operation
    Imgproc.resize(wheelImage, resizeResult, new Size(wheelImage.width() * 2, wheelImage.height() * 2));

    // Preparing the kernel matrix object
    Mat kernel = Imgproc.getStructuringElement(
            Imgproc.MORPH_RECT,
            new Size(3, 3));

    // Perform the erosion operation of the image
    Imgproc.erode(resizeResult, erosionResult, kernel);

    // To that image, we apply the canny contours detection
    Imgproc.Canny(erosionResult, cannyResult, 50, 150);

    // Finally, we apply a dilation
    Imgproc.dilate(cannyResult, preprocessedImage, kernel);

    // Declare auxiliary methods
    List<MatOfPoint> contours = new ArrayList<>();
    Mat hierarchy = new Mat();

    // We find the contours of the image
    Imgproc.findContours(
            preprocessedImage,
            contours,
            hierarchy,
            Imgproc.RETR_CCOMP,
            Imgproc.CHAIN_APPROX_SIMPLE,
            new Point(0, 0));

    int maxIndex = 0, maxArea = 0;
    for (int i = 0; i < contours.size(); i++) {
      Rect contour = Imgproc.boundingRect(contours.get(i));
      int area = contour.width * contour.height;
      if (area > maxArea) {
        maxArea = area;
        maxIndex = i;
      }
    }

    MatOfInt hull = new MatOfInt();
    Imgproc.convexHull(contours.get(maxIndex), hull);


    System.out.println("Number of teeth > " + (int) hull.size().height);
  }
}
