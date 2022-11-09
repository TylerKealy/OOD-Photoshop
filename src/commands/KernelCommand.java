package commands;

import model.Kernels.BlurKernel;
import model.Kernels.IKernel;
import model.PhotoshopModel;
import model.PhotoshopModelPro;

public class KernelCommand implements PhotoshopCommand {
  String sourceName;
  String destName;
  IKernel kernel;
  public KernelCommand(IKernel kernel, String sourceName, String destName) {
    this.kernel = kernel;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run(PhotoshopModel model) {
    PhotoshopModelPro pro = (PhotoshopModelPro) model;
    pro.kernel(sourceName, destName, kernel);
  }
}
