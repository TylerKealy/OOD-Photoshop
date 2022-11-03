import java.io.InputStreamReader;

import controller.PhotoshopController;
import controller.PhotoshopControllerImpl;
import model.PhotoshopModel;
import model.PhotoshopModelImpl;
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
    PhotoshopModel model = new PhotoshopModelImpl();
    PhotoshopView view = null;
    PhotoshopController controller =
            new PhotoshopControllerImpl(model, view, new InputStreamReader(System.in));
    controller.run();
  }
}
