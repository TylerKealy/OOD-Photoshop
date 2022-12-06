package imageprocessor.model.imageoperations.pixelfilters;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

/** Filters the passed in pixel based on surrounding pixels and a
 * passed in matrix.
 *
 */
public abstract class FilterAdjacent implements PixelFilter {
  protected double[][] mat;

  /**
   * Adjusts the passed in pixel based on the surrounding pixels.
   * @param centerR the row of the pixel being changed
   * @param centerC the column of the pixel being changed
   */
  public void apply(IPixel current, IImage image, int centerR, int centerC) {
    int matCenterR = mat.length / 2;
    int matCenterC = mat[0].length / 2;
    double redAccum = 0;
    double greenAccum = 0;
    double blueAccum = 0;


    for (int r = 0; r < mat.length; r++) {
      for (int c = 0; c < mat[0].length; c++) {
        int rImg = centerR - matCenterR + r;
        int cImg = centerC - matCenterC + c;
        if (rImg >= 0 && cImg >= 0 && rImg < image.getHeight() && cImg < image.getWidth()) {
          IPixel pixel = image.getPixelAt(rImg, cImg);
          redAccum += mat[r][c] * pixel.getRedComponent();
          greenAccum += mat[r][c] * pixel.getGreenComponent();
          blueAccum += mat[r][c] * pixel.getBlueComponent();
        }
      }
    }

    int redFinal = Math.max(0, Math.min((int) redAccum, current.getMaxValue()));
    int greenFinal = Math.max(0, Math.min((int) greenAccum, current.getMaxValue()));
    int blueFinal = Math.max(0, Math.min((int) blueAccum, current.getMaxValue()));

    current.setComponents(redFinal, greenFinal, blueFinal);
  }
}
