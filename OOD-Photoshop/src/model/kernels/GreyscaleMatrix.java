package model.kernels;

/**
 * A GreyScale matrix is a matrix that produces a greyscale effect.
 */
public class GreyscaleMatrix extends AMatrix {

  /**
   * This is the data to be used to apply to each pixel to create the greyscale effect.
   */
  public GreyscaleMatrix() {
    this.matrix = new float[][]{
            {.2126f, .7152f, .0722f},
            {.2126f, .7152f, .0722f},
            {.2126f, .7152f, .0722f}};
  }

}
