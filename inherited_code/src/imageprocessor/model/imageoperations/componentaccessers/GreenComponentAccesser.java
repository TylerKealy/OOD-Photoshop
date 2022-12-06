package imageprocessor.model.imageoperations.componentaccessers;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Accesses the green component of the pixel.
 */
public class GreenComponentAccesser implements ComponentAccesser {

  /**
   * Creates a new green component accessor.
   */
  public GreenComponentAccesser() {
    // Constructor doesn't need any value to access green component.
  }

  /**
   * Returns the normalized green component int of the given pixel.
   * @param pixel the given pixel
   * @return the normalized green component
   */
  public int apply(IPixel pixel) {
    int component = pixel.getGreenComponent();
    int normalized = ComponentAccesser.toBase256(component, 255);
    return normalized;
  }
}

