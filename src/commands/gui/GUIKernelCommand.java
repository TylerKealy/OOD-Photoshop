package commands.gui;

import commands.terminal.KernelCommand;
import commands.terminal.PhotoshopCommand;
import model.PhotoshopGUIModelPro;
import model.kernels.BlurKernel;
import model.kernels.SharpenKernel;
import view.PhotoshopGUIView;

public class GUIKernelCommand extends AGUICommand {

  KernelCommand kernelCommand;

  public GUIKernelCommand(PhotoshopGUIModelPro model, PhotoshopGUIView gui) {
    super(model, gui);
  }

  @Override
  protected void onDialogUpdated() {
    switch (gui.getDialogInput().toLowerCase()) {
      case "blur":
        this.kernelCommand.kernel = new BlurKernel();
        break;
      case "sharpen":
        this.kernelCommand.kernel = new SharpenKernel();
        break;
      default:
        this.kernelCommand.kernel = null;
    }
  }

  @Override
  public PhotoshopCommand initializeCommand() {
    this.kernelCommand = new KernelCommand(model, null, model.getRecentImageName(),
            model.getRecentImageName());

    return kernelCommand;
  }
}
