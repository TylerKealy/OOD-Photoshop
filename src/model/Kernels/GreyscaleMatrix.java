package model.Kernels;

import model.Enums.RGB;

public class GreyscaleMatrix extends AMatrix {

  public GreyscaleMatrix() {
    this.matrix = new float[][]{
            {.2126f, .7152f, .0722f},
            {.2126f, .7152f, .0722f},
            {.2126f, .7152f, .0722f}};
  }

}
