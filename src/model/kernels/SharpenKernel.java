package model.kernels;

/**
 * A Kernel that "sharpens" the given image.
 */
public class SharpenKernel extends AKernel {

  /**
   * The data used to sharpen an image.
   */
  public SharpenKernel() {
    this.kernel = new float[][]{
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f}};
  }
}
