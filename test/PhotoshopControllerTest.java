import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.Scanner;

import controller.PhotoshopController;
import controller.PhotoshopControllerImpl;
import model.MockPhotoshopModel;
import model.PhotoshopModel;
import model.PhotoshopModelImpl;
import view.PhotoshopView;

import static org.junit.Assert.assertEquals;

public class PhotoshopControllerTest {

  private MockPhotoshopModel model;
  private PhotoshopView view = null;
  private Appendable ap;
  private StringReader sr;
  private PhotoshopControllerImpl controller;

  @Before
  public void init() {
    sr = new StringReader("");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerImpl(model, view, sr);
  }

  @Test
  public void testLoad() {
    sr = new StringReader("load name destname");
    ap = new StringBuilder();
    model = new MockPhotoshopModel(ap);
    view = null;
    controller = new PhotoshopControllerImpl(model, view, sr);

    controller.run();
    assertEquals(ap.toString(), "loadImage imagePath:name imageName: destname");
  }

}
