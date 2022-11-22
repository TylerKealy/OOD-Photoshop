package commands.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import view.PhotoshopGUIView;

/**
 * Abstract class for a GUICommand. listens for when command has been given extra information, and
 * updates accordingly.
 */
public abstract class AGUICommand implements ActionListener, PhotoshopCommand {

  protected PhotoshopGUIView gui;
  protected PhotoshopGUIModelPro model;
  private PhotoshopCommand cmd;

  /**
   * Constructor needs access to both GUI model and GUI view to get info, and run the command.
   * @param model the GUI model for photoshop.
   * @param gui the gui view for photoshop.
   */
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
