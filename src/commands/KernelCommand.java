package commands;

import model.kernels.IKernel;
import model.PhotoshopModelPro;

/**
 * A KernelCommand is a command that gives a kernel and executes the kernel method
 * from the PhotoshopModelPro model.
 */
public class KernelCommand implements PhotoshopCommand {
  private final String sourceName;
  private final String destName;
  private final IKernel kernel;

  private final PhotoshopModelPro model;

  /**
   * Takes in all necessary parameters for the model.kernel() method.
   * @param model The model to run this command on
   * @param kernel The kernel to apply to the source image.
   * @param sourceName the name of the source image.
   * @param destName the name of output image.
   */
  public KernelCommand(PhotoshopModelPro model,
                       IKernel kernel, String sourceName, String destName) {
    this.model = model;
    this.kernel = kernel;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run() {
    model.kernel(sourceName, destName, kernel);
  }
}
