package controller;

import java.util.Scanner;

import commands.GUICommandTypes;
import commands.gui.GUIBrightenCommand;
import commands.gui.GUIComponentCommand;
import commands.gui.GUIFlipCommand;
import commands.gui.GUIKernelCommand;
import commands.gui.GUITransformCommand;
import commands.terminal.LoadCommand;
import commands.terminal.SaveCommand;
import model.PhotoshopGUIModelPro;
import view.PhotoshopGUIView;

/**
 * Photoshop GUI controller. Runs differently than normal controllers. Implements Photoshop
 * features.
 */
public class PhotoshopGUIController extends PhotoshopControllerPro implements PhotoshopFeatures {

  PhotoshopGUIView gui;
  PhotoshopGUIModelPro guiModel;

  /**
   * Constructor needs a GUI model and GUI view to interconnect the two.
   *
   * @param guiModel the model specifically for GUI version of photoshop.
   * @param view     the view specifically for GUI version of photoshop.
   */
  public PhotoshopGUIController(PhotoshopGUIModelPro guiModel, PhotoshopGUIView view) {
    super(guiModel, view);
    this.gui = view;
    this.guiModel = guiModel;
  }

  @Override
  public void runTerminalCommand() {
    Scanner scan = new Scanner(gui.getTerminalInput());
    if (!scan.hasNext()) {
      return;
    }
    runCommand(scan);
    gui.resetTerminalText();
    gui.setImage(guiModel.getRecentImage());
  }


  private void loadImage() {
    String loc = gui.fileDirectory("Select a file to load.", false);
    if (loc == null) {
      return;
    }
    try {
      new LoadCommand(this.guiModel, loc, "loaded").run();
    } catch (IllegalStateException e) {
      return;
    }
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
  public void runGUICommand(GUICommandTypes command) {
    switch (command) {
      case Brighten:
        gui.dialogTextField("brighten increment (number)",
                new GUIBrightenCommand(this.guiModel, this.gui));
        break;
      case Flip:
        gui.dialogDropdown("flip type:", new String[]{"Horizontal", "Vertical"},
                new GUIFlipCommand(this.guiModel, this.gui));
        break;
      case Component:
        gui.dialogDropdown("component type:",
                new String[]{"Red", "Green", "Blue", "Value", "Intensity", "Luma"},
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
      default:
        throw new IllegalStateException("Unknown command");
    }
  }

  @Override
  public void run() {
    //we want to overwrite the previous functionality for run to do nothing.
  }

}
