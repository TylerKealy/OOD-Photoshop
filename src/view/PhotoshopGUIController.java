package view;

import java.util.Objects;
import java.util.Scanner;

import commands.LoadCommand;
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
  public void performCommand() {
    Scanner scan = new Scanner(gui.getTerminalInput());
    if(!scan.hasNext()) return;
    runCommand(scan);
    gui.resetTerminalText();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void loadImage() {
    System.out.println("yo");
    new LoadCommand(this.guiModel, Objects.requireNonNull(gui.getFileLoaction()), "loaded").run();
    gui.setImage(guiModel.getRecentImage());
  }

  @Override
  public void run() {

  }

}
