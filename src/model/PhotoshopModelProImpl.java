package model;

import model.enums.RGB;
import model.kernels.BlurKernel;
import model.kernels.IKernel;

public class PhotoshopModelProImpl extends PhotoshopModelImpl implements PhotoshopModelPro {
  @Override
  public void kernel(String imageName, String destImageName, IKernel kernel) {
    RGB[][] source = imageStorage.get(imageName);
    RGB[][] output = kernel.apply(source);
    imageStorage.put(destImageName, output);
  }

  public void transform(String imageName, String destImageName, IKernel matrix) {
    RGB[][] source = imageStorage.get(imageName);
    RGB[][] output = matrix.apply(source);
    imageStorage.put(destImageName, output);
  }

  /**
   * Main method used for testing the model.
   *
   * @param args java parameters.
   */
  public static void main(String[] args) {
    PhotoshopModelProImpl impl = new PhotoshopModelProImpl();
    impl.loadImage("images/dogs.ppm", "dogs");
    impl.kernel("dogs", "blur_dogs", new BlurKernel());
    impl.saveImage("images/blur_dogs.ppm", "blur_dogs");
  }

}
