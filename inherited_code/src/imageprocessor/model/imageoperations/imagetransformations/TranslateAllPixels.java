package imageprocessor.model.imageoperations.imagetransformations;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.imageoperations.pixelaccessers.PixelAccesser;

/**
 * Performs a transformation that doesn't require altering the individual pixels in the image.
 */
public class TranslateAllPixels implements ImageTransformation {
  private PixelAccesser effect;

  /**
   * Creates a transformation with the desired effect.
   *
   * @param effect the desired effect
   */
  public TranslateAllPixels(PixelAccesser effect) {
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
        IPixel pixel = this.effect.getPixel(copy, r, c);
        image.setPixelAt(pixel, r, c);
      }
    }
  }
}
