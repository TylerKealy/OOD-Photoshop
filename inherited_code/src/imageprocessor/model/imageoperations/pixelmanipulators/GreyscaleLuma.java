package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can greyscale an image based off its luma component.
 */
public class GreyscaleLuma implements PixelTransformation {
  /**
   * Creates a new Greyscale Red to transform an image.
   */
  public GreyscaleLuma() {
    // constructor doesn't need any value to make the new pixel.
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    double luma = 0.2126 * pixel.getRedComponent() + 0.7152 * pixel.getGreenComponent() +
            0.0722 * pixel.getBlueComponent();
    pixel.setComponents((int) luma, (int) luma, (int) luma);
  }
}