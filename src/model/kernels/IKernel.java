package model.kernels;

import model.enums.RGB;

public interface IKernel {
  RGB[][] apply(RGB[][] source);
}
