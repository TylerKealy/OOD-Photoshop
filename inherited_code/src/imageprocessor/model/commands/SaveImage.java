package imageprocessor.model.commands;

import imageprocessor.model.imagereading.ImageUtil;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;

/**
 * Saves an image from collections onto the directory.
 */
public class SaveImage extends IOCommand {
  private ImageCollection collection;
  private String path;
  private String name;

  /**
   * Creates a new Save Image that can save an image to directory.
   * @param collection takes in a map of images (collection).
   * @param path the given path.
   * @param name the name of the image.
   */
  public SaveImage(ImageCollection collection, String path, String name) {
    this.collection = collection;
    this.path = path;
    this.name = name;
  }


  /**
   * Does the save file operation. (create file, write file to path, all in one.)
   */
  public void execute() {
    ImageUtil fileIO = this.getUtilFromPath(this.path);

    IImage img = this.collection.getImage(this.name);

    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }
    fileIO.write(this.path, img);
  }
}
