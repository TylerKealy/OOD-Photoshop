package view;

import java.awt.image.BufferedImage;

import controller.PhotoshopFeatures;

/**
 * The interface for GUIView. Allows for mutation of its buttons/fields.
 */
public interface GUIView {

  String getTerminalInput();

  void setFeatures(PhotoshopFeatures features);

  void resetTerminalText();

  void setImage(BufferedImage image);

  String getDialogInput();




}
