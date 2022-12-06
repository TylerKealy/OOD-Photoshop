package imageprocessor.model.imageoperations.componentaccessers;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Accesses the red component of the pixel.
 */
public class RedComponentAccesser implements ComponentAccesser {

  /**
   * Creates a new red component accessor.
   */
  public RedComponentAccesser() {
    // Constructor doesn't need any value to access red component.
  }

  /**
   * Returns the normalized red component int of the given pixel.
   * @param pixel the given pixel
   * @return the normalized red component
   */
  public int apply(IPixel pixel) {
    int component = pixel.getRedComponent();
    int normalized = ComponentAccesser.toBase256(component, 255);
    return normalized;
  }
}
