package controller;

import model.PhotoshopModel;
import view.PhotoshopView;

/**
 * A PhotoshopControllerStarter is an APhotoshopController that can only run the default commands
 * provided in APhotoshopController.
 */
public class PhotoshopControllerStarter extends APhotoshopController {
  /**
   * Constructor to create a PhotoshopController. Runs the model.
   *
   * @param model model to run.
   * @param view  view to show program.
   * @param rd    user input.
   */
  public PhotoshopControllerStarter(PhotoshopModel model, PhotoshopView view, Readable rd) {
    super(model, view, rd);
  }
}
