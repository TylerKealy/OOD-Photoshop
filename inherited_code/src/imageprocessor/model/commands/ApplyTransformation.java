package imageprocessor.model.commands;

import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.data.ImageCollection;

/**
 * Applies the desired transformation to the desired image.
 */
public class ApplyTransformation implements ICommand {
  private String name;
  private ImageTransformation transformation;
  private ImageCollection collection;
  private IImage image;

  /**
   * Creates a new ApplyTransformation with the parameters that it needs to fulfill its task.
   * @param collection A map of images.
   * @param img An image which is a 2d arraylist of pixels which will be transformed.
   * @param name The name of the image.
   * @param transformation The desired transformation of the image.
   */
  public ApplyTransformation(ImageCollection collection, IImage img, String name,
                             ImageTransformation transformation) {
    if (img == null) {
      throw new IllegalStateException("Invalid image");
    }

    this.name = name;
    this.transformation = transformation;
    this.collection = collection;
    this.image = img;
  }

  /**
   * Executes the transformation, sets the name, and adds the image to the collection map.
   */
  public void execute() {
    this.transformation.transform(this.image);
    this.image.setName(this.name);
    this.collection.addImage(this.image);
    this.collection.setSelectedImage(this.name);
  }
}
