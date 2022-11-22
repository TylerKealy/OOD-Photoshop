package view;

import java.util.Scanner;

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
    if(!scan.hasNext()) return;
    runCommand(scan);
    gui.resetTerminalText();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void loadImage() {
    String loc = gui.getFileLoaction();
    if(loc == null) {
      return;
    }
    new LoadCommand(this.guiModel, loc, "loaded").run();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void brightenImage() {
    gui.dialogTextField("brighten increment (number)", new GUIBrightenCommand(this.guiModel, this.gui));
  }

  @Override
  public void flipImage() {
    gui.dialogDropdown("flip type:", new String[]{"Horizontal", "Vertical"},
            new GUIFlipCommand(this.guiModel, this.gui));
  }

  @Override
  public void run() {
  }

}
