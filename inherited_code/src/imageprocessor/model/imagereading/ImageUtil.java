package imageprocessor.model.imagereading;

import imageprocessor.model.components.image.IImage;


/**
 * Allows for read and write operations on images.
 */
public interface ImageUtil {

  /**
   * Read an image file and create an image from its contents.
   *
   * @param filename the path of the file.
   * @return operation.IImage an image constructed from the file's pixels
   */
  public IImage read(String filename);

  /**
   * Write an image to the contents of an image file.
   *
   * @param path the path of the image to write to
   * @param image the image being used
   */
  public void write(String path, IImage image);

  /**
   * From a file name, gets the file's extension.
   * @param filename the name of an image file
   * @return the extension of the file
   */
  public static String getFileExtension(String filename) {
    String[] components = filename.split("\\.");
    if (components.length != 0) {
      return components[components.length - 1];
    }
    else {
      return "";
    }
  }
}

