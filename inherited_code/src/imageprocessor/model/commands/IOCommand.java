package imageprocessor.model.commands;


import imageprocessor.model.imagereading.ImageUtil;
import imageprocessor.model.imagereading.PPMUtil;
import imageprocessor.model.imagereading.StandardFormatsUtil;

/**
 * Represents a certain command.
 */
public abstract class IOCommand implements ICommand {
  /**
   * Gets a ppm file, throws exception if not ppm.
   * @param extension the file to be retrieved.
   * @return the file.
   */
  private ImageUtil getUtilFromExtension(String extension) {
    ImageUtil util;
    extension = extension.toLowerCase();
    switch (extension) {
      case "ppm":
        util =  new PPMUtil();
        break;
      case "png":
      case "jpg":
      case "bmp":
      case "jpeg":
        util = new StandardFormatsUtil(extension);
        break;
      default:
        throw new IllegalStateException("Invalid Image Format");
    }
    return util;
  }

  /**
   * Gets the Util from the given path.
   * @param path the path of the desired util.
   * @return the path from the given util.
   */
  public ImageUtil getUtilFromPath(String path) {
    String extension = ImageUtil.getFileExtension(path);
    ImageUtil fileIO = this.getUtilFromExtension(extension);
    return fileIO;
  }
}
