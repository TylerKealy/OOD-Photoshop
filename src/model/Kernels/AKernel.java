package model.Kernels;

import model.Enums.RGB;

public abstract class AKernel implements IKernel{
  RGB[][] source;
  public RGB[][] apply(RGB[][] source)  {
    this.source = source;
    RGB[][] output = new RGB[source.length][source[0].length];

    int height = source.length;
    int width = source[0].length;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        output[row][col] = applyPixel(row,col);
      }
    }

    return output;
  }

  abstract RGB applyPixel(int row, int col);

  protected boolean isOutOfBounds(int row, int col) {
    //System.out.println("out: " + (row < 0 || row >= source.length || col < 0 || col >= source[0].length));
    return (row < 0 || row >= source.length || col < 0 || col >= source[0].length);
  }
}
