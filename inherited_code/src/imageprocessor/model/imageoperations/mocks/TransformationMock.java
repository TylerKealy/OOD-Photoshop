package imageprocessor.model.imageoperations.mocks;

import java.io.IOException;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.imageoperations.imagetransformations.ImageTransformation;

/**
 * Mock class for transformation.
 */
public class TransformationMock implements ImageTransformation {
  private Appendable app;

  /**
   * Create a new Mock transformation with a given appendable.
   * @param app the given appendable.
   */
  public TransformationMock(Appendable app) {
    this.app = app;
  }

  /**
   * Mock method for transform image.
   * @param image the image that will be altered.
   */
  @Override
  public void transform(IImage image) {
    this.log("transform: image=" + image.getName());
  }

  /**
   * Mock method for logging a message.
   * @param msg the message to be logged.
   * @throws IllegalStateException if IOException is caught.
   */
  private void log(String msg) throws IllegalStateException {
    try {
      this.app.append(msg + "\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("IO exception caught; Invalid data");
    }
  }

}
