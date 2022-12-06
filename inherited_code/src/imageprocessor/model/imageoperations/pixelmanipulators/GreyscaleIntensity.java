package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Class that can greyscale an image based off its intensity component.
 */
public class GreyscaleIntensity implements PixelTransformation {
  /**
   * Creates a new Greyscale Red to transform an image.
   */
  public GreyscaleIntensity() {
    // constructor doesn't need any value to make the new pixel.
  }

  /**
   * Takes in the pixel and produces the new pixel that will replace the old one.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel) {
    int intensity = (pixel.getRedComponent() + pixel.getGreenComponent() +
            pixel.getBlueComponent()) / 3;
    pixel.setComponents(intensity, intensity, intensity);
  }
}
