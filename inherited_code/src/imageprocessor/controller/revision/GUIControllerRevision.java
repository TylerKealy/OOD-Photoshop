package imageprocessor.controller.revision;

import java.awt.event.ActionEvent;

import imageprocessor.controller.GUIController;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;
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
import imageprocessor.view.guiview.IGUIView;
import imageprocessor.view.guiview.IGUIViewRevision;

public class GUIControllerRevision extends GUIController {

  IGUIViewRevision gui;
  String storedAction;
  String[] dialogArgs = null;

  /**
   * Creates a new Gui controller.
   *
   * @param view  the view that is being controlled
   * @param model the model that the view is implementing
   */
  public GUIControllerRevision(IGUIViewRevision view, ImageCollection model) {
    super(view, model);
    this.gui = view;
  }

  @Override
  public void actionPerformed(ActionEvent argument) {
    try {
      ICommand cmd = null;
      switch (argument.getActionCommand()) {
        case "Flip Vertically": {
          PixelAccesser accesser = new FlipVertically();
          cmd = this.createTranslateAllPixels(accesser);
          break;
        }
        case "Flip Horizontally": {
          PixelAccesser accesser = new FlipHorizontally();
          cmd = this.createTranslateAllPixels(accesser);
          break;
        }
        case "Brighten +10": {
          PixelTransformation effect = new ChangeBrightness(10);
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Darken +10": {
          PixelTransformation effect = new Darken(10);
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Red Greyscale": {
          PixelTransformation effect = new GreyscaleRed();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Green Greyscale": {
          PixelTransformation effect = new GreyscaleGreen();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Blue Greyscale": {
          PixelTransformation effect = new GreyscaleBlue();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Intensity Greyscale": {
          PixelTransformation effect = new GreyscaleIntensity();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Luma Greyscale":
        case "Greyscale": {
          PixelTransformation effect = new GreyscaleLuma();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Value Greyscale": {
          PixelTransformation effect = new GreyscaleMaxValue();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Sepia Tone": {
          PixelTransformation effect = new SepiaTone();
          cmd = this.createTransformAllPixels(effect);
          break;
        }
        case "Gaussian Blur": {
          PixelFilter effect = new GaussianBlur();
          cmd = this.createFilterAllPixels(effect);
          break;
        }
        case "Sharpen": {
          PixelFilter effect = new Sharpen();
          cmd = this.createFilterAllPixels(effect);
          break;
        }
        //ADDED
        case "Mosaic": {
          if(dialogArgs == null) {
            storedAction = "Mosaic";
            gui.dialogBox("Number of Seeds", this);
          }else {
            try {
              String name = this.model.getSelectedImage();
              IImage image = model.getImage(name);
              int seeds = Integer.parseInt(dialogArgs[0]);
              PixelFilter effect = new Mosaic(seeds, image);
              cmd = this.createFilterAllPixels(effect);
            }catch(NumberFormatException e) {
              throw new IllegalArgumentException("Seeds must be a number.");
            }
            dialogArgs = null;
          }

          break;
        }
        case "DialogBox": {
          this.dialogArgs = new String[] {gui.getDialogBoxText()};
          this.actionPerformed(
                  new ActionEvent(this, ActionEvent.ACTION_PERFORMED, storedAction));
          break;
        }
        //
        default: {
          // not possible under normal circumstances due to button based gui.
          throw new IllegalStateException("unknown button case statement");
        }
      }

      if(cmd != null) {
        cmd.execute();
        this.view.updateData();
      }
    }
    catch (IllegalStateException e) {
      // no effect necessary for catching exception.
    }
  }

}
