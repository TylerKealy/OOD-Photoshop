package imageprocessor.model.imageoperations.pixelfilters;

/**
 * Sharpens each pixel by having a matrix with negative values at the edge and large values at
 * the center.
 */
public class Sharpen extends FilterAdjacent {
  /**
   * Creates a new matrix that methods from FilterAdjacent will use.
   */
  public Sharpen() {
    this.mat = new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125},
      {-0.125, 0.25, 1, 0.25, -0.125},
      {-0.125, 0.25, 0.25, 0.25, -0.125},
      {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }
}
