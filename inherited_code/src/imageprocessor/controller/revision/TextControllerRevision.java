package imageprocessor.controller.revision;

import imageprocessor.controller.GUIController;
import imageprocessor.controller.TextController;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.commands.LoadImage;
import imageprocessor.model.commands.SaveImage;
import imageprocessor.model.commands.mocks.CommandMock;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.imageoperations.pixelaccessers.FlipHorizontally;
import imageprocessor.model.imageoperations.pixelaccessers.FlipVertically;
import imageprocessor.model.imageoperations.pixelaccessers.PixelAccesser;
import imageprocessor.model.imageoperations.pixelfilters.GaussianBlur;
import imageprocessor.model.imageoperations.pixelfilters.Mosaic;
import imageprocessor.model.imageoperations.pixelfilters.PixelFilter;
import imageprocessor.model.imageoperations.pixelfilters.Sharpen;
import imageprocessor.model.imageoperations.pixelmanipulators.ChangeBrightness;
import imageprocessor.model.imageoperations.pixelmanipulators.Darken;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleBlue;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleGreen;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleIntensity;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleLuma;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleMaxValue;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleRed;
import imageprocessor.model.imageoperations.pixelmanipulators.PixelTransformation;
import imageprocessor.model.imageoperations.pixelmanipulators.SepiaTone;
import imageprocessor.view.IView;
import imageprocessor.view.guiview.IGUIView;

public class TextControllerRevision extends TextController {
  public TextControllerRevision(Readable read, IView view) {
    super(read, view);
  }

  public TextControllerRevision(Readable read, IView view, ImageCollection collection) {
    super(read, view, collection);
  }

  public TextControllerRevision(Readable read, IView view, ImageCollection collection, Appendable app, boolean blockedIO) {
    super(read, view, collection, app, blockedIO);
  }

  @Override
  protected void executeCommandInput() {
    String command = this.scan.next();
    ImageTransformation transform;
    ICommand cmd = null;
    String[] args;

    switch (command) {
      case "load": {
        args = this.processInputs(2);
        String path = args[0];
        String name = args[1];
        if (this.blockedIO) {
          cmd = new CommandMock(command, this.app);
        } else {
          cmd = new LoadImage(this.collection, path, name);
        }
        break;
      }
      case "save": {
        args = this.processInputs(2);
        String path = args[0];
        String name = args[1];
        if (this.blockedIO) {
          cmd = new CommandMock(command, this.app);
        } else {
          cmd = new SaveImage(this.collection, path, name);
        }
        break;
      }
      case "vertical-flip": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelAccesser accesser = new FlipVertically();
        cmd = this.createTranslateAllPixels(oldName, newName, accesser);
        break;
      }
      case "horizontal-flip": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelAccesser accesser = new FlipHorizontally();
        cmd = this.createTranslateAllPixels(oldName, newName, accesser);
        break;
      }
      case "brighten": {
        args = this.processInputs(3);
        int value = Integer.parseInt(args[0]);
        String oldName = args[1];
        String newName = args[2];
        PixelTransformation effect = new ChangeBrightness(value);
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "darken": {
        args = this.processInputs(3);
        int value = Integer.parseInt(args[0]);
        String oldName = args[1];
        String newName = args[2];
        PixelTransformation effect = new Darken(value);
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "red-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleRed();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "green-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleGreen();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "blue-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleBlue();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "intensity-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleIntensity();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "luma-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleLuma();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "value-component": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleMaxValue();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "greyscale": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new GreyscaleLuma();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "sepia-tone": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelTransformation effect = new SepiaTone();
        cmd = this.createTransformAllPixels(oldName, newName, effect);
        break;
      }
      case "blur": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelFilter effect = new GaussianBlur();
        cmd = this.createFilterAllPixels(oldName, newName, effect);
        break;
      }
      case "sharpen": {
        args = this.processInputs(2);
        String oldName = args[0];
        String newName = args[1];
        PixelFilter effect = new Sharpen();
        cmd = this.createFilterAllPixels(oldName, newName, effect);
        break;
      }
      case "mosaic": {
        args = this.processInputs(3);
        int value = Integer.parseInt(args[0]);
        String oldName = args[1];
        String newName = args[2];
        PixelFilter effect = new Mosaic(value, collection.getImage(oldName));
        cmd = this.createFilterAllPixels(oldName, newName, effect);
        break;
      }
      default:
        throw new IllegalStateException("Invalid command");
    }
    cmd.execute();
  }

  public static class GUIControllerRevision extends GUIController {
    /**
     * Creates a new Gui controller.
     *
     * @param view  the view that is being controlled
     * @param model the model that the view is implementing
     */
    public GUIControllerRevision(IGUIView view, ImageCollection model) {
      super(view, model);
    }
  }
}
