package model.kernels;

/**
 * A BlurKernel is a type of Kernel that uses nearby pixel data to blur
 * an image with this kernel.
 */
public class BlurKernel extends AKernel {
  /**
   * This is the data to be applied to each pixel to create a blur effect.
   */
  public BlurKernel() {
    this.kernel = new float[][]{
            {1f / 16f, 1f / 8f, 1f / 16f},
            {1f / 8f, 1f / 4f, 1f / 8f},
            {1f / 16f, 1f / 8f, 1f / 16f}};
  }


}
