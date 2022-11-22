package controller;

import commands.GUICommandTypes;

/**
 * All features needed to be implemented by the GUI Controller.
 */
public interface PhotoshopFeatures {

  void runTerminalCommand();

  void runGUICommand(GUICommandTypes command);

}

