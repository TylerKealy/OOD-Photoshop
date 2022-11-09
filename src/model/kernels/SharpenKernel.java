package model.kernels;

public class SharpenKernel extends AKernel {

  public SharpenKernel() {
    this.kernel = new float[][]{
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f}};
  }
}
