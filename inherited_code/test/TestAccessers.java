import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.imageoperations.componentaccessers.BlueComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.ComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.GreenComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.IntensityComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.RedComponentAccesser;

import static org.junit.Assert.assertEquals;


/** Tests for component accessers.
 *
 */
public class TestAccessers {
  private IImage image1;

  @Before
  public void setup() {
    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(1,2,3));
    pixelGrid.get(0).add(new RGBPixel(4,5,6));
    pixelGrid.get(1).add(new RGBPixel(7,8,9));
    pixelGrid.get(1).add(new RGBPixel(10,11,12));
    this.image1 = new RGBImage("one", pixelGrid);
  }

  @Test
  public void testRedAccesser() {
    ComponentAccesser component = new RedComponentAccesser();
    assertEquals(1, component.apply(this.image1.getPixelAt(0,0)));
    assertEquals(4, component.apply(this.image1.getPixelAt(0,1)));
    assertEquals(7, component.apply(this.image1.getPixelAt(1,0)));
    assertEquals(10, component.apply(this.image1.getPixelAt(1,1)));
  }

  @Test
  public void testGreenAccesser() {
    ComponentAccesser component = new GreenComponentAccesser();
    assertEquals(2, component.apply(this.image1.getPixelAt(0,0)));
    assertEquals(5, component.apply(this.image1.getPixelAt(0,1)));
    assertEquals(8, component.apply(this.image1.getPixelAt(1,0)));
    assertEquals(11, component.apply(this.image1.getPixelAt(1,1)));
  }

  @Test
  public void testBlueAccesser() {
    ComponentAccesser component = new BlueComponentAccesser();
    assertEquals(3, component.apply(this.image1.getPixelAt(0,0)));
    assertEquals(6, component.apply(this.image1.getPixelAt(0,1)));
    assertEquals(9, component.apply(this.image1.getPixelAt(1,0)));
    assertEquals(12, component.apply(this.image1.getPixelAt(1,1)));
  }

  @Test
  public void testIntensityAccesser() {
    ComponentAccesser component = new IntensityComponentAccesser();
    assertEquals(2, component.apply(this.image1.getPixelAt(0,0)));
    assertEquals(5, component.apply(this.image1.getPixelAt(0,1)));
    assertEquals(8, component.apply(this.image1.getPixelAt(1,0)));
    assertEquals(11, component.apply(this.image1.getPixelAt(1,1)));
  }

}