package imageprocessor.model.imagereading;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * An abstract class that adds a method to append data to an appendable.
 */
public abstract class ImageOperationsUtil implements ImageUtil {
  /**
   * Takes in an appendable and adds the given data and adds a new line.
   * @param app the given appendable that is being appended.
   * @param data the data that is appended on the appendable.
   */
  protected void writeLine(BufferedWriter app, String data) {
    try {
      app.append(data);
      app.append("\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("Invalid write to appendable");
    }
  }

}
