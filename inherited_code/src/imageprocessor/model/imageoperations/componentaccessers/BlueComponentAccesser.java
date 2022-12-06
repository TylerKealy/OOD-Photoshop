package imageprocessor.model.imageoperations.componentaccessers;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Accesses the blue component of the pixel.
 */
public class BlueComponentAccesser implements ComponentAccesser {

  /**
   * Creates a new blue component accessor.
   */
  public BlueComponentAccesser() {
    // Constructor doesn't need any value to access blue component.
  }

  /**
   * Returns the normalized blue component int of the given pixel.
   * @param pixel the given pixel
   * @return the normalized blue component
   */
  public int apply(IPixel pixel) {
    int component = pixel.getBlueComponent();
    int normalized = ComponentAccesser.toBase256(component, 255);
    return normalized;
  }
}
