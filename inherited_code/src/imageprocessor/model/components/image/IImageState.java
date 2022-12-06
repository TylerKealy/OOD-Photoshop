package imageprocessor.model.components.image;

import java.awt.image.BufferedImage;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Represents an image that is a sequence of pixels.
 */
public interface IImageState {
  /**
   * Gets a pixel at a specified location.
   *
   * @param r the row of the pixel indexing by 0
   * @param c the column of the pixel indexing by 0
   * @return the pixel at the specified location
   */
  public IPixel getPixelAt(int r, int c);


  /**
   * Gets the width of the image.
   *
   * @return the image width in pixels
   */
  public int getWidth();

  /**
   * Gets the height of the image.
   *
   * @return the image height in pixels
   */
  public int getHeight();

  /**
   * Gets the maxValue of a pixel.
   *
   * @return the maxValue of the pixel.
   */
  public int getMaxValue();

  /**
   * Gets the name of the image.
   *
   * @return the name of the image.
   */
  public String getName();

  /**
   * Copies an image.
   *
   * @return the copies image.
   */
  public IImage copyImage();

  /** Converts an image into a buffered Image.
   *
   * @param bufferedImageType the type of BufferedImage (Such as ARGB, RGB, etc).
   * @return BufferedImage using data from this image.
   */
  public BufferedImage toBufferedImage(int bufferedImageType);

}
