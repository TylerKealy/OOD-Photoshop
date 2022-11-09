package model.Kernels;

import model.Enums.RGB;

public class BlurKernel extends AKernel {
  //TODO: do we want to specify 3x3 in size, or in implementation?
  public BlurKernel() {
    this.kernel = new float[][]{
            {1f / 16f, 1f / 8f, 1f / 16f},
            {1f / 8f, 1f / 4f, 1f / 8f},
            {1f / 16f, 1f / 8f, 1f / 16f}};
  }


}
