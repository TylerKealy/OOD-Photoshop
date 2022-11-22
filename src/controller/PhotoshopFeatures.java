package controller;

import commands.CommandTypes;

public interface PhotoshopFeatures {

  void runTerminalCommand();

  void runGUICommand(CommandTypes command);

}

