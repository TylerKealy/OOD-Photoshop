package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can add a given value to each pixel in the image, brightening it.
 */
public class ChangeBrightness implements PixelTransformation {
  private int value;

  /**
   * Creates a new Change Brightness to transform it.
   *
   * @param value the amount of brightness that will change.
   */
  public ChangeBrightness(int value) {
    this.value = value;
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    int maxRed = Math.max(0, Math.min(pixel.getRedComponent() + value, pixel.getMaxValue()));
    int maxGreen = Math.max(0, Math.min(pixel.getGreenComponent() + value, pixel.getMaxValue()));
    int maxBlue = Math.max(0, Math.min(pixel.getBlueComponent() + value, pixel.getMaxValue()));

    pixel.setComponents(maxRed, maxGreen, maxBlue);
  }
}

