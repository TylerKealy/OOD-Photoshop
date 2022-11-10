package model.kernels;

/**
 * This is the matrix that when applied, results in an image with a Sepia effect.
 */
public class SepiaMatrix extends AMatrix {

  /**
   * This is the data that creates the sepia effect.
   */
  public SepiaMatrix() {
    this.matrix = new float[][]{
            {.393f, .769f, .189f},
            {.349f, .686f, .168f},
            {.272f, .534f, .131f}};
  }
}
