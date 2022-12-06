package imageprocessor.controller;

import java.io.IOException;
import java.util.Scanner;

import imageprocessor.model.commands.mocks.CommandMock;
import imageprocessor.model.imageoperations.imagetransformations.FilterAllPixels;
import imageprocessor.model.imageoperations.pixelfilters.GaussianBlur;
import imageprocessor.model.imageoperations.pixelfilters.PixelFilter;
import imageprocessor.model.imageoperations.pixelfilters.Sharpen;
import imageprocessor.model.imageoperations.pixelmanipulators.ChangeBrightness;
import imageprocessor.model.imageoperations.pixelaccessers.FlipHorizontally;
import imageprocessor.model.imageoperations.pixelaccessers.FlipVertically;
import imageprocessor.model.imageoperations.pixelmanipulators.Darken;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleBlue;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleGreen;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleIntensity;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleLuma;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleMaxValue;
import imageprocessor.model.imageoperations.pixelmanipulators.GreyscaleRed;
import imageprocessor.model.imageoperations.pixelaccessers.PixelAccesser;
import imageprocessor.model.imageoperations.pixelmanipulators.PixelTransformation;
import imageprocessor.model.imageoperations.imagetransformations.TransformAllPixels;
import imageprocessor.model.imageoperations.imagetransformations.TranslateAllPixels;
import imageprocessor.model.commands.ApplyTransformation;
import imageprocessor.model.commands.ICommand;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.ImageMapCollection;
import imageprocessor.model.commands.LoadImage;
import imageprocessor.model.commands.SaveImage;
import imageprocessor.model.imageoperations.pixelmanipulators.SepiaTone;
import imageprocessor.view.IView;
import imageprocessor.view.textview.ITextView;

/**
 * Represents a controller that takes in a command and executes the respective software using the
 * directory of stored images.
 */
public class TextController implements ITextController {
  private ImageCollection collection;
  private Readable read;
  private IView view;
  private Scanner scan;
  private Appendable app;
  private boolean blockedIO;

  /**
   * Create a new controller with a given readable and view.
   *
   * @param read the input that represents the instructions of the user.
   * @param view the view that can render a message.
   */
  public TextController(Readable read, IView view) {
    this.collection = new ImageMapCollection();
    this.read = read;
    this.view = view;
    this.scan = new Scanner(read);
  }

  /**
   * Create a new controller with a given readable, view, and collection.
   *
   * @param read       the input that represents the instructions of the user.
   * @param view       the view that can render a message.
   * @param collection a map of the images that can be altered.
   */
  public TextController(Readable read, IView view, ImageCollection collection) {
    this(read, view);
    this.collection = collection;
  }

  /**
   * Create a new controller with a given readable, view, collection, appendable, and boolean.
   *
   * @param read       the input that represents the instructions of the user.
   * @param view       the view that can render a message.
   * @param collection a map of the images that can be altered.
   * @param app        an appendable that can be appended to.
   * @param blockedIO  if true, then bypass the input.
   */
  public TextController(Readable read, IView view, ImageCollection collection, Appendable app,
                        boolean blockedIO) {
    this(read, view, collection);
    this.read = read;
    this.blockedIO = blockedIO;
    this.app = app;
  }

  /** Executes a command based on input.
   *
   */
  private void executeCommandInput() {
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
      default:
        throw new IllegalStateException("Invalid command");
    }
    cmd.execute();
  }

  /**
   * Takes in the command and executes the command.
   * Throws exception if could not execute command
   */
  public void start() {
    this.transmitMessage("Enter a command:");
    while (this.scan.hasNext()) {
      try {
        this.executeCommandInput();
      } catch (IllegalStateException e) {
        this.transmitMessage("Invalid command!");
      }
      this.transmitMessage("Enter a command:");
    }
  }

  /**
   * Creates a filter transformation that edits individual pixels to help the controller
   * apply transformations.
   *
   * @param oldName    the old name of the image.
   * @param newName    the new name of the image.
   * @param pixelFil the interface for transformations that edit individual pixels which holds
   *                   the desired transformation that will be performed on the image.
   *                   image.
   * @return the applied filter of the command.
   */
  private ApplyTransformation createFilterAllPixels(String oldName, String newName,
                                                       PixelFilter pixelFil) {
    IImage img = collection.getImage(oldName);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation filterAll = new FilterAllPixels(pixelFil);
    return new ApplyTransformation(this.collection, copy, newName, filterAll);
  }

  /**
   * Creates a transform transformation that edits individual pixels to help the controller
   * apply transformations.
   *
   * @param oldName    the old name of the image.
   * @param newName    the new name of the image.
   * @param pixelManip the interface for transformations that edit individual pixels which holds
   *                   the desired transformation that will be performed on the image.
   *                   image.
   * @return the applied transformation of the command.
   */
  private ApplyTransformation createTransformAllPixels(String oldName, String newName,
                                                       PixelTransformation pixelManip) {
    IImage img = collection.getImage(oldName);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation transformAll = new TransformAllPixels(pixelManip);
    return new ApplyTransformation(this.collection, copy, newName, transformAll);
  }

  /**
   * Creates a translate transformation that doesn't edit individual pixels to help the controller
   * apply transformations.
   *
   * @param oldName  the old name of the image.
   * @param newName  the new name of the image.
   * @param accesser the interface for transformations that doesn't edit individual pixels which
   *                 holds the desired transformation that will be performed on the image.
   * @return the applied transformation of the command.
   */
  private ApplyTransformation createTranslateAllPixels(String oldName, String newName,
                                                       PixelAccesser accesser) {
    IImage img = collection.getImage(oldName);
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    IImage copy = img.copyImage();
    ImageTransformation translateAll = new TranslateAllPixels(accesser);
    return new ApplyTransformation(this.collection, copy, newName, translateAll);
  }

  /**
   * Takes in a number of inputs and stores them in an array.
   *
   * @param numArgs the number of inputs to process
   * @return the array of inputs
   */
  private String[] processInputs(int numArgs) {
    int count = 0;
    String[] inputs = new String[numArgs];
    while (count < numArgs) {
      if (!this.scan.hasNext()) {
        throw new IllegalStateException("End of input");
      }
      String in = this.scan.next();
      // ppm util will check if the input is valid
      inputs[count] = in;
      count++;
    }
    return inputs;
  }

  /**
   * Transmits a message to the ImageProcessor.model.view.
   * @param message represents a message to be sent to the ImageProcessor.model.view.
   * @throws IllegalStateException if the board is not correctly sent to the
   *                               ImageProcessor.model.view.
   */
  private void transmitMessage(String message) throws IllegalStateException {
    try {
      ((ITextView) view).renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Board not correctly sent to controller");
    }
  }


}
