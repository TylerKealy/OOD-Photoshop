package commands.terminal;

import model.kernels.AMatrix;
import model.PhotoshopModelPro;

/**
 * A TransformCommand is a command that gives an AMatrix and executes the transform method
 * from the PhotoshopModelPro model.
 */
public class TransformCommand implements PhotoshopCommand {
  String sourceName;
  String destName;
  public AMatrix matrix;

  private final PhotoshopModelPro model;

  /**
   * Takes in all necessary parameters for the model.transform() method.
   * @param model the model to run this command on.
   * @param matrix the matrix to apply to the model.
   * @param sourceName the name of the source image.
   * @param destName the name of the output image.
   */
  public TransformCommand(PhotoshopModelPro model,
                          AMatrix matrix, String sourceName, String destName) {
    this.model = model;
    this.matrix = matrix;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run() {
    model.transform(sourceName, destName, matrix);
  }
}
