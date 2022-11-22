package view;

import java.util.Scanner;

import commands.CommandTypes;
import commands.gui.GUIBrightenCommand;
import commands.gui.GUIComponentCommand;
import commands.gui.GUIFlipCommand;
import commands.gui.GUIKernelCommand;
import commands.gui.GUITransformCommand;
import commands.terminal.LoadCommand;
import commands.terminal.SaveCommand;
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
    String loc = gui.fileDirectory("Select a file to load.", false);
    if (loc == null) {
      return;
    }
    new LoadCommand(this.guiModel, loc, "loaded").run();
    gui.setImage(guiModel.getRecentImage());
  }

  private void saveImage() {
    String loc = gui.fileDirectory("Select a directory to save to, and name your file.", true);
    if (loc == null) {
      return;
    }
    new SaveCommand(this.guiModel, loc, this.guiModel.getRecentImageName()).run();
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
      case Component:
        gui.dialogDropdown("component type:", new String[]{"Red", "Green", "Blue",
                        "Value", "Intensity", "Luma"},
                new GUIComponentCommand(this.guiModel, this.gui));
        break;
      case Kernel:
        gui.dialogDropdown("kernel type:", new String[]{"Blur", "Sharpen"},
                new GUIKernelCommand(this.guiModel, this.gui));
        break;
      case Transform:
        gui.dialogDropdown("transform type:", new String[]{"Sepia", "Greyscale"},
                new GUITransformCommand(this.guiModel, this.gui));
        break;
      case Load:
        loadImage();
        break;
      case Save:
        saveImage();
        break;
    }
  }

  @Override
  public void run() {
  }

}
