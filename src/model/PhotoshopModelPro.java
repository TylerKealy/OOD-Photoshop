package model;

import model.Kernels.IKernel;

public interface PhotoshopModelPro extends PhotoshopModel{

  void kernel(String imageName, String destImageName, IKernel kernel);

}
