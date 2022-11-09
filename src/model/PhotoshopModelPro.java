package model;

import model.kernels.AMatrix;
import model.kernels.IKernel;

/**
 * The purpose of PhotoshopModelPro is to add bonus functionality for the Pro edition
 * beyond the basic.
 */
public interface PhotoshopModelPro extends PhotoshopModel {

  void kernel(String imageName, String destImageName, IKernel kernel);

  void transform(String imageName, String destImageName, AMatrix matrix);


}
