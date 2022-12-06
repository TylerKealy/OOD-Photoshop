package imageprocessor.model.data;

import imageprocessor.model.components.image.IImage;

/**
 * Represents a map of images. Capable of accessing items within this map.
 */
public interface ImageCollectionState {
  /**
   * Produces the image associated with the given name if available.
   * @param name the name of the image.
   * @return the image associated with the given name
   */
  public IImage getImage(String name);


  /**
   * Gets the selected image.
   * @return this selected image.
   */
  public String getSelectedImage();

}
