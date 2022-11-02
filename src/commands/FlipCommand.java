package commands;

import controller.PhotoshopCommand;
import model.Direction;
import model.PhotoshopModel;

/**
 * FlipCommand is a PhotoshopCommand that executes the flip method on the model.
 */
public class FlipCommand implements PhotoshopCommand {
  Direction dir;
  String sourceName;
  String destName;
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
