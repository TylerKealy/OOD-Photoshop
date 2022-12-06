import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.imageoperations.imagetransformations.FilterAllPixels;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.imageoperations.imagetransformations.TransformAllPixels;
import imageprocessor.model.imageoperations.imagetransformations.TranslateAllPixels;
import imageprocessor.model.imageoperations.pixelaccessers.FlipHorizontally;
import imageprocessor.model.imageoperations.pixelaccessers.FlipVertically;
import imageprocessor.model.imageoperations.pixelfilters.GaussianBlur;
import imageprocessor.model.imageoperations.pixelfilters.Sharpen;
import imageprocessor.model.imageoperations.pixelmanipulators.ChangeBrightness;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleBlue;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleIntensity;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleMaxValue;
import imageprocessor.model.imageoperations.pixelmanipulators.SepiaTone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for transformations.
 */
public class TestTransformations {
  private IImage image1;
  private IImage image2;
  private RGBImage expectedImage;

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
    this.expectedImage = new RGBImage((RGBImage) this.image1);
    this.image2 = new RGBImage((RGBImage) this.image1);
    this.image2.setPixelAt(new RGBPixel(1,2,3), 0, 0);
    this.image2.setPixelAt(new RGBPixel(4,5,6), 0, 1);
    this.image2.setPixelAt(new RGBPixel(7,8,9), 1, 0);
    this.image2.setPixelAt(new RGBPixel(10,11,12), 1, 1);
  }

  @Test
  public void testHorizontalFlip() {
    this.expectedImage.setPixelAt(new RGBPixel(2,2,2), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(1,1,1), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(4,4,4), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(3,3,3), 1, 1);

    assertNotEquals(this.expectedImage, this.image1);

    ImageTransformation translateAll = new TranslateAllPixels(new FlipHorizontally());
    translateAll.transform(this.image1);

    assertEquals(this.expectedImage, this.image1);
  }

  @Test
  public void testVerticalFlip() {
    this.expectedImage.setPixelAt(new RGBPixel(1,1,1), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(2,2,2), 1, 1);
    this.expectedImage.setPixelAt(new RGBPixel(3,3,3), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(4,4,4), 0, 1);

    assertNotEquals(this.expectedImage, this.image1);

    ImageTransformation translateAll = new TranslateAllPixels(new FlipVertically());
    translateAll.transform(this.image1);

    assertEquals(this.expectedImage, this.image1);
  }

  @Test
  public void testBrighten() {
    this.expectedImage.setPixelAt(new RGBPixel(6,7,8), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(9,10,11), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(12,13,14), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(15,16,17), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new ChangeBrightness(5));

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);

    this.expectedImage.setPixelAt(new RGBPixel(6,7,8), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(9,10,11), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(12,13,14), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(15,16,17), 1, 1);

    assertNotEquals(orig, this.image2);

    transformAll = new TransformAllPixels(new ChangeBrightness(-5));
    transformAll.transform(this.image2);

    assertEquals(orig, this.image2);
  }

  @Test
  public void testBrightenExceedRanges() {
    this.expectedImage.setPixelAt(new RGBPixel(0,0,0), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(0,0,0), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(0,1,2), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(3,4,5), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new ChangeBrightness(-7));

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);

    this.expectedImage.setPixelAt(new RGBPixel(248,249,250), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(251,252,253), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(254,255,255), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(255,255,255), 1, 1);

    this.image2 = new RGBImage((RGBImage) orig);

    transformAll = new TransformAllPixels(new ChangeBrightness(247));
    transformAll.transform(this.image2);

    assertEquals(expectedImage, this.image2);
  }

  @Test
  public void testGreyScaleColor() {
    this.expectedImage.setPixelAt(new RGBPixel(3,3,3), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(6,6,6), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(9,9,9), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(12,12,12), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new GreyscaleBlue());

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);
  }

  @Test
  public void testGreyScaleCalculation1() {
    this.expectedImage.setPixelAt(new RGBPixel(3,3,3), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(6,6,6), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(9,9,9), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(12,12,12), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new GreyscaleMaxValue());

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);


  }

  @Test
  public void testGreyScaleCalculation2() {
    this.expectedImage.setPixelAt(new RGBPixel(2,2,2), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(5,5,5), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(8,8,8), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(11,11,11), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new GreyscaleIntensity());

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);
  }

  @Test
  public void testBlur() {
    this.expectedImage.setPixelAt(new RGBPixel(2,2,3), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(2,3,3), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(3,3,4), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(3,4,5), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation filterAll = new FilterAllPixels(new GaussianBlur());

    IImage orig = new RGBImage((RGBImage) this.image2);

    filterAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);
  }

  @Test
  public void testSharpen() {
    this.expectedImage.setPixelAt(new RGBPixel(6,8,9), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(8,10,12), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(10,12,14), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(13,14,16), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation filterAll = new FilterAllPixels(new Sharpen());

    IImage orig = new RGBImage((RGBImage) this.image2);

    filterAll.transform(this.image2);
    assertEquals(this.expectedImage, this.image2);
  }

  @Test
  public void testSepia() {
    this.expectedImage.setPixelAt(new RGBPixel(2,2,1), 0, 0);
    this.expectedImage.setPixelAt(new RGBPixel(6,5,4), 0, 1);
    this.expectedImage.setPixelAt(new RGBPixel(10,9,7), 1, 0);
    this.expectedImage.setPixelAt(new RGBPixel(14,13,10), 1, 1);

    assertNotEquals(this.expectedImage, this.image2);

    ImageTransformation transformAll = new TransformAllPixels(new SepiaTone());

    IImage orig = new RGBImage((RGBImage) this.image2);

    transformAll.transform(this.image2);

    assertEquals(this.expectedImage, this.image2);
  }

}
