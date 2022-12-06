import org.junit.Before;
import org.junit.Test;

import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for RGB Pixel.
 */
public class TestRGBPixel {
  private IPixel pixel1;
  private IPixel pixel2;

  @Before
  public void setup() {
    this.pixel1 = new RGBPixel(205, 124, 22);
    this.pixel2 = new RGBPixel(300, 124, 22, 276);
  }

  @Test
  public void testGetters() {
    assertEquals(205, this.pixel1.getRedComponent());
    assertEquals(124, this.pixel1.getGreenComponent());
    assertEquals(22, this.pixel1.getBlueComponent());
    assertEquals(255, this.pixel1.getMaxValue());
    assertEquals(255, this.pixel1.getAlpha());

    assertEquals(124, this.pixel2.getRedComponent());
    assertEquals(22, this.pixel2.getGreenComponent());
    assertEquals(276, this.pixel2.getBlueComponent());
    assertEquals(300, this.pixel2.getMaxValue());
  }

  @Test
  public void testSetComponents() {
    assertEquals(205, this.pixel1.getRedComponent());
    assertEquals(124, this.pixel1.getGreenComponent());
    assertEquals(22, this.pixel1.getBlueComponent());
    assertEquals(255, this.pixel1.getMaxValue());

    this.pixel1.setComponents(1,2,3);
    assertEquals(1, this.pixel1.getRedComponent());
    assertEquals(2, this.pixel1.getGreenComponent());
    assertEquals(3, this.pixel1.getBlueComponent());
    assertEquals(255, this.pixel1.getMaxValue());
  }

  @Test
  public void testEquals() {
    assertFalse(this.pixel1.equals(this.pixel2));
    assertTrue(this.pixel1.equals(this.pixel1));
    assertTrue(this.pixel1 == this.pixel1);
    assertFalse(this.pixel1 == this.pixel2);
    assertTrue(this.pixel1.equals(this.pixel1));

    IPixel copy = new RGBPixel((RGBPixel) this.pixel1);
    assertTrue(this.pixel1.equals(copy));
    assertFalse(this.pixel2.equals(copy));
    assertFalse(this.pixel1 == copy);
  }

  @Test
  public void testToInt() {
    assertEquals(-3310570, this.pixel1.toInt());
    assertEquals(-9891094, this.pixel2.toInt());
  }

}
