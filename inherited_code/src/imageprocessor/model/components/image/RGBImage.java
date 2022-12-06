package imageprocessor.model.components.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;

/**
 * Represents an image that is a sequence of RGB pixels
 * Need to have seperate classes for each method.
 * Each class will use the getters to complete each operation for the pixel
 */
public class RGBImage implements IImage {
  // double arraylist of pixels
  private List<List<IPixel>> pixels;

  private String name;

  /**
   * Represents an image that is a sequence of RGB pixels.
   */
  public RGBImage(String name, List<List<IPixel>> pixelGrid) {
    this.name = name;
    this.pixels = pixelGrid;
  }

  /**
   * Creates an RGB image that copies another RGB image.
   * @param image the RGB image that is copied.
   */
  public RGBImage(RGBImage image) {
    this.name = image.getName();
    this.pixels = new ArrayList<List<IPixel>>();

    for (int r = 0; r < image.getHeight(); r++) {
      List<IPixel> row = new ArrayList<IPixel>();
      for (int c = 0; c < image.getWidth(); c++) {
        IPixel pixel = new RGBPixel((RGBPixel) image.getPixelAt(r, c));
        row.add(pixel);
      }
      this.pixels.add(row);
    }

  }

  /** Creates an RGB image that copies a BufferedImage.
   *
   * @param name the name of the image
   * @param render the buffered image to copy pixel data from
   */
  public RGBImage(String name, BufferedImage render) {
    this.name = name;
    this.pixels = new ArrayList<List<IPixel>>();

    for (int r = 0; r < render.getHeight(); r++) {
      List<IPixel> row = new ArrayList<IPixel>();
      for (int c = 0; c < render.getWidth(); c++) {
        int rgbInt = render.getRGB(c, r);
        IPixel pixel = new RGBPixel(rgbInt);
        row.add(pixel);
      }
      this.pixels.add(row);
    }
  }


  /**
   * Overwrites the pixel at the given location with the given pixel.
   * @param pixel a pixel
   * @param r the row of the pixel
   * @param c the column of pixel
   */
  @Override
  public void setPixelAt(IPixel pixel, int r, int c) {
    pixels.get(r).set(c, pixel);
  }

  /**
   * Overwrite the name of the image.
   * @param name the new name of this image
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the name of the pixel.
   * @return name of the pixel
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the pixel at the given location.
   * @param r the row of the pixel indexing by 0
   * @param c the column of the pixel indexing by 0
   * @return the pixel at the given location
   */
  @Override
  public IPixel getPixelAt(int r, int c) {
    return pixels.get(r).get(c);
  }

  /**
   * Gets the width of the image.
   * @return width of image
   */
  @Override
  public int getWidth() {
    if (this.pixels.size() > 0) {
      return this.pixels.get(0).size();
    }

    return 0;
  }

  /**
   * Gets the height of the image.
   * @return height of image
   */
  @Override
  public int getHeight() {
    return this.pixels.size();
  }

  /**
   * Gets the max value of the image.
   * @return maxValue of image
   */
  @Override
  public int getMaxValue() {
    if (pixels.size() > 0) {
      if (pixels.get(0).size() > 0) {
        return pixels.get(0).get(0).getMaxValue();
      }
    }
    return 0;
  }

  /**
   * Copies an image.
   * @return the copies image.
   */
  @Override
  public IImage copyImage() {
    IImage img = new RGBImage(this);
    return img;
  }

  /**
   * Checks if the given object and this object are equal.
   * @param other the other object
   * @return true if the objects are equal
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    if (!(other instanceof RGBImage)) {
      return false;
    }

    RGBImage o = (RGBImage) other;

    if (this.getHeight() != o.getHeight()
        || this.getWidth() != o.getWidth()
        || !this.getName().equals(o.getName())
        || this.getMaxValue() != o.getMaxValue()) {
      return false;
    }

    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        if (!this.getPixelAt(r,c).equals(o.getPixelAt(r,c))) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Hashes this image's pixel array, the height, width, maxValue, and name.
   * @return the hashcode (an int)
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.pixels,
            this.getHeight(),
            this.getWidth(),
            this.getMaxValue(),
            this.getName());
  }

  /**
   * Converts this image to a buffered image using a given buffered type(png, jpg, etc.).
   * @param bufferedImageType the given buffered image type
   * @return the buffered image
   */
  @Override
  public BufferedImage toBufferedImage(int bufferedImageType) {

    BufferedImage bufImg = new BufferedImage(this.getWidth(), this.getHeight(), bufferedImageType);

    for (int r = 0; r < this.getHeight(); r++) {
      for (int c = 0; c < this.getWidth(); c++) {
        IPixel pixel = this.getPixelAt(r, c);
        int numRep = pixel.toInt();
        bufImg.setRGB(c, r, numRep);
      }
    }

    return bufImg;
  }
}
