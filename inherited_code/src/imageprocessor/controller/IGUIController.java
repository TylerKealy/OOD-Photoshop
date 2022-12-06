package imageprocessor.controller;

import imageprocessor.model.commands.ICommand;

/**
 * Represents the controller for the gui. Performs transformations based on an action listener
 * that listens for button clicks.
 */
public interface IGUIController extends IController  {
  /**
   * Takes in a command and executes it.
   * @param command the given command
   */
  public void acceptCommand(ICommand command);
}
