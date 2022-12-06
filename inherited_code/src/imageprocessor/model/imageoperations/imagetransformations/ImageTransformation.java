package imageprocessor.model.imageoperations.imagetransformations;

import imageprocessor.model.components.image.IImage;

/**
 * Performs a transformation on an image.
 */
public interface ImageTransformation {
  /**
   * Transforms the image as desired.
   * @param image the image that will be altered.
   */
  public void transform(IImage image);
}
