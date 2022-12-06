package imageprocessor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import imageprocessor.model.commands.ApplyTransformation;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.imageoperations.imagetransformations.FilterAllPixels;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.imageoperations.imagetransformations.TransformAllPixels;
import imageprocessor.model.imageoperations.imagetransformations.TranslateAllPixels;
import imageprocessor.model.imageoperations.pixelaccessers.FlipHorizontally;
import imageprocessor.model.imageoperations.pixelaccessers.FlipVertically;
import imageprocessor.model.imageoperations.pixelaccessers.PixelAccesser;
import imageprocessor.model.imageoperations.pixelfilters.GaussianBlur;
import imageprocessor.model.imageoperations.pixelfilters.Mosaic;//FOR TESTING MUST REMOVE
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

/**
 * Represents the controller for the gui. Performs transformations based on an action listener
 * that listens for button clicks.
 */
public class GUIController implements ActionListener, IGUIController {
  private ImageCollection model;
  private IGUIView view;

  /**
   * Creates a new Gui controller.
   * @param view the view that is being controlled
   * @param model the model that the view is implementing
   */
  public GUIController(IGUIView view, ImageCollection model) {
    this.view = view;
    this.model = model;
    this.view.setActionListener(this);
  }

  /**
   * Takes in an event and performs the corresponding change.
   * @param argument the event to be processed
   */
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
        //FOR TESTING MUST REMOVE
        case "Mosaic": {
          String name = this.model.getSelectedImage();
          IImage image = model.getImage(name);
          PixelFilter effect = new Mosaic(20, image);
          cmd = this.createFilterAllPixels(effect);
          break;
        }
        //
        default: {
          // not possible under normal circumstances due to button based gui.
          throw new IllegalStateException("unknown button case statement");
        }
      }
      cmd.execute();
      this.view.updateData();
    }
    catch (IllegalStateException e) {
      // no effect necessary for catching exception.
    }
  }

  /**
   * Creates a filter transformation that edits individual pixels to help the controller
   * apply transformations.
   *
   * @param pixelFil the interface for transformations that edit individual pixels which holds
   *                   the desired transformation that will be performed on the image.
   *                   image.
   * @return the applied filter of the command.
   */
  private ApplyTransformation createFilterAllPixels(PixelFilter pixelFil) {
    String name = this.model.getSelectedImage();
    IImage img = model.getImage(name);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation filterAll = new FilterAllPixels(pixelFil);
    return new ApplyTransformation(this.model, copy, name, filterAll);
  }

  /**
   * Creates a transform transformation that edits individual pixels to help the controller
   * apply transformations.
   *
   * @param pixelManip the interface for transformations that edit individual pixels which holds
   *                   the desired transformation that will be performed on the image.
   *                   image.
   * @return the applied transformation of the command.
   */
  private ApplyTransformation createTransformAllPixels(PixelTransformation pixelManip) {
    String name = this.model.getSelectedImage();
    IImage img = model.getImage(name);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation transformAll = new TransformAllPixels(pixelManip);
    return new ApplyTransformation(this.model, copy, name, transformAll);
  }

  /**
   * Creates a translate transformation that doesn't edit individual pixels to help the controller
   * apply transformations.
   *
   * @param accesser the interface for transformations that doesn't edit individual pixels which
   *                 holds the desired transformation that will be performed on the image.
   * @return the applied transformation of the command.
   */
  private ApplyTransformation createTranslateAllPixels(PixelAccesser accesser) {
    String name = this.model.getSelectedImage();
    IImage img = model.getImage(name);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation translateAll = new TranslateAllPixels(accesser);
    return new ApplyTransformation(this.model, copy, name, translateAll);
  }

  /**
   * Takes in a command and executes it.
   * @param command the given command
   */
  public void acceptCommand(ICommand command) {
    command.execute();
  }
}
