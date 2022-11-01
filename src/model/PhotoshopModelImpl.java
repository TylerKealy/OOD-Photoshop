package model;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class PhotoshopModelImpl implements PhotoshopModel {

  Map<String, RGB[][]> imageStorage = new HashMap<>();

  public PhotoshopModelImpl() {

  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    ImageUtil util = new ImageUtil();
    RGB[][] pixels = util.readPPM(imagePath);
    this.imageStorage.put(imageName, pixels);

  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    try {
      PrintWriter outfile = new PrintWriter(imagePath + imageName + ".ppm");

      RGB[][] pixels = imageStorage.get(imageName);

      outfile.println("P3");
      outfile.println(pixels.length + " " + pixels[0].length);
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
            output[col][source[0].length - row] = source[col][row];
            break;
          case Vertical:
            output[source.length - col][row] = source[col][row];
            break;
          default:
            throw new IllegalArgumentException("Flip direction invalid.");
        }
      }
    }
    saveImage("images/", destImageName);

  }

  @Override
  public void colorComponent(RGBColor color, String imageName, String destImageName) {

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
    System.out.println("pixels.length: " + pixels.length);

    for (int col = 0; col < pixels.length; col++) {
      for (int row = 0; row < pixels[0].length; row++) {
        pixels[col][row].r = Math.min(increment + pixels[col][row].r, 255);
        pixels[col][row].g = Math.min(increment + pixels[col][row].g, 255);;
        pixels[col][row].b = Math.min(increment + pixels[col][row].b, 255);;
      }
    }
    //idk if this is how it should work.
    imageStorage.put(destImageName, pixels);
    saveImage("images/", destImageName);
  }


  //figure out loading/saving an image and why its currently broken.
  public static void main(String []args) {
    PhotoshopModelImpl impl = new PhotoshopModelImpl();
    impl.loadImage("images/Koala.ppm", "koala");
    //impl.saveImage("images/", "koala");
    impl.brighten(0, "koala", "another_koala");
  }
}
