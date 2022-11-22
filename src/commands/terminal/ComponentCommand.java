package commands.terminal;

import model.enums.ComponentGreyscale;
import model.PhotoshopModel;

/**
 * ComponentCommand is a Photoshop command that executes the greyscaleComponent method on the model.
 */
public class ComponentCommand implements PhotoshopCommand {

  private final ComponentGreyscale component;
  private final String sourceName;
  private final String destName;

  private final PhotoshopModel model;

  /**
   * Constructor for information needed regarding executing a Component Command.
   *
   * @param component  which component to greyscale.
   * @param sourceName source image name.
   * @param destName   exported image name.
   */
  public ComponentCommand(PhotoshopModel model,
                          ComponentGreyscale component, String sourceName, String destName) {
    this.model = model;
    this.component = component;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run() {
    model.greyscaleComponent(component, sourceName, destName);
  }
}
