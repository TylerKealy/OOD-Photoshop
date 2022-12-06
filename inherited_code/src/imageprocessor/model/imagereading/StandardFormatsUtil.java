package imageprocessor.model.imagereading;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageprocessor.model.components.image.IImage;

/**
 * Utils class for standard formats with an extension.
 */
public class StandardFormatsUtil extends ImageOperationsUtil {
  private String extension;

  /**
   * Creates a new Standard Formats Util class with a given extension.
   * @param extension the given extension
   */
  public StandardFormatsUtil(String extension) {
    this.extension = extension;
  }

  /**
   * Reads a file and returns the buffered image inside it.
   * This is a helper method.
   * @param f the file to read from
   * @return the buffered image contained in the file
   */
  private RenderedImage readFromFile(File f) {
    try {
      return ImageIO.read(f);
    }
    catch (IOException e) {
      throw new IllegalStateException("Invalid file");
    }
  }

  /**
   * Takes a filename and returns the image inside it.
   * @param filename the path of the file.
   * @return the image from the file
   */
  @Override
  public IImage read(String filename) {
    File f = new File(filename);
    RenderedImage bufImg = this.readFromFile(f);
    IImage img = IImage.createFromBufferedImage(this.extension, (BufferedImage) bufImg);
    return img;
  }

  /**
   * Takes in an image and a path, and uses it to write a new file containing that information.
   * @param img the image being used
   * @param path the given path
   */
  private void writeToFile(RenderedImage img, String path) {
    try {
      File f = new File(path);
      f.createNewFile();
      ImageIO.write(img, this.extension, f);
    }
    catch (IOException e) {
      throw new IllegalStateException("Could not write to file");
    }
  }

  /**
   * Get the buffered converter from the given extension.
   * @param extension the given extension
   * @return the extension's buffered converter
   */
  private int getTypeFromExtension(String extension) {
    int type = 0;
    switch (this.extension) {
      case "png":
        type = BufferedImage.TYPE_INT_ARGB;
        break;
      case "jpeg":
        type = BufferedImage.TYPE_INT_RGB;
        break;
      case "jpg":
        type = BufferedImage.TYPE_INT_RGB;
        break;
      case "bmp":
        type = BufferedImage.TYPE_INT_RGB;
        break;
      default:
        throw new IllegalStateException("Invalid extension type");
    }

    return type;
  }

  /**
   * Writes a file based on the given path and image.
   * @param path the given path
   * @param image the image being used
   */
  @Override
  public void write(String path, IImage image) {
    int bufferedIntType = this.getTypeFromExtension(this.extension);
    RenderedImage bufImg = image.toBufferedImage(bufferedIntType);
    writeToFile(bufImg, path);
  }
}
