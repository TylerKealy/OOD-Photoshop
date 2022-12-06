package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can greyscale an image based off its luma component.
 */
public class SepiaTone implements PixelTransformation {
  /**
   * Creates a new Sepia Tone to transform an image.
   */
  public SepiaTone() {
    // constructor doesn't need any value to make the new pixel.
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    int r = pixel.getRedComponent();
    int b = pixel.getBlueComponent();
    int g = pixel.getGreenComponent();
    double newRed = Math.max(0, Math.min(0.393 * r + 0.769 * g + 0.189 * b, pixel.getMaxValue()));
    double newGreen = Math.max(0, Math.min(0.349 * r + 0.686 * g + 0.168 * b, pixel.getMaxValue()));
    double newBlue = Math.max(0, Math.min(0.272 * r + 0.534 * g + 0.131 * b, pixel.getMaxValue()));

    pixel.setComponents((int) newRed, (int) newGreen, (int) newBlue);
  }
}