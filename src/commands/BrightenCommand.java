package commands;

import controller.PhotoshopCommand;
import model.PhotoshopModel;

/**
 * BrightenCommand is a Photoshop command that executes the brighten method on the model.
 */
public class BrightenCommand implements PhotoshopCommand {

  private final int increment;
  private final String source;
  private final String export;

  public BrightenCommand(int increment, String sourcename, String exportName) {
    this.increment = increment;
    this.source = sourcename;
    this.export = exportName;
  }

  @Override
  public void run(PhotoshopModel model) {
    System.out.println("got to run");
    model.brighten(increment, source, export);
  }
}
