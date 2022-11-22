package view;

import java.awt.image.BufferedImage;

import controller.PhotoshopFeatures;

public interface GUIView {

  String getTerminalInput();

  void setFeatures(PhotoshopFeatures features);

  void resetTerminalText();

  void setImage(BufferedImage image);

  String getDialogInput();




}
