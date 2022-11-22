package commands.gui;

import commands.terminal.FlipCommand;
import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import model.enums.Direction;
import view.PhotoshopGUIView;

/**
 * GUI Flip command. Waits to see which direction to flip before running.
 */
public class GUIFlipCommand extends AGUICommand {

  private FlipCommand flipCommand;

  public GUIFlipCommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    super(model, gui);
  }

  @Override
  protected void onDialogUpdated() {
    String input = gui.getDialogInput();
    if (input.equalsIgnoreCase("horizontal")) {
      flipCommand.dir = Direction.Horizontal;
    } else {
      flipCommand.dir = Direction.Vertical;
    }
  }

  @Override
  public PhotoshopCommand initializeCommand() {
    flipCommand = new FlipCommand(model, null, model.getRecentImageName(),
            model.getRecentImageName());
    return flipCommand;
  }
}
