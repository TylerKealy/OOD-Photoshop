package view;

import java.util.Scanner;

import commands.CommandTypes;
import commands.gui.GUIBrightenCommand;
import commands.gui.GUIFlipCommand;
import commands.terminal.LoadCommand;
import controller.PhotoshopControllerPro;
import model.PhotoshopGUIModelPro;

public class PhotoshopGUIController extends PhotoshopControllerPro implements PhotoshopFeatures {

  PhotoshopGUIView gui;
  PhotoshopGUIModelPro guiModel;


  public PhotoshopGUIController(PhotoshopGUIModelPro guiModel, PhotoshopGUIView view) {
    super(guiModel, view);
    this.gui = view;
    this.guiModel = guiModel;
  }

  @Override
  public void runTerminalCommand() {
    Scanner scan = new Scanner(gui.getTerminalInput());
    if (!scan.hasNext()) return;
    runCommand(scan);
    gui.resetTerminalText();
    gui.setImage(guiModel.getRecentImage());
  }


  private void loadImage() {
    String loc = gui.getFileLocation();
    if (loc == null) {
      return;
    }
    new LoadCommand(this.guiModel, loc, "loaded").run();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void runGUICommand(CommandTypes command) {
    switch (command) {
      case Brighten:
        gui.dialogTextField("brighten increment (number)", new GUIBrightenCommand(this.guiModel, this.gui));
        break;
      case Flip:
        gui.dialogDropdown("flip type:", new String[]{"Horizontal", "Vertical"},
                new GUIFlipCommand(this.guiModel, this.gui));
        break;
      case Load:
        loadImage();
        break;
    }
  }

  @Override
  public void run() {
  }

}
