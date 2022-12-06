package imageprocessor.model.data;

import java.util.HashMap;
import java.util.Map;

import imageprocessor.model.components.image.IImage;

/**
 * Represents a map of the images in a collection. Each element has a name and an image.
 */
public class ImageMapCollection implements ImageCollection {
  private Map<String, IImage> map;
  private String selectedImage;

  /**
   * Creates a new image map collection with a new Hashmap. Each element has a name and an image.
   */
  public ImageMapCollection() {
    this.map = new HashMap<String, IImage>();
    this.selectedImage = null;
  }

  /**
   * Adds an image to the map collections.
   * @param image the image that will be added to collections.
   */
  public void addImage(IImage image) {
    this.map.put(image.getName(), image);
  }

  /**
   * Removes an image from collections.
   * @param name the name of the image that shall be removed
   */
  public void removeImage(String name) {
    this.map.remove(name);
  }

  /**
   * Produces the image associated with the given name if available.
   * @param name the name of the image.
   * @return the image associated with the given name
   */
  public IImage getImage(String name) {
    return this.map.get(name);
  }

  /**
   * Replaces the old name of the image with a new name.
   * @param oldName a String of the old name of the image.
   * @param newName a String of the new name of the image.
   */
  public void renameImage(String oldName, String newName) {
    IImage img = this.getImage(oldName);
    this.removeImage(oldName);
    img.setName(newName);
    this.addImage(img);
  }

  /**
   * Gets the selected image.
   * @return this selected image.
   */
  public String getSelectedImage() {
    return this.selectedImage;
  }

  /**
   * Sets the selected image as the image with the given name.
   * @param name teh name of the new selected image
   */
  public void setSelectedImage(String name) {
    this.selectedImage = name;
  }
}
