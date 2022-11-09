import java.io.InputStreamReader;

import controller.PhotoshopController;
import controller.PhotoshopControllerPro;
import model.PhotoshopModelProImpl;
import view.PhotoshopView;

/**
 * Used to run Photoshop.
 */
public class PhotoshopPlayer {

  /**
   * Method used to run the Photoshop program.
   *
   * @param args java stuff.
   */
  public static void main(String[] args) {
    PhotoshopModelProImpl model = new PhotoshopModelProImpl();
    PhotoshopView view = null;
    PhotoshopController controller;
    //controller = new PhotoshopControllerImpl(model, view,
    // new StringReader("load images/dogs.jpg dogs save images/saved.bmp dogs"));
    controller = new PhotoshopControllerPro(model, view, new InputStreamReader(System.in));
    controller.run();
  }
}
