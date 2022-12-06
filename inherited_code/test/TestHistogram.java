import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.components.histogram.IHistogram;
import imageprocessor.model.components.histogram.RGBHistogram;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.imageoperations.componentaccessers.BlueComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.GreenComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.IntensityComponentAccesser;
import imageprocessor.model.imageoperations.componentaccessers.RedComponentAccesser;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests for histograms.
 */
public class TestHistogram {
  private IHistogram histogram;

  @Before
  public void setup() {
    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(1,2,3));
    pixelGrid.get(0).add(new RGBPixel(4,5,6));
    pixelGrid.get(1).add(new RGBPixel(1,8,9));
    pixelGrid.get(1).add(new RGBPixel(10,11,12));
    IImage image1 = new RGBImage("one", pixelGrid);

    this.histogram = new RGBHistogram(image1);
  }

  @Test
  public void testCreateFromComponentAndGetHistogram() {
    this.histogram.createFromComponent(new RedComponentAccesser());
    int[] expectedRed = new int[256];
    expectedRed[1] = 2;
    expectedRed[4] = 1;
    expectedRed[10] = 1;

    assertArrayEquals(expectedRed, this.histogram.getHistogram());

    this.histogram.createFromComponent(new GreenComponentAccesser());
    int[] expectedGreen = new int[256];
    expectedGreen[2] = 1;
    expectedGreen[5] = 1;
    expectedGreen[8] = 1;
    expectedGreen[11] = 1;

    assertArrayEquals(expectedGreen, this.histogram.getHistogram());

    this.histogram.createFromComponent(new BlueComponentAccesser());
    int[] expectedBlue = new int[256];
    expectedBlue[3] = 1;
    expectedBlue[6] = 1;
    expectedBlue[9] = 1;
    expectedBlue[12] = 1;

    assertArrayEquals(expectedBlue, this.histogram.getHistogram());

    this.histogram.createFromComponent(new IntensityComponentAccesser());
    int[] expectedIntensity = new int[256];
    expectedIntensity[2] = 1;
    expectedIntensity[5] = 1;
    expectedIntensity[6] = 1;
    expectedIntensity[11] = 1;

    assertArrayEquals(expectedIntensity, this.histogram.getHistogram());
  }
}
