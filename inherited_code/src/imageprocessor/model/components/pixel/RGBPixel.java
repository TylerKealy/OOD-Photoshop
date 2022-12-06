package imageprocessor.model.components.pixel;

import java.awt.Color;
import java.util.Objects;


/**
 * Represents an RGB pixel with 3 components and a MaxValue.
 * there should be no methods in the pixel class except getters.
 */
public class RGBPixel implements IPixel {
  private int maxValue;
  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * Represents a hexadecimal pixel with given RGB components.
   * @param red the red component of the pixel
   * @param green the green component of the pixel
   * @param blue the blue component of the pixel
   */
  public RGBPixel(int red, int green, int blue) {
    this(255, red, green, blue);
  }

  /**
   * Represents a pixel with a given maxValue and given RGB components.
   * @param maxValue the maximum value of each RGB component
   * @param red the red component of the pixel
   * @param green the green component of the pixel
   * @param blue the blue component of the pixel
   */
  public RGBPixel(int maxValue, int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.maxValue = maxValue;
    this.alpha = maxValue;
  }

  /**
   * Represents a pixel that copies a given pixel.
   * @param pixel the given pixel.
   */
  public RGBPixel(RGBPixel pixel) {
    this.red = pixel.getRedComponent();
    this.green = pixel.getGreenComponent();
    this.blue = pixel.getBlueComponent();
    this.maxValue = pixel.getMaxValue();
    this.alpha = pixel.getAlpha();
  }

  /**
   * Creates a new RGB pixel given the RGB as an int value.
   * @param rgb the RGB as an int value
   */
  public RGBPixel(int rgb) {
    this(rgb, 255);
  }

  /**
   * Creates a new RGB pixel given the RGB as an int value and the maxValue.
   * @param rgb the RGB as an int value
   * @param maxValue the max value
   */
  public RGBPixel(int rgb, int maxValue) {
    this.maxValue = maxValue;
    Color c = new Color(rgb);
    this.red = this.fromBase256(c.getRed(), this.getMaxValue());
    this.green = this.fromBase256(c.getGreen(), this.getMaxValue());
    this.blue = this.fromBase256(c.getBlue(), this.getMaxValue());
    this.alpha = this.fromBase256(c.getAlpha(), this.getMaxValue());
  }


  /**
   * Gets the red component of the pixel.
   * @return red component
   */
  @Override
  public int getRedComponent() {
    return this.red;
  }

  /**
   * Gets the green component of the pixel.
   * @return green component
   */
  @Override
  public int getGreenComponent() {
    return this.green;
  }

  /**
   * Gets the blue component of the pixel.
   * @return blue component
   */
  @Override
  public int getBlueComponent() {
    return this.blue;
  }

  /**
   * Gets the max value component of the pixel.
   * @return max value component
   */
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * Overwrites the components to the given values.
   * @param red given red component.
   * @param blue given blue component.
   * @param green given green component.
   */
  public void setComponents(int red, int green, int blue) {
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Checks if this object is equal to the given object.
   * @param other the other object.
   * @return true if both objects are equal
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    if (!(other instanceof RGBPixel)) {
      return false;
    }

    RGBPixel o = (RGBPixel) other;

    return (this.getBlueComponent() == o.getBlueComponent()
          && this.getGreenComponent() == o.getGreenComponent()
          && this.getRedComponent() == o.getRedComponent()
          && this.getMaxValue() == o.getMaxValue());

  }

  /**
   * Creates a hashcode of the pixel's private variables.
   * @return the hashcode of the red, blue, green, and maxvalue
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getBlueComponent(),
            this.getGreenComponent(),
            this.getRedComponent(),
            this.getMaxValue());
  }

  /**
   * Return the alpha of this pixel.
   * @return alpha of this pixel
   */
  @Override
  public int getAlpha() {
    return this.alpha;
  }


  /** Converts RGB data as an integer. Note that this data is in a 256-representation
   * so using an unconventional base may make lead to losing precision of the data.
   *
   * @return the integer representation of the RGB data
   */
  @Override
  public int toInt() {
    int alpha = this.toBase256(this.getAlpha(), this.getMaxValue());
    int red = this.toBase256(this.getRedComponent(), this.getMaxValue());
    int green = this.toBase256(this.getGreenComponent(), this.getMaxValue());
    int blue = this.toBase256(this.getBlueComponent(), this.getMaxValue());
    Color c = new Color(red, green, blue, alpha);
    return c.getRGB();
  }

  /** Converts a value of arbitrary max value into its form as a 256-base (255 max-value).
   * WARNING: may lose data in unusual bases.
   * @param componentVal the current value of a component.
   * @param maxVal the max value (255 is default).
   * @return the component when the max value is 255.
   */
  private int toBase256(int componentVal, int maxVal) {
    double universalBase = (((double) componentVal) / maxVal);
    int converted = (int) (universalBase * 255);
    return converted;
  }

  /** Converts a value from base 256 into its form as an arbitrary base.
   * WARNING: may lose data in unusual bases.
   * @param componentVal the current value of a component.
   * @param maxVal the max value to convert to.
   * @return the component when the max value is maxVal.
   */
  private int fromBase256(int componentVal, int maxVal) {
    double universalBase = (((double) componentVal) / 255);
    int converted = (int) (universalBase * maxVal);
    return converted;
  }


}
