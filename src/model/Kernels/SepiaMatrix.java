package model.Kernels;

import model.Enums.RGB;
import model.Kernels.AKernel;

public class SepiaMatrix extends AMatrix {

  public SepiaMatrix() {
    this.matrix = new float[][]{
            {.393f, .769f, .189f},
            {.349f, .686f, .168f},
            {.272f, .534f, .131f}};
  }
}
