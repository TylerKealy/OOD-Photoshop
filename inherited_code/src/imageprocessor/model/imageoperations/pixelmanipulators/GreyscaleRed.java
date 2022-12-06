package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can greyscale an image based off its red component.
 */
public class GreyscaleRed implements PixelTransformation {
  /**
   * Creates a new Greyscale Red to transform an image.
   */
  public GreyscaleRed() {
    // constructor doesn't need any value to make the new pixel.
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    pixel.setComponents(pixel.getRedComponent(), pixel.getRedComponent(),
            pixel.getRedComponent());
  }
}
