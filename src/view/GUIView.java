package view;

import java.awt.image.BufferedImage;

public interface GUIView {

  String getTerminalInput();

  void setFeatures(PhotoshopFeatures features);

  void resetTerminalText();

  void setImage(BufferedImage image);

  String getDialogInput();




}
