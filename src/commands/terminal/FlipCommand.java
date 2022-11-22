package commands.terminal;

import model.enums.Direction;
import model.PhotoshopModel;

/**
 * FlipCommand is a PhotoshopCommand that executes the flip method on the model.
 */
public class FlipCommand implements PhotoshopCommand {
  public Direction dir;
  private final String sourceName;
  private final String destName;

  private final PhotoshopModel model;

  /**
   * Constructor for information needed regarding executing a Flip Command.
   *
   * @param dir        which direction to flip.
   * @param sourceName source image name.
   * @param destName   exported image name.
   */
  public FlipCommand(PhotoshopModel model, Direction dir, String sourceName, String destName) {
    this.model = model;
    this.dir = dir;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run() {
    model.flipImage(dir, sourceName, destName);
  }
}
