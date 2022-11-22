package commands.gui;

import commands.terminal.ComponentCommand;
import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import model.enums.ComponentGreyscale;
import view.PhotoshopGUIView;

/**
 * GUI command for Component. Waits to see WHICH from the GUI component before running the command.
 */
public class GUIComponentCommand extends AGUICommand {

  ComponentCommand componentCommand;

  public GUIComponentCommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    super(model, gui);
  }

  @Override
  protected void onDialogUpdated() {
    ComponentGreyscale updatedComponent = componentCommand.component;
    switch (gui.getDialogInput().toLowerCase()) {
      case "red":
        updatedComponent = ComponentGreyscale.Red;
        break;
      case "green":
        updatedComponent = ComponentGreyscale.Green;
        break;
      case "blue":
        updatedComponent = ComponentGreyscale.Blue;
        break;
      case "intensity":
        updatedComponent = ComponentGreyscale.Intensity;
        break;
      case "value":
        updatedComponent = ComponentGreyscale.Value;
        break;
      case "luma":
        updatedComponent = ComponentGreyscale.Luma;
        break;
      default:
        updatedComponent = componentCommand.component;
    }
    componentCommand.component = updatedComponent;
  }

  @Override
  public PhotoshopCommand initializeCommand() {
    componentCommand = new ComponentCommand(model, null,
            model.getRecentImageName(), model.getRecentImageName());
    return componentCommand;
  }
}
