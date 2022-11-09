package model.Kernels;

import model.Enums.RGB;

public class BlurKernel extends AKernel {
  //TODO: do we want to specify 3x3 in size, or in implementation?
  float[][] blur;

  public BlurKernel() {
    blur = new float[][]{
            {1f / 16f, 1f / 8f, 1f / 16f},
            {1f / 8f, 1f / 4f, 1f / 8f},
            {1f / 16f, 1f / 8f, 1f / 16f}};
  }

  @Override
  public RGB applyPixel(int row, int col) {
    RGB output = new RGB(0, 0, 0);
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if(isOutOfBounds(row + i, col + j)) continue;
        output.r += source[row + i][col + j].r * blur[i + 1][j + 1];
        output.g += source[row + i][col + j].g * blur[i + 1][j + 1];
        output.b += source[row + i][col + j].b * blur[i + 1][j + 1];
      }
    }
    return output;
  }

}
