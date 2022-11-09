package commands;

import model.Kernels.IKernel;
import model.PhotoshopModel;
import model.PhotoshopModelPro;

public class TransformCommand implements PhotoshopCommand {
  String sourceName;
  String destName;
  IKernel matrix;
  public TransformCommand(IKernel matrix, String sourceName, String destName) {
    this.matrix = matrix;
    this.sourceName = sourceName;
    this.destName = destName;
  }

  @Override
  public void run(PhotoshopModel model) {
    PhotoshopModelPro pro = (PhotoshopModelPro) model;
    pro.transform(sourceName, destName, matrix);
  }
}
