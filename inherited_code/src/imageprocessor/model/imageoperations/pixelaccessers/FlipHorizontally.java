package imageprocessor.model.imageoperations.pixelaccessers;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that flips an image horizontally.
 */
public class FlipHorizontally implements PixelAccesser {
  /**
   * Creates a flip horizontally to transform an image.
   */
  public FlipHorizontally() {
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
    IPixel pixel = img.getPixelAt(r, img.getWidth() - c - 1);
    return pixel;
  }
}
