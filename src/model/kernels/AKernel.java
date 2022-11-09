package model.kernels;

import model.enums.RGB;

public abstract class AKernel implements IKernel {
  RGB[][] source;
  float[][] kernel;

  public RGB[][] apply(RGB[][] source) {
    this.source = source;
    RGB[][] output = new RGB[source.length][source[0].length];

    int height = source.length;
    int width = source[0].length;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        output[row][col] = applyPixel(row, col);
      }
    }

    return output;
  }

  int clampToRGB(int input) {
    return Math.min(Math.max(input, 0), 255);
  }

  RGB applyPixel(int row, int col) {
    RGB output = new RGB(0, 0, 0);
    int offset = kernel.length / 2;
    for (int i = -offset; i <= offset; i++) {
      for (int j = -offset; j <= offset; j++) {
        if (isOutOfBounds(row + i, col + j)) continue;
        output.r += clampToRGB((int)
                (source[row + i][col + j].r * kernel[i + offset][j + offset]));
        output.g += clampToRGB((int)
                (source[row + i][col + j].g * kernel[i + offset][j + offset]));
        output.b += clampToRGB((int)
                (source[row + i][col + j].b * kernel[i + offset][j + offset]));
      }
    }
    return output;
  }

  protected boolean isOutOfBounds(int row, int col) {
    //System.out.println("out: " +
    // (row < 0 || row >= source.length || col < 0 || col >= source[0].length));
    return (row < 0 || row >= source.length || col < 0 || col >= source[0].length);
  }
}
