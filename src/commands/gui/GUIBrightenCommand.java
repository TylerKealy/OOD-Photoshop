package commands.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commands.terminal.BrightenCommand;
import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import view.PhotoshopGUIView;

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
