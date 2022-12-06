package imageprocessor.model.commands;

import imageprocessor.model.imagereading.ImageUtil;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;

/**
 * Loads an image from a directory(images) onto collection.
 */
public class LoadImage extends IOCommand {
  private ImageCollection collection;
  private String path;
  private String name;

  /**
   * Creates a new load image that takes in a path, collection, and name.
   *
   * @param collection the map of images.
   * @param path       the path of the command.
   * @param name       the name of the image.
   */
  public LoadImage(ImageCollection collection, String path, String name) {
    this.collection = collection;
    this.path = path;
    this.name = name;
  }

  /**
   * Takes the image from the directory, reads it, sets a name, and adds it to collection.
   */
  public void execute() {
    ImageUtil fileIO = this.getUtilFromPath(this.path);
    IImage loaded = fileIO.read(path);
    loaded.setName(this.name);
    this.collection.addImage(loaded);
    this.collection.setSelectedImage(this.name);
  }
}
