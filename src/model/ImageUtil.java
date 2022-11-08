package model;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import model.Enums.RGB;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {


  public static RGB[][] read(String filename) {
    if (filename.endsWith(".ppm")) {
      return readPPM(filename);
    }
    return readSTDFormats(filename);
  }

  public static void save(String filename, RGB[][] pixels) {
    if (filename.endsWith(".ppm")) {
      savePPM(filename, pixels);
    }
  }

  //TODO: save as either a PNG or a JPG. use ImageIO.
  public static void saveSTDFormats(String filename, RGB[][] pixels) {
    if (filename.endsWith(".png")) {

    } else if (filename.endsWith(".jpg")) {

    }
  }

  public static void savePPM(String filename, RGB[][] pixels) {
    try {
      System.out.println("saving");
      PrintWriter outfile = new PrintWriter(filename);

      outfile.println("P3");
      outfile.println(pixels[0].length + " " + pixels.length);
      outfile.println("255");

      int height = pixels.length;
      int width = pixels[0].length;
      for (int row = 0; row < height; row++) {
        for (int col = 0; col < width; col++) {
          outfile.println(pixels[row][col].r);
          outfile.println(pixels[row][col].g);
          outfile.println(pixels[row][col].b);
        }
      }
      outfile.close();
    } catch (Exception e) {
      System.out.println(e.toString() + " caught in createPPM.");
      e.printStackTrace();
    }
  }


  //TODO: add support for bmp.
  /**
   * Reads standard formats of imgaes such as PNG or JPG
   *
   * @return
   */
  public static RGB[][] readSTDFormats(String filename) {
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalStateException("Given file caused error!");
    }

    int height = bufferedImage.getHeight();
    int width = bufferedImage.getWidth();

    RGB[][] output = new RGB[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        RGB pixel = new RGB(bufferedImage.getRGB(col, row));
        output[row][col] = pixel;
      }
    }

    return output;
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static RGB[][] readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    //System.out.println("Width of image: "+width);
    int height = sc.nextInt();
    //System.out.println("Height of image: "+height);
    int maxValue = sc.nextInt();
    //System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    RGB[][] output = new RGB[height][width];
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGB pixel = new RGB(r, g, b);
        output[row][col] = pixel;
        //System.out.println("Color of pixel ("+j+","+row+"): "+ r+","+g+","+b);
      }
    }
    return output;
  }


  /**
   * main method used to demo the ImageUtil class.
   *
   * @param args java arguments.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "images/Koala.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}

