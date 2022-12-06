package imageprocessor.model.components.image;

import java.awt.image.BufferedImage;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Represents an image that is a sequence of pixels which can be mutated.
 */
public interface IImage extends IImageState {

  /** Sets a pixel at a specified coordinate.
   *
   * @param pixel a pixel
   * @param r the row of the pixel
   * @param c the column of pixel
   */
  public void setPixelAt(IPixel pixel, int r, int c);

  /**
   * Overwrite the name of the image.
   * @param name the new name of this image
   */
  public void setName(String name);

  /**
   * Creates an asRGB from the given extension and buffered image.
   * @param extension the given extension
   * @param img teh image to be used in the asRGB
   * @return the asRGB
   */
  public static IImage createFromBufferedImage(String extension, BufferedImage img) {
    IImage asRGB = new RGBImage("", img);
    return asRGB;
  }
}
