package imageprocessor.model.imageoperations.pixelmanipulators;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Represents a class that can apply transformations on given pixels.
 */
public interface PixelTransformation {
  /**
   * Applies the transformation on the given pixel.
   *
   * @param pixel the pixel that is being transformed.
   */
  public void apply(IPixel pixel);
}
