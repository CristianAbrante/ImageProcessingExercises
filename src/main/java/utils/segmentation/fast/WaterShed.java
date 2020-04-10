package utils.segmentation.fast;

import boofcv.struct.image.GrayU8;
import utils.segmentation.Position;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaterShed {
  Position minimum;
  List<Position> positions;
  Color color;

  public WaterShed(Position minimum) {
    this.minimum = minimum;
    this.positions = new ArrayList<>();

    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();
    this.color = new Color(r, g, b);
  }

  public void addPosition(Position pos) {
    this.positions.add(pos);
  }

  public Position getMinimum() {
    return this.minimum;
  }

  public List<Position> getPositions() {
    return positions;
  }

  public Color getColor() {
    return color;
  }

  public static List<WaterShed> constructWaterSheds(GrayU8 image, DirectionMatrix directionMatrix, List<Position> localMinimums) {
    List<WaterShed> waterSheds = new ArrayList<>();

    // watersheds initialized with local minimums.
    for (Position minimum : localMinimums) {
      waterSheds.add(new WaterShed(minimum));
    }

    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        Direction direction = directionMatrix.getDirection(j, i);
        Position position = new Position(j, i);
        while (!direction.equals(Direction.CENTER)) {
          position = direction.followDirection(position);
          direction = directionMatrix.getDirection(position);
        }

        for (WaterShed shed : waterSheds) {
          if (shed.getMinimum().equals(position)) {
            shed.addPosition(new Position(j, i));
          }
        }
      }
    }

    return waterSheds;
  }

  public static BufferedImage getImageWithRegions(GrayU8 inputImage, List<WaterShed> regions) {
    BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (WaterShed region : regions) {
      for (Position position : region.getPositions()) {
        outputImage.setRGB(position.getX(), position.getY(), region.getColor().getRGB());
      }
    }
    return outputImage;
  }
}
