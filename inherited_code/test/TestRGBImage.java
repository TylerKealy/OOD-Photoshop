import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for RGB Image.
 */
public class TestRGBImage {
  private IImage image1;
  private IImage image2;
  private IImage image3;

  @Before
  public void setup() {
    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(1,1,1));
    pixelGrid.get(0).add(new RGBPixel(2,2,2));
    pixelGrid.get(1).add(new RGBPixel(3,3,3));
    pixelGrid.get(1).add(new RGBPixel(4,4,4));
    this.image1 = new RGBImage("one", pixelGrid);
    this.image2 = new RGBImage((RGBImage) this.image1);
    this.image2.setName("two");

    List<List<IPixel>> pixelGrid2 = new ArrayList<>();
    List<IPixel> arr3 = new ArrayList<IPixel>();
    pixelGrid2.add(arr3);
    pixelGrid2.get(0).add(new RGBPixel(1,1,1));
    this.image3 = new RGBImage("three", pixelGrid2);
  }

  @Test
  public void testGetters() {
    assertEquals("one", this.image1.getName());
    assertEquals(255, this.image1.getMaxValue());
    assertEquals(2, this.image1.getHeight());
    assertEquals(2, this.image1.getWidth());
    IPixel expectedPixel = new RGBPixel(4,4,4);
    IPixel actualPixel = this.image1.getPixelAt(1,1);
    assertEquals(expectedPixel.getRedComponent(), actualPixel.getRedComponent());
    assertEquals(expectedPixel.getBlueComponent(), actualPixel.getBlueComponent());
    assertEquals(expectedPixel.getGreenComponent(), actualPixel.getGreenComponent());

    assertEquals("two", this.image2.getName());
    assertEquals(255, this.image2.getMaxValue());
    assertEquals(2, this.image2.getHeight());
    assertEquals(2, this.image2.getWidth());
    IPixel expectedPixel2 = new RGBPixel(4,4,4);
    IPixel actualPixel2 = this.image2.getPixelAt(1,1);
    assertEquals(expectedPixel2, actualPixel2);
  }

  @Test
  public void testSetName() {
    assertEquals("one", this.image1.getName());
    this.image1.setName("two");
    assertEquals("two", this.image1.getName());
  }

  @Test
  public void testSetPixelAt() {
    IPixel expectedPixel = new RGBPixel(4,4,4);
    IPixel actualPixel = this.image1.getPixelAt(1,1);
    assertEquals(expectedPixel, actualPixel);

    IPixel newPixel = new RGBPixel(8, 8, 8);

    this.image1.setPixelAt(newPixel, 1, 1);
    IPixel actualPixel2 = this.image1.getPixelAt(1,1);
    assertEquals(newPixel, actualPixel2);
  }

  @Test
  public void testEqualsAndCopyImage() {
    IImage copy = this.image1.copyImage();
    assertTrue(this.image1 == this.image1);
    assertFalse(copy == this.image1);
    assertTrue(this.image1.equals(copy));

    assertFalse(this.image2.equals(this.image1));
    this.image2.setName("one");
    assertFalse(this.image3.equals(this.image1));

    assertTrue(this.image1.equals(this.image1));
  }

  @Test
  public void testHashCode() {
    assertEquals(1784839463, this.image1.hashCode());
    assertEquals(1784844557, this.image2.hashCode());
    assertEquals(1283252155, this.image3.hashCode());
    IImage copy = new RGBImage((RGBImage) this.image1);
    assertEquals(1784839463, copy.hashCode());

    assertEquals(this.image1.hashCode(), copy.hashCode());
  }

  @Test
  public void testBufferedImageConversions() {
    BufferedImage buf = this.image1.toBufferedImage(BufferedImage.TYPE_INT_ARGB);
    IImage newImg = new RGBImage("one", buf);

    assertEquals(this.image1, newImg);

  }

}
