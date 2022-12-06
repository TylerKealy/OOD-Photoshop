import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.ImageMapCollection;

import static org.junit.Assert.assertEquals;

/**
 * Test class for image map collection.
 */
public class TestImageMapCollection {
  private ImageCollection collection;
  private IImage image1;
  private IImage image2;

  @Before
  public void setup() {
    this.collection = new ImageMapCollection();
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
  }

  @Test
  public void testAddAndGetImage() {
    this.collection.addImage(this.image1);
    assertEquals(this.image1, this.collection.getImage("one"));

    this.collection.addImage(this.image2);
    assertEquals(this.image2, this.collection.getImage("two"));

  }

  @Test
  public void testRemoveImage() {
    this.collection.addImage(this.image2);
    assertEquals(this.image2, this.collection.getImage("two"));

    this.collection.removeImage("two");

    assertEquals(null, this.collection.getImage("two"));

  }

  @Test
  public void testRenameImage() {
    this.collection.addImage(this.image2);
    assertEquals(this.image2, this.collection.getImage("two"));
    assertEquals(null, this.collection.getImage("three"));

    this.collection.renameImage("two", "three");

    assertEquals(null, this.collection.getImage("two"));
    assertEquals(this.image2, this.collection.getImage("three"));


  }

  @Test
  public void testSelectedImage() {
    assertEquals(null, this.collection.getSelectedImage());

    this.collection.setSelectedImage("test");
    assertEquals("test", this.collection.getSelectedImage());

    this.collection.setSelectedImage("test2");
    assertEquals("test2", this.collection.getSelectedImage());
  }
}
