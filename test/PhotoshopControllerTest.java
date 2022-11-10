import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import controller.APhotoshopController;
import controller.PhotoshopControllerStarter;
import model.MockPhotoshopModel;
import view.PhotoshopView;

import static org.junit.Assert.assertEquals;

/**
 * Class used to test that the Controller is relaying information correctly
 * and calling the correct model methods.
 */
public class PhotoshopControllerTest {

  private MockPhotoshopModel model;
  private PhotoshopView view = null;
  private Appendable ap;
  private StringReader sr;
  private APhotoshopController controller;

  @Before
  public void init() {
    sr = new StringReader("");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);
  }

  @Test
  public void testLoad() {
    sr = new StringReader("load name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(), "loadImage imagePath:name imageName: destname");
  }

  @Test
  public void testSave() {
    sr = new StringReader("save directory name");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(), "saveImage imagePath:directory imageName: name");
  }

  @Test
  public void testFlip() {
    sr = new StringReader("horizontal-flip name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "flipImage flipDirection:Horizontal imageName: name destImageName: destname");

    sr = new StringReader("vertical-flip name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "flipImage flipDirection:Vertical imageName: name destImageName: destname");
  }


  @Test
  public void testBadInput() {
    sr = new StringReader("hello I'm bad input");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    try {
      controller.run();
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "Improper command.");
    }
  }

  @Test
  public void testBrighten() {
    sr = new StringReader("brighten 20 name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "brighten increment:20 imageName: name destImageName: destname");

    sr = new StringReader("brighten -20 name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "brighten increment:-20 imageName: name destImageName: destname");
  }

  @Test
  public void testGreyscale() {
    sr = new StringReader("red-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Red imageName: name destImageName: destname");

    sr = new StringReader("blue-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Blue imageName: name destImageName: destname");


    sr = new StringReader("green-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Green imageName: name destImageName: destname");


    sr = new StringReader("value-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Value imageName: name destImageName: destname");


    sr = new StringReader("intensity-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Intensity imageName: name destImageName: destname");


    sr = new StringReader("luma-component name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerStarter(model, view, sr);

    controller.run();
    assertEquals(ap.toString(),
            "greyscaleComponent color:Luma imageName: name destImageName: destname");

  }



}
