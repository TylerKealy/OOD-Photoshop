package commands;

import model.PhotoshopModel;

/**
 * A SaveCommand is a PhotoshopCommand that saves an image to your machine.
 */
public class SaveCommand implements PhotoshopCommand {

  private final String directory;
  private final String name;
  private final PhotoshopModel model;

  /**
   * Constructor for information needed regarding executing a Save Command.
   *
   * @param directory directory to save to.
   * @param name      name of image to save.
   */
  public SaveCommand(PhotoshopModel model, String directory, String name) {
    this.model = model;
    this.directory = directory;
    this.name = name;
  }

  @Override
  public void run() {
    model.saveImage(directory, name);
  }
}
