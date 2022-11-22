package commands.gui;

import commands.terminal.PhotoshopCommand;
import commands.terminal.TransformCommand;
import model.PhotoshopGUIModelPro;
import model.kernels.BlurKernel;
import model.kernels.GreyscaleMatrix;
import model.kernels.SepiaMatrix;
import model.kernels.SharpenKernel;
import view.PhotoshopGUIView;

public class GUITransformCommand extends AGUICommand {

  TransformCommand transformCommand;

  public GUITransformCommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    super(model, gui);
  }

  @Override
  protected void onDialogUpdated() {
    switch (gui.getDialogInput().toLowerCase()) {
      case "sepia":
        this.transformCommand.matrix = new SepiaMatrix();
        break;
      case "greyscale":
        this.transformCommand.matrix = new GreyscaleMatrix();
        break;
      default:
        this.transformCommand.matrix = null;
    }
  }

  @Override
  public PhotoshopCommand initializeCommand() {
    this.transformCommand = new TransformCommand(model, null, model.getRecentImageName(),
            model.getRecentImageName());
    return transformCommand;
  }
}
