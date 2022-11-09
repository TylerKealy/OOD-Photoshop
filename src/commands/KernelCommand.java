package commands;

import model.Kernels.BlurKernel;
import model.Kernels.IKernel;
import model.PhotoshopModel;
import model.PhotoshopModelPro;

public class KernelCommand implements PhotoshopCommand {
  private final String sourceName;
  private final String destName;
  private final  IKernel kernel;

  private final PhotoshopModelPro model;
  public KernelCommand(PhotoshopModelPro model, IKernel kernel, String sourceName, String destName) {
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
