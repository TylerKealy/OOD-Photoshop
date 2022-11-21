package view;

import java.util.Scanner;

import controller.APhotoshopController;
import controller.PhotoshopControllerPro;
import model.PhotoshopGUIModel;
import model.PhotoshopModel;

public class PhotoshopGUIController extends PhotoshopControllerPro implements PhotoshopFeatures {

  PhotoshopGUIView gui;
  PhotoshopGUIModel guiModel;


  public PhotoshopGUIController(PhotoshopGUIModel guiModel, PhotoshopGUIView view) {
    super(guiModel, view);
    this.gui = view;
    this.guiModel = guiModel;
  }

  @Override
  public void performCommand() {
    Scanner scan = new Scanner(gui.getTerminalInput());
    if(!scan.hasNext()) return;
    runCommand(scan);
    gui.resetTerminalText();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void run() {

  }

}
