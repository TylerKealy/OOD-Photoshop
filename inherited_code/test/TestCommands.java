import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.model.commands.ApplyTransformation;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.commands.LoadImage;
import imageprocessor.model.commands.SaveImage;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.mocks.ImageCollectionMock;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.imageoperations.mocks.TransformationMock;

import static org.junit.Assert.assertEquals;

/**
 * Test class for commands.
 */
public class TestCommands {
  private ImageCollection collection;
  private Appendable sbMockModel;
  private IImage image1;

  @Before
  public void setup() {
    this.sbMockModel = new StringBuilder();
    this.collection = new ImageCollectionMock(this.sbMockModel);

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
  }

  @Test
  public void testApplyTransformation() {
    ImageTransformation effect = new TransformationMock(this.sbMockModel);
    ICommand transform = new ApplyTransformation(this.collection,
            this.image1, "new name", effect);

    transform.execute();

    assertEquals("transform: image=one\n" +
            "addImage: image=new name\n" +
            "setSelectedImage(): name=new name\n", this.sbMockModel.toString());

    transform.execute();
    assertEquals("transform: image=one\n" +
            "addImage: image=new name\n" +
            "setSelectedImage(): name=new name\n" +
            "transform: image=new name\n" +
            "addImage: image=new name\n" +
            "setSelectedImage(): name=new name\n", this.sbMockModel.toString());
  }

  @Test
  public void testLoadImage() {
    ICommand load = new LoadImage(this.collection,
            "test/testImages/basicImg.ppm", "new name");

    load.execute();
    assertEquals("addImage: image=new name\n" +
            "setSelectedImage(): name=new name\n", this.sbMockModel.toString());

  }

  @Test
  public void testSaveImage() {
    ICommand save = new SaveImage(this.collection,
            "test/testImages/basicImg-res.ppm", "one");

    save.execute();
    assertEquals("getImage: name=one\n", this.sbMockModel.toString());

  }
}
