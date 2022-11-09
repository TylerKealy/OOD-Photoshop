package model.Kernels;

import model.Enums.RGB;

public class SharpenKernel extends AKernel {

  float[][] sharpen;

  public SharpenKernel() {
    this.source = source;
    sharpen = new float[][]{
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f, 1f / 4f, -1f / 8f},
            {-1f / 8f, 1f / 4f, 1f / 4f, 1f / 4f, -1f / 8f},
            {-1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f, -1f / 8f}};
  }

  @Override
  public RGB applyPixel(int row, int col) {
    RGB output = new RGB(0, 0, 0);
    for (int i = -2; i <= 2; i++) {
      for (int j = -2; j <= 2; j++) {
        if (isOutOfBounds(row + i, col + j)) continue;
        output.r += source[row + i][col + j].r * sharpen[i + 2][j + 2];
        output.g += source[row + i][col + j].g * sharpen[i + 2][j + 2];
        output.b += source[row + i][col + j].b * sharpen[i + 2][j + 2];
      }
    }
    return output;
  }
}
