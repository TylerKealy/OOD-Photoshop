package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can greyscale an image based off its max value component.
 */
public class GreyscaleMaxValue implements PixelTransformation {
  /**
   * Creates a new Greyscale MaxValue to transform an image.
   */
  public GreyscaleMaxValue() {
    // constructor doesn't need any value to make the new pixel.
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    int max = Math.max(pixel.getRedComponent(), pixel.getGreenComponent());
    int max2 = Math.max(max, pixel.getBlueComponent());
    pixel.setComponents(max2, max2, max2);
  }
}
