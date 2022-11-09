package model.Kernels;

import model.Enums.RGB;

public interface IKernel {
  RGB[][] apply(RGB[][] source);
}
