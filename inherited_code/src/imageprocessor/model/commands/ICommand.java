package imageprocessor.model.commands;

/**
 * Represents a command that can be applied to an image.
 */
public interface ICommand {
  /**
   * Executes the desired operation on the image.
   */
  public void execute();
}
