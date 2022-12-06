package imageprocessor.model.imageoperations.pixelfilters;

/** Represents a Gaussian Blur Filter.
 *
 */
public class GaussianBlur extends FilterAdjacent {
  /**
   * Creates a new matrix that methods from FilterAdjacent will use.
   */
  public GaussianBlur() {
    this.mat = new double[][]{{.0625, .125, .0625},
      {.125, .25, .125},
      {.0625, .125, .0625}};
  }
}
