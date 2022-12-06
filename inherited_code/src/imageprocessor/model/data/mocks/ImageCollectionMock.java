package imageprocessor.model.data.mocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.data.ImageCollection;

/**
 * Mock Model for Image Collection.
 */
public class ImageCollectionMock implements ImageCollection {
  private Appendable app;

  public ImageCollectionMock(Appendable app) {
    this.app = app;
  }

  /**
   * Mock add image.
   *
   * @param image the given image.
   */
  @Override
  public void addImage(IImage image) {
    this.log("addImage: image=" + image.getName());
  }

  /**
   * Mock remove image.
   *
   * @param name the given name of image.
   */
  @Override
  public void removeImage(String name) {
    this.log("removeImage: name=" + name);
  }

  /**
   * Mock get image.
   *
   * @param name the name of the desired image.
   * @return a new mock image.
   */
  @Override
  public IImage getImage(String name) {
    this.log("getImage: name=" + name);
    List<List<IPixel>> list = new ArrayList<>();
    IImage img = new RGBImage("mocked", list);
    return img;
  }

  /**
   * Mock for renaming an image.
   *
   * @param oldName a String of the old name of the image.
   * @param newName a String of the new name of the image.
   */
  @Override
  public void renameImage(String oldName, String newName) {
    this.log("renameImage: old=" + oldName + " new=" + newName);
  }

  /**
   * Sets the selected image as the image with the given name.
   * @param name teh name of the new selected image
   */
  @Override
  public void setSelectedImage(String name) {
    this.log("setSelectedImage(): name=" + name);
  }

  /**
   * Gets the selected image.
   * @return this selected image.
   */
  @Override
  public String getSelectedImage() {
    this.log("getSelectedImage()");
    return "";
  }

  /**
   * Mock for logging a message.
   *
   * @param msg the message to be logged.
   * @throws IllegalStateException if IOException is caught.
   */
  private void log(String msg) throws IllegalStateException {
    try {
      this.app.append(msg + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("IO exception caught; Invalid data");
    }
  }

}
