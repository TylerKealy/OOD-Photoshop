package model;

import java.util.HashMap;
import java.util.Map;

import model.enums.ComponentGreyscale;
import model.enums.Direction;
import model.enums.RGB;

/**
 * An implementation of PhotoshopModel that supports all functionality.
 */
public class PhotoshopModelImpl implements PhotoshopModel {

  protected final Map<String, RGB[][]> imageStorage;

  /**
   * Default constructor for normal purposes.
   */
  public PhotoshopModelImpl() {
    this.imageStorage = new HashMap<>();
  }

  /**
   * Constructor for testing purposes, or for importing your own hashMap library of
   * Pixel data for images. Will be accessible by those you call it.
   * @param imageStorage provided map of String to RGB[][]
   */
  public PhotoshopModelImpl(Map<String, RGB[][]> imageStorage) {
    this.imageStorage = imageStorage;
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    ImageUtil util = new ImageUtil();
    RGB[][] pixels = util.read(imagePath);
    this.imageStorage.put(imageName, pixels);
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    ImageUtil util = new ImageUtil();
    util.save(imagePath, imageStorage.get(imageName));
  }

  @Override
  public void flipImage(Direction flipDirection, String imageName, String destImageName) {
    //needs to throw error if the image doesnt have a source[0]
    RGB[][] source = imageStorage.get(imageName);
    RGB[][] output = new RGB[source.length][source[0].length];

    int height = source.length;
    int width = source[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        switch (flipDirection) {
          case Horizontal:
            output[row][source[0].length - col - 1] = source[row][col];
            break;
          case Vertical:
            output[source.length - row - 1][col] = source[row][col];
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
    RGB[][] output = new RGB[pixels.length][pixels[0].length];

    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int[] result = greyscaleHelper(pixels[row][col].r,
                pixels[row][col].g,
                pixels[row][col].b,
                color);
        output[row][col] = new RGB(0,0,0);
        output[row][col].r = result[0];
        output[row][col].g = result[1];
        output[row][col].b = result[2];
      }
    }
    imageStorage.put(destImageName, output);
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
    RGB[][] output = new RGB[pixels.length][pixels[0].length];

    int height = pixels.length;
    int width = pixels[0].length;
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        output[row][col] = new RGB(0,0,0);
        output[row][col].r = Math.max(Math.min(increment + pixels[row][col].r, 255),0);
        output[row][col].g = Math.max(Math.min(increment + pixels[row][col].g, 255),0);
        output[row][col].b = Math.max(Math.min(increment + pixels[row][col].b, 255),0);
      }
    }
    imageStorage.put(destImageName, output);
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
