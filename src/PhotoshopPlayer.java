import java.io.InputStreamReader;
import java.io.StringReader;

import controller.PhotoshopController;
import controller.PhotoshopControllerImpl;
import model.PhotoshopModel;
import model.PhotoshopModelImpl;
import model.PhotoshopModelProImpl;
import view.PhotoshopView;

/**
 * Used to run Photoshop.
 */
public class PhotoshopPlayer {

  /**
   * Method used to run the Photoshop program.
   * @param args java stuff.
   */
  public static void main(String[] args) {
    PhotoshopModel model = new PhotoshopModelProImpl();
    PhotoshopView view = null;
    PhotoshopController controller;
    //controller = new PhotoshopControllerImpl(model, view, new StringReader("load images/dogs.jpg dogs save images/saved.ppm dogs"));
    controller = new PhotoshopControllerImpl(model, view, new InputStreamReader(System.in));
    controller.run();
  }
}
