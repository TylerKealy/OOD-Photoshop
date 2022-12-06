package imageprocessor.model.imageoperations.pixelfilters;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

/** Applies an effect on a pixel based on surrounding pixels.
 *
 */
public interface PixelFilter {
  /** Applies a filter on a pixel.
   *
   * @param pixel the pixel to apply the filter on.
   * @param image the original image to use for surrounding pixel data.
   * @param r the row of the pixel.
   * @param c the column of the pixel.
   */
  public void apply(IPixel pixel, IImage image, int r, int c);
}
