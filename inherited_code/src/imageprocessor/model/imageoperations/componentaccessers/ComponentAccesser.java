package imageprocessor.model.imageoperations.componentaccessers;

import imageprocessor.model.components.pixel.IPixel;

/**
 * Accesses a component of the pixel.
 */
public interface ComponentAccesser {
  /**
   * Accesses the given pixel for the desired component.
   * @param pixel the given pixel
   * @return the desired component
   */
  public int apply(IPixel pixel);

  /** Converts a value of arbitrary max value into its form as a 256-base (255 max-value).
   * WARNING: may lose data in unusual bases.
   * @param componentVal the current value of a component.
   * @param maxVal the max value (255 is default).
   * @return the component when the max value is 255.
   */
  public static int toBase256(int componentVal, int maxVal) {
    double universalBase = (((double) componentVal) / maxVal);
    int converted = (int) (universalBase * 255);
    return converted;
  }
}
