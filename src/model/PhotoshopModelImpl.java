package model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of PhotoshopModel that supports all functionality.
 */
public class PhotoshopModelImpl implements PhotoshopModel {

  Map<String, RGB[][]> imageStorage = new HashMap<>();

  @Override
  public void loadImage(String imagePath, String imageName) {
    ImageUtil util = new ImageUtil();
    RGB[][] pixels = util.readPPM(imagePath);
    this.imageStorage.put(imageName, pixels);
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    try {
      PrintWriter outfile = new PrintWriter(imagePath + ".ppm");

      RGB[][] pixels = imageStorage.get(imageName);


      outfile.println("P3");
      outfile.println(pixels[0].length + " " + pixels.length);
      outfile.println("255");

      for (int col = 0; col < pixels.length; col++) {
        for (int row = 0; row < pixels[0].length; row++) {
          outfile.println(pixels[col][row].r);
          outfile.println(pixels[col][row].g);
          outfile.println(pixels[col][row].b);
        }
      }
      outfile.close();
    } catch (Exception e) {
      System.out.println(e.toString() + " caught in createPPM.");
      e.printStackTrace();
    }
  }

  @Override
  public void flipImage(Direction flipDirection, String imageName, String destImageName) {
    //needs to throw error if the image doesnt have a source[0]
    RGB[][] source = imageStorage.get(imageName);
    RGB[][] output = new RGB[source.length][source[0].length];

    for (int col = 0; col < source.length; col++) {
      for (int row = 0; row < source[0].length; row++) {
        switch (flipDirection) {
          case Horizontal:
            output[col][source[0].length - row - 1] = source[col][row];
            break;
          case Vertical:
            output[source.length - col - 1][row] = source[col][row];
            break;
          default:
            throw new IllegalArgumentException("Flip direction invalid.");
        }
      }
    }
    imageStorage.put(destImageName, output);
  }

  @Override
  public void greyscaleComponent(ComponentGreyscale color, String imageName, String destImageName) {
    RGB[][] pixels = imageStorage.get(imageName);

    for (int col = 0; col < pixels.length; col++) {
      for (int row = 0; row < pixels[0].length; row++) {
        int[] result = greyscaleHelper(pixels[col][row].r,
                pixels[col][row].g,
                pixels[col][row].b,
                color);
        pixels[col][row].r = result[0];
        pixels[col][row].g = result[1];
        pixels[col][row].b = result[2];
      }
    }
    imageStorage.put(destImageName, pixels);
  }

  //command design here?

  /**
   * Helper for greyscaleComponent. Returns an updated version of the given rgb values.
   *
   * @param r         red value
   * @param g         green value
   * @param b         blue value
   * @param component the component to greyscale.
   * @return a int[3] of the new r, g, b values.
   */
  private int[] greyscaleHelper(int r, int g, int b, ComponentGreyscale component) {
    switch (component) {
      case Red:
        g = 0;
        b = 0;
        break;
      case Green:
        r = 0;
        b = 0;
        break;
      case Blue:
        r = 0;
        g = 0;
        break;
      case Luma:
        int luma = (int) (.2126f * r + .7152f * g + .0722f * b);
        r = luma;
        g = luma;
        b = luma;
        break;
      case Value:
        int value = Math.max(Math.max(r, g), b);
        r = value;
        g = value;
        b = value;
        break;
      case Intensity:
        int intensity = (r + g + b) / 3;
        r = intensity;
        g = intensity;
        b = intensity;
        break;

      default:
        throw new IllegalArgumentException("Greyscale not supported.");
    }
    return new int[]{r, g, b};
  }

  /**
   * gets the given imageName, increments each component by increment and then saves
   * it as destImageName.
   *
   * @param increment     how much to add to each pixel (positive brighter)
   * @param imageName     name of the image to edit.
   * @param destImageName the name henceforth of the new brightened image.
   */
  @Override
  public void brighten(int increment, String imageName, String destImageName) {
    //go through each pixel and add increment to each pixel
    RGB[][] pixels = imageStorage.get(imageName);

    for (int col = 0; col < pixels.length; col++) {
      for (int row = 0; row < pixels[0].length; row++) {
        pixels[col][row].r = Math.min(increment + pixels[col][row].r, 255);
        pixels[col][row].g = Math.min(increment + pixels[col][row].g, 255);
        ;
        pixels[col][row].b = Math.min(increment + pixels[col][row].b, 255);
        ;
      }
    }
    imageStorage.put(destImageName, pixels);
  }


  /**
   * Main method used for testing the model.
   *
   * @param args java parameters.
   */
  public static void main(String[] args) {
    PhotoshopModelImpl impl = new PhotoshopModelImpl();
    impl.loadImage("images/koala.ppm", "koala");
    impl.greyscaleComponent(ComponentGreyscale.Value, "koala", "value");
    impl.saveImage("images/value", "value");
  }
}
