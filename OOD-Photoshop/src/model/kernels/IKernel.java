package model.kernels;

import model.enums.RGB;

/**
 * An IKernel is the interface for a Kernel. Kernels are applied to an image to filter it.
 */
public interface IKernel {
  /**
   * applies this Kernel to the given source image data and returns a new RGB[][] output.
   * @param source the source image data
   * @return a new Kernel filtered RGB[][]
   */
  RGB[][] apply(RGB[][] source);
}
