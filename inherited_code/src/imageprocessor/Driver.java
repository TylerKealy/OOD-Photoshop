package imageprocessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import imageprocessor.controller.GUIController;
import imageprocessor.controller.IController;
import imageprocessor.controller.ITextController;
import imageprocessor.controller.TextController;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.ImageMapCollection;
import imageprocessor.view.IView;
import imageprocessor.view.guiview.IGUIView;
import imageprocessor.view.guiview.SwingView;
import imageprocessor.view.textview.TextView;

/**
 * The Main method that starts the controller.
 */
public class Driver {
  /** Creates a File Reader based on a file name.
   *
   * @param fileName the name of a file
   * @return a readable that reads the file
   */
  private static Readable createFileReader(String fileName) {
    try {
      Readable in = new InputStreamReader(new FileInputStream(fileName));
      return in;
    }
    catch (IOException e) {
      throw new IllegalArgumentException("Cannot read from file");
    }
  }

  /**
   * Gets the users input stream.
   * @param args the passed in arguments
   * @return an input stream for either user input or file stream
   */
  private static Readable getInputStream(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("-file")) {
        if (i + 1 < args.length) {
          String fileName = args[i + 1];
          return Driver.createFileReader(fileName);
        }
      } else if (args[i].equalsIgnoreCase("-text")) {
        return new InputStreamReader(System.in);
      }
      else {
        System.out.println("Invalid arguments");
        System.exit(0);
      }
    }
    return null;
  }

  /**
   * Creates a view, input, and controller.
   * @param args the argument passed through.
   */
  public static void main(String[] args) {
    ImageCollection model = new ImageMapCollection();
    IView view;
    IController controller;
    Readable input = Driver.getInputStream(args);

    if (input == null) {
      view = new SwingView(model);
      controller = new GUIController((IGUIView) view, model);
    }
    else {
      view = new TextView(System.out);
      controller = new TextController(input, view);
      ((ITextController) controller).start();
    }
  }
}
