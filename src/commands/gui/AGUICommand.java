package commands.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import view.PhotoshopGUIView;

public abstract class AGUICommand implements ActionListener, PhotoshopCommand {

  PhotoshopGUIView gui;
  PhotoshopGUIModelPro model;
  PhotoshopCommand cmd;

  public AGUICommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    this.gui = gui;
    this.model = model;
    this.cmd = initializeCommand();
  }

  protected abstract void onDialogUpdated();

  public abstract PhotoshopCommand initializeCommand();

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("DialogAction")) {
      onDialogUpdated();
      run();
      gui.setImage(model.getRecentImage());
    }
  }

  @Override
  public void run() {
    cmd.run();
  }
}
