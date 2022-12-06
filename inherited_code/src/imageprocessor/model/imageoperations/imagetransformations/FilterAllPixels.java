package imageprocessor.model.imageoperations.imagetransformations;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.imageoperations.pixelfilters.PixelFilter;

/**
 * Performs a filter that requires altering the individual pixels in the image
 * using surrounding pixels.
 */
public class FilterAllPixels implements ImageTransformation {
  private PixelFilter effect;

  /**
   * Creates a filter with the desired effect.
   *
   * @param effect the desired effect
   */
  public FilterAllPixels(PixelFilter effect) {
    this.effect = effect;
  }

  /**
   * Transforms the image as desired.
   *
   * @param image the image that will be altered.
   */
  public void transform(IImage image) {
    IImage copy = image.copyImage();
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        IPixel center = image.getPixelAt(r, c);
        this.effect.apply(center, copy, r, c);
      }
    }
  }
}