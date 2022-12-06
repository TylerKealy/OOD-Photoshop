package imageprocessor.model.imageoperations.imagetransformations;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.imageoperations.pixelmanipulators.PixelTransformation;

/**
 * Performs a transformation that requires altering the individual pixels in the image.
 */
public class TransformAllPixels implements ImageTransformation {
  private PixelTransformation effect;

  /**
   * Creates a transformation with the desired effect.
   *
   * @param effect the desired effect
   */
  public TransformAllPixels(PixelTransformation effect) {
    this.effect = effect;
  }

  /**
   * Transforms the image as desired.
   *
   * @param image the image that will be altered.
   */
  public void transform(IImage image) {
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        IPixel pixel = image.getPixelAt(r, c);
        this.effect.apply(pixel);
      }
    }
  }
}
