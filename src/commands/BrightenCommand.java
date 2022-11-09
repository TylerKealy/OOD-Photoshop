package commands;

import model.PhotoshopModel;

/**
 * BrightenCommand is a Photoshop command that executes the brighten method on the model.
 */
public class BrightenCommand implements PhotoshopCommand {

  private final int increment;
  private final String source;
  private final String export;
  private final PhotoshopModel model;

  /**
   * Constructor for information needed regarding executing a Brighten Command.
   * @param increment how much to brighten.
   * @param sourcename source image name.
   * @param exportName exported image name.
   */
  public BrightenCommand(PhotoshopModel model, int increment, String sourcename, String exportName) {
    this.model = model;
    this.increment = increment;
    this.source = sourcename;
    this.export = exportName;
  }

  @Override
  public void run() {
    System.out.println("got to run");
    model.brighten(increment, source, export);
  }
}
