import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.imagereading.ImageUtil;
import imageprocessor.model.imagereading.PPMUtil;
import imageprocessor.model.imagereading.StandardFormatsUtil;

import static org.junit.Assert.assertEquals;

/**
 * Test class for image util.
 */
public class TestImageUtil {
  private IImage expectedImg;
  private ImageUtil utilPPM;
  private ImageUtil utilPng;
  private ImageUtil utilJpg;
  private ImageUtil utilJpeg;
  private ImageUtil utilBmp;

  @Before
  public void setup() {
    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(255,0,0));
    pixelGrid.get(0).add(new RGBPixel(0,255,0));
    pixelGrid.get(0).add(new RGBPixel(0,0,255));
    pixelGrid.get(1).add(new RGBPixel(255,255,0));
    pixelGrid.get(1).add(new RGBPixel(255,255,255));
    pixelGrid.get(1).add(new RGBPixel(0,0,0));
    this.expectedImg = new RGBImage("", pixelGrid);

    this.utilPPM = new PPMUtil();
    this.utilPng = new StandardFormatsUtil("png");
    this.utilJpg = new StandardFormatsUtil("jpg");
    this.utilJpeg = new StandardFormatsUtil("jpeg");
    this.utilBmp = new StandardFormatsUtil("bmp");
  }

  @Test
  public void testGetExtensionFromPath() {
    assertEquals("png", ImageUtil.getFileExtension("testFile.png"));
    assertEquals("png", ImageUtil.getFileExtension("dir1/dir2/dir3/img.png"));

    assertEquals("jpg", ImageUtil.getFileExtension("testFile.jpg"));
    assertEquals("jpg", ImageUtil.getFileExtension("dir1/dir2/dir3/img.jpg"));


    assertEquals("ppm", ImageUtil.getFileExtension("testFile.ppm"));
    assertEquals("ppm", ImageUtil.getFileExtension("dir1/dir2/dir3/img.ppm"));

    assertEquals("123456", ImageUtil.getFileExtension("testFile.123456"));
    assertEquals("123456", ImageUtil.getFileExtension("dir1/dir2/dir3/img.123456"));

    assertEquals("123456", ImageUtil.getFileExtension("testFile.hello.123456"));
    assertEquals("123456", ImageUtil.getFileExtension("dir1/dir2/dir3/img.hello.123456"));
  }

  @Test
  public void testReadWritePPM() {
    IImage res = this.utilPPM.read("test/testImages/basicImg.ppm");
    assertEquals(this.expectedImg, res);

    this.utilPPM.write("test/testImages/basicImg-ReadWritePPM.ppm", res);
    IImage res2 = this.utilPPM.read("test/testImages/basicImg-ReadWritePPM.ppm");

    assertEquals(res, res2);
  }

  @Test
  public void testReadWriteFromPPMToStandard() {
    IImage res = this.utilPPM.read("test/testImages/basicImg.ppm");
    assertEquals(this.expectedImg, res);

    this.utilPng.write("test/testImages/basicImg-ReadWriteFromPPMToStandard.png", res);
    IImage res2 = this.utilPng.read("test/testImages/basicImg-ReadWriteFromPPMToStandard.png");

    assertEquals(res, res2);

    IImage resJpg = this.utilJpg.read("test/testImages/basicImg-Result.jpg");
    this.utilPng.write("test/testImages/basicImg-ReadWriteFromPPMToStandard2.png", resJpg);
    IImage res3 = this.utilPng.read("test/testImages/basicImg-ReadWriteFromPPMToStandard2.png");
    assertEquals(res3, resJpg);

    IImage resJpeg = this.utilJpeg.read("test/testImages/basicImg-Result.jpeg");
    this.utilPng.write("test/testImages/basicImg-ReadWriteFromPPMToStandard3.png", resJpeg);
    IImage res4 = this.utilPng.read("test/testImages/basicImg-ReadWriteFromPPMToStandard3.png");
    assertEquals(res4, resJpeg);

    IImage resBmp = this.utilBmp.read("test/testImages/basicImg-Result.bmp");
    this.utilPng.write("test/testImages/basicImg-ReadWriteFromPPMToStandard4.png", resBmp);
    IImage res5 = this.utilPng.read("test/testImages/basicImg-ReadWriteFromPPMToStandard4.png");
    assertEquals(res5, resBmp);
  }

  @Test
  public void testReadWriteFromStandardToPPM() {
    IImage resPng = this.utilPng.read("test/testImages/basicImg-res2.png");
    assertEquals(this.expectedImg, resPng);
    this.utilPPM.write("test/testImages/basicImg-ReadWriteFromStandardToPPM.ppm", resPng);
    IImage res1 = this.utilPPM.read("test/testImages/basicImg-ReadWriteFromStandardToPPM.ppm");
    assertEquals(resPng, res1);

    IImage resJpg = this.utilJpg.read("test/testImages/basicImg-res2.jpg");
    this.utilPPM.write("test/testImages/basicImg-ReadWriteFromStandardToPPM2.ppm", resJpg);
    IImage res2 = this.utilPPM.read("test/testImages/basicImg-ReadWriteFromStandardToPPM2.ppm");
    assertEquals(resJpg, res2);

    IImage resJpeg = this.utilJpg.read("test/testImages/basicImg-res2.jpeg");
    this.utilPPM.write("test/testImages/basicImg-ReadWriteFromStandardToPPM3.ppm", resJpeg);
    IImage res3 = this.utilPPM.read("test/testImages/basicImg-ReadWriteFromStandardToPPM3.ppm");
    assertEquals(resJpeg, res3);

    IImage resBmp = this.utilBmp.read("test/testImages/basicImg-res2.bmp");
    this.utilPPM.write("test/testImages/basicImg-ReadWriteFromStandardToPPM4.ppm", resJpeg);
    IImage res4 = this.utilPPM.read("test/testImages/basicImg-ReadWriteFromStandardToPPM4.ppm");
    assertEquals(resBmp, res4);
  }

}

