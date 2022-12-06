package imageprocessor.model.data;

import imageprocessor.model.components.image.IImage;

/**
 * Represents a map of the images in a collection. Each element has a name and an image.
 */
public interface ImageCollection extends ImageCollectionState {
  /**
   * Adds an image to the map collections.
   * @param image the image that will be added to collections.
   */
  public void addImage(IImage image);

  /**
   * Removes an image from collections.
   * @param name the name of the image that shall be removed
   */
  public void removeImage(String name);

  /**
   * Replaces the old name of the image with a new name.
   * @param oldName a String of the old name of the image.
   * @param newName a String of the new name of the image.
   */
  public void renameImage(String oldName, String newName);

  public void setSelectedImage(String name);
}
