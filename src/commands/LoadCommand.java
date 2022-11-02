package commands;

import controller.PhotoshopCommand;
import model.PhotoshopModel;

/**
 * A LoadCommand is a PhotoshopCommand that loads an image into the model.
 */
public class LoadCommand implements PhotoshopCommand {

  private final String directory;
  private final String name;


  public LoadCommand(String directory, String name) {
    this.directory = directory;
    this.name = name;
  }

  @Override
  public void run(PhotoshopModel model) {
    model.loadImage(directory, name);
  }
}
