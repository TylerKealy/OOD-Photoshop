import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import imageprocessor.controller.GUIController;
import imageprocessor.controller.IGUIController;
import imageprocessor.model.commands.ApplyTransformation;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.commands.mocks.CommandMock;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.mocks.ImageCollectionMock;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.imageoperations.imagetransformations.TransformAllPixels;
import imageprocessor.model.imageoperations.pixelmanipulators.PixelTransformation;
import imageprocessor.model.imageoperations.pixelmanipulators.SepiaTone;
import imageprocessor.view.guiview.IGUIView;
import imageprocessor.view.mock.MockView;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the GUI Controller.
 */
public class TestGUIController {
  private Appendable sbModel;
  private Appendable sbView;
  private Appendable sbCommand;

  private IGUIController controller;
  private IImage image1;

  @Before
  public void setup() {
    this.sbModel = new StringBuilder();
    this.sbView = new StringBuilder();
    this.sbCommand = new StringBuilder();
    ImageCollection model = new ImageCollectionMock(this.sbModel);
    IGUIView view = new MockView(this.sbView);
    this.controller = new GUIController(view, model);

    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(1,2,3));
    pixelGrid.get(0).add(new RGBPixel(4,5,6));
    pixelGrid.get(1).add(new RGBPixel(1,8,9));
    pixelGrid.get(1).add(new RGBPixel(10,11,12));
    this.image1 = new RGBImage("one", pixelGrid);
  }

  @Test
  public void testAcceptCommandMock() {
    this.controller.acceptCommand(new CommandMock("basic", this.sbCommand));
    assertEquals("basic\n", sbCommand.toString());
    assertEquals("", sbModel.toString());
    assertEquals("setActionListener()\n", sbView.toString());
  }

  @Test
  public void testAcceptModelInteraction() {
    ImageCollection model = new ImageCollectionMock(this.sbModel);
    PixelTransformation sepia = new SepiaTone();
    ImageTransformation transform = new TransformAllPixels(sepia);
    ICommand cmd = new ApplyTransformation(model, this.image1,"image", transform);
    this.controller.acceptCommand(cmd);

    assertEquals("addImage: image=image\n" +
            "setSelectedImage(): name=image\n", sbModel.toString());
  }

  @Test
  public void testAcceptViewInteraction() {
    ImageCollection model = new ImageCollectionMock(this.sbModel);
    PixelTransformation sepia = new SepiaTone();
    ImageTransformation transform = new TransformAllPixels(sepia);
    ICommand cmd = new ApplyTransformation(model, this.image1,"image", transform);
    this.controller.acceptCommand(cmd);

    assertEquals("setActionListener()\n", sbView.toString());
  }

}
