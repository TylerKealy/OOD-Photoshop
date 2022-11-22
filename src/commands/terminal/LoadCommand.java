package commands.terminal;

import model.PhotoshopModel;

/**
 * A LoadCommand is a PhotoshopCommand that loads an image into the model.
 */
public class LoadCommand implements PhotoshopCommand {

  private final String directory;
  private final String name;

  private final PhotoshopModel model;

  /**
   * Constructor for information needed regarding executing a Load Command.
   *
   * @param directory image to load.
   * @param name      saved, loaded name.
   */
  public LoadCommand(PhotoshopModel model, String directory, String name) {
    this.model = model;
    this.directory = directory;
    this.name = name;
  }

  @Override
  public void run() {
    model.loadImage(directory, name);
  }
}
