package commands;

import model.Kernels.IKernel;
import model.PhotoshopModel;
import model.PhotoshopModelPro;

public class TransformCommand implements PhotoshopCommand {
  String sourceName;
  String destName;
  IKernel matrix;

  private final PhotoshopModelPro model;
  public TransformCommand(PhotoshopModelPro model, IKernel matrix, String sourceName, String destName) {
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
