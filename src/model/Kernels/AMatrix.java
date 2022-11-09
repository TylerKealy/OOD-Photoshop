package model.Kernels;

import model.Enums.RGB;

public class AMatrix extends AKernel{
  float[][] matrix;

  @Override
  RGB applyPixel(int row, int col) {
    RGB output = new RGB(0, 0, 0);
    RGB sourcePixel = source[row][col];
    output.r = (int) (matrix[0][0] * sourcePixel.r +
            matrix[0][1] * sourcePixel.g +
            matrix[0][2] * sourcePixel.b);
    output.g = (int) (matrix[1][0] * sourcePixel.r +
            matrix[1][1] * sourcePixel.g +
            matrix[1][2] * sourcePixel.b);;
    output.b = (int) (matrix[2][0] * sourcePixel.r +
            matrix[2][1] * sourcePixel.g +
            matrix[2][2] * sourcePixel.b);;
    return output;
  }
}
