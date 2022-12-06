package imageprocessor.model.imageoperations.componentaccessers;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Accesses the intensity component of the pixel.
 */
public class IntensityComponentAccesser implements ComponentAccesser {

  /**
   * Creates a new intensity component accessor.
   */
  public IntensityComponentAccesser() {
    // Constructor doesn't need any value to access intensity component.
  }

  /**
   * Returns the normalized intensity component int of the given pixel.
   * @param pixel the given pixel
   * @return the normalized intensity component
   */
  public int apply(IPixel pixel) {
    int intensity = (pixel.getRedComponent() + pixel.getGreenComponent() +
            pixel.getBlueComponent()) / 3;
    int normalized = ComponentAccesser.toBase256(intensity, 255);
    return normalized;
  }
}
