package commands.gui;

import commands.terminal.BrightenCommand;
import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import view.PhotoshopGUIView;

/**
 * GUI brighten command. Waits for information about how much to brighten before running.
 */
public class GUIBrightenCommand extends AGUICommand {

  BrightenCommand brightenCommand;

  public GUIBrightenCommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    super(model, gui);
  }

  @Override
  protected void onDialogUpdated() {
    brightenCommand.increment = Integer.parseInt(gui.getDialogInput());
  }

  @Override
  public PhotoshopCommand initializeCommand() {
    brightenCommand = new BrightenCommand(model, 0,
            model.getRecentImageName(), model.getRecentImageName());
    return brightenCommand;
  }

}
