package model.kernels;

import model.enums.RGB;

/**
 * AKernel abstracts common functionalities used between all IKernel implementations.
 */
public abstract class AKernel implements IKernel {
  RGB[][] source;
  float[][] kernel;

  /**
   * Applies Kernel to the given RGB[][]].
   *
   * @param source the source image to apply this Kernel to.
   * @return an updated RGB pixel.
   */
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

  /**
   * Applies Kernel to the given RGB[row][col] pixel.
   *
   * @param row row of pixel.
   * @param col col of pixel.
   * @return an updated RGB pixel.
   */
  RGB applyPixel(int row, int col) {
    RGB output = new RGB(0, 0, 0);
    int offset = kernel.length / 2;
    for (int i = -offset; i <= offset; i++) {
      for (int j = -offset; j <= offset; j++) {
        if (isOutOfBounds(row + i, col + j)) {
          continue;
        }
        output.r += (source[row + i][col + j].r * kernel[i + offset][j + offset]);
        output.g += (source[row + i][col + j].g * kernel[i + offset][j + offset]);
        output.b += (source[row + i][col + j].b * kernel[i + offset][j + offset]);
      }
    }
    output.r = clampToRGB(output.r);
    output.g = clampToRGB(output.g);
    output.b = clampToRGB(output.b);
    return output;
  }

  protected boolean isOutOfBounds(int row, int col) {
    return (row < 0 || row >= source.length || col < 0 || col >= source[0].length);
  }
}
