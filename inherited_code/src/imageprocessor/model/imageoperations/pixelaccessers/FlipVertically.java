package imageprocessor.model.imageoperations.pixelaccessers;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that flips an image vertically.
 */
public class FlipVertically implements PixelAccesser {
  /**
   * Creates a flip vertically to transform an image.
   */
  public FlipVertically() {
    // constructor doesn't need any value to get the new pixel.
  }

  /**
   * Takes in the image, row and column, and gets the new pixel that will replace the old one.
   *
   * @param img the image that is being altered.
   * @param r   the row of the old pixel.
   * @param c   the column of the old pixel.
   * @return the new pixel that will replace the old one.
   */
  public IPixel getPixel(IImage img, int r, int c) {
    IPixel pixel = img.getPixelAt(img.getHeight() - r - 1, c);
    return pixel;
  }
}

