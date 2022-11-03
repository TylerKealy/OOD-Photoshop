package commands;

import controller.PhotoshopCommand;
import model.Direction;
import model.PhotoshopModel;

/**
 * FlipCommand is a PhotoshopCommand that executes the flip method on the model.
 */
public class FlipCommand implements PhotoshopCommand {
  private final Direction dir;
  private final String sourceName;
  private final String destName;

  /**
   * Constructor for information needed regarding executing a Flip Command.
   *
   * @param dir        which direction to flip.
   * @param sourceName source image name.
   * @param destName   exported image name.
   */
  public FlipCommand(Direction dir, String sourceName, String destName) {
    this.dir = dir;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run(PhotoshopModel model) {
    model.flipImage(dir, sourceName, destName);
  }
}
