import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import imageprocessor.controller.ITextController;
import imageprocessor.controller.TextController;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.mocks.ImageCollectionMock;
import imageprocessor.view.IView;
import imageprocessor.view.textview.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for controller.
 */
public class TestTextController {
  private Appendable sbView;
  private Appendable sbModel;
  private ITextController controller;

  @Before
  public void setup() {
    this.inputToController("");
  }

  private void inputToController(String input) {
    Readable read = new StringReader(input);
    this.sbModel = new StringBuilder();
    this.sbView = new StringBuilder();
    ImageCollection modelMock = new ImageCollectionMock(this.sbModel);
    IView view = new TextView(this.sbView);
    this.controller = new TextController(read, view, modelMock, this.sbModel, true);
  }


  @Test
  public void testStartModelInteraction() {
    this.inputToController("load images/koala.ppm koala");
    this.controller.start();

    assertEquals("load\n", this.sbModel.toString());

    this.inputToController("load images/koala.ppm koala " +
                            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\nsave\n", this.sbModel.toString());


    this.inputToController("load images/koalas.ppm koala " +
            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\nsave\n", this.sbModel.toString());

    this.inputToController("load images/koalas.ppm koala " +
            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\nsave\n", this.sbModel.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-vertical\n" +
            "setSelectedImage(): name=koala-vertical\n" +
            "save\n", this.sbModel.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "brighten 10 koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-vertical\n" +
            "setSelectedImage(): name=koala-vertical\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-component\n" +
            "setSelectedImage(): name=koala-component\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-brighten\n" +
            "setSelectedImage(): name=koala-brighten\n" +
            "save\n", this.sbModel.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "invalid koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-vertical\n" +
            "setSelectedImage(): name=koala-vertical\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-component\n" +
            "setSelectedImage(): name=koala-component\n" +
            "save\n", this.sbModel.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "vertical-flip 10 koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("load\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-vertical\n" +
            "setSelectedImage(): name=koala-vertical\n" +
            "getImage: name=koala\n" +
            "addImage: image=koala-component\n" +
            "setSelectedImage(): name=koala-component\n" +
            "getImage: name=10\n" +
            "addImage: image=koala\n" +
            "setSelectedImage(): name=koala\n" +
            "save\n", this.sbModel.toString());

  }

  @Test
  public void testStartViewInteraction() {
    this.inputToController("load images/koala.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koala.ppm koala " +
            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());


    this.inputToController("load images/koalas.ppm koala " +
            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koalas.ppm koala " +
            "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "brighten 10 koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "invalid koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Invalid command!\n" +
            "Enter a command:\n" +
            "Invalid command!\n" +
            "Enter a command:\n" +
            "Invalid command!\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

    this.inputToController("load images/koalas.ppm koala "
            + "vertical-flip koala koala-vertical "
            + "value-component koala koala-component "
            + "vertical-flip 10 koala koala-brighten "
            + "save images/koala-new.ppm koala");
    this.controller.start();

    assertEquals("Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Enter a command:\n" +
            "Invalid command!\n" +
            "Enter a command:\n" +
            "Enter a command:\n", this.sbView.toString());

  }
}
