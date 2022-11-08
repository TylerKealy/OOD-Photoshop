package model;

import model.Enums.RGB;
import model.Kernels.BlurKernel;

public class PhotoshopModelProImpl extends PhotoshopModelImpl implements PhotoshopModelPro {
  @Override
  public void transform(String imageName, String destImageName) {
    blur(imageName, destImageName);
  }

  private void blur(String imageName, String destImageName) {
    RGB[][] source = imageStorage.get(imageName);
    BlurKernel blurKernel = new BlurKernel(source);

    RGB[][] output = blurKernel.apply();

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
    impl.transform("dogs", "blur_dogs");
    impl.saveImage("images/blur_dogs.ppm", "blur_dogs");
  }

}
