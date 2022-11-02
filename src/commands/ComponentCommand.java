package commands;

import controller.PhotoshopCommand;
import model.ComponentGreyscale;
import model.PhotoshopModel;

/**
 * ComponentCommand is a Photoshop command that executes the greyscaleComponent method on the model.
 */
public class ComponentCommand implements PhotoshopCommand {

  private final ComponentGreyscale component;
  private final String sourceName;
  private final String destName;

  public ComponentCommand(ComponentGreyscale component, String sourceName, String destName) {
    this.component = component;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run(PhotoshopModel model) {
    model.greyscaleComponent(component, sourceName, destName);
  }
}
