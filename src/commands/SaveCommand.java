package commands;

import controller.PhotoshopCommand;
import model.PhotoshopModel;

/**
 * A SaveCommand is a PhotoshopCommand that saves an image to your machine.
 */
public class SaveCommand implements PhotoshopCommand {

  private final String directory;
  private final String name;

  public SaveCommand(String directory, String name) {
    this.directory = directory;
    this.name = name;
  }

  @Override
  public void run(PhotoshopModel model) {
    model.saveImage(directory, name);
  }
}
