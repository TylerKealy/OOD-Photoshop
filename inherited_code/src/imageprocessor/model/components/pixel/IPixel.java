package imageprocessor.model.components.pixel;

/**
 * Represents a pixel.
 */
public interface IPixel {
  /**
   * Gets the red component of the pixel.
   * @return red component
   */
  public int getRedComponent();

  /**
   * Gets the green component of the pixel.
   * @return green component
   */
  public int getGreenComponent();

  /**
   * Gets the blue component of the pixel.
   * @return blue component
   */
  public int getBlueComponent();

  /**
   * Gets the max value component of the pixel.
   * @return max value component
   */
  public int getMaxValue();

  /** Gets the alpha component of the pixel.
   *
   * @return the alpha component
   */
  public int getAlpha();

  /**
   * Overwrites the components to the given values.
   * @param red given red component.
   * @param blue given blue component.
   * @param green given green component.
   */
  public void setComponents(int red, int green, int blue);

  /** Converts the pixel data into integer form.
   *
   * @return the pixel data as an integer.
   */
  public int toInt();
}