package imageprocessor.model.imagereading;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.RGBPixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its
 * contents. Feel free to change this method as required.
 */
public class PPMUtil extends ImageOperationsUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   * Read an image file and produce a double arraylist of pixels.
   * @param filename the path of the file.
   */
  public IImage read(String filename) {
    try {
      Readable in = new InputStreamReader(new FileInputStream(filename));
      return this.read(in);
    } catch (FileNotFoundException e) {
      throw new IllegalStateException("File " + filename + " not found!");
    }
  }

  /**
   * Read the file and produce the IImage, which is a 2d arraylist of pixels.
   * @param in the contents of the file.
   * @return the IImage extracted by reading the file
   */
  public IImage read(Readable in) {
    Scanner sc;

    sc = new Scanner(in);

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

    List<List<IPixel>> pixels = new ArrayList<List<IPixel>>();
    for (int i = 0; i < height; i++) {
      List<IPixel> row = new ArrayList<IPixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        IPixel pixel = new RGBPixel(maxValue, r, g, b);
        row.add(pixel);
        // System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);
      }
      pixels.add(row);
    }
    IImage img = new RGBImage("", pixels);
    return img;
  }


  /**
   * Takes in the name of the file and creates it.
   * @param fileName the name of the file to be created.
   * @return a file that was created
   */
  public File createFile(String fileName) {
    try {
      File f = new File(fileName);
      f.createNewFile();
      return f;
    }
    catch (IOException e) {
      throw new IllegalStateException("File could not be created");
    }

  }

  /** Creates a File Writer based on a path.
   *
   * @param path the path of the file.
   * @return a FileWriter
   */
  private FileWriter createFileWriter(String path) {
    try {
      FileWriter w = new FileWriter(path, false);
      w.close();
      return new FileWriter(path, true);
    }
    catch (IOException e) {
      throw new IllegalStateException("Invalid path");
    }
  }

  /**
   * Closes the given buffered writer.
   * @param w the given buffered writer
   */
  private void closeWriter(BufferedWriter w) {
    try {
      w.close();
    }
    catch (IOException e) {
      throw new IllegalStateException("Invalid buffered writer");
    }
  }

  /**
   * Creates a writer and writes it with the given parameters to writeLine.
   * @param path the path being used
   * @param image the image being used
   */
  public void write(String path, IImage image) {
    File f = this.createFile(path);

    BufferedWriter writer = new BufferedWriter(this.createFileWriter(path));

    this.writeLine(writer, "P3");
    this.writeLine(writer, image.getWidth() + " " + image.getHeight());
    this.writeLine(writer, "" + image.getMaxValue());

    for (int r = 0; r < image.getHeight(); r++) {
      String line = "";
      for (int c = 0; c < image.getWidth(); c++) {
        IPixel pixel = image.getPixelAt(r, c);
        RGBPixel asRGB = (RGBPixel) pixel;
        line += asRGB.getRedComponent() +
                " " + asRGB.getGreenComponent() +
                " " + asRGB.getBlueComponent() + " ";

      }
      this.writeLine(writer, line);
    }

    this.closeWriter(writer);
  }
}

