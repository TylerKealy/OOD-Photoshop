package model;

import model.enums.RGB;
import model.kernels.AMatrix;
import model.kernels.BlurKernel;
import model.kernels.IKernel;

/**
 * PhotoshopModelProImpl extends the base functionality of the starter PhotoshopModelImpl
 * and implements the extra bonus methods for the Pro edition through the PhotoshopModelPro
 * interface.
 */
public class PhotoshopModelProImpl extends PhotoshopModelImpl implements PhotoshopModelPro {

  /**
   * Applies the kernel to the "imageName" image and stores it as "destImageName".
   * @param imageName source image.
   * @param destImageName exported image.
   * @param kernel kernel to apply.
   */
  public void kernel(String imageName, String destImageName, IKernel kernel) {
    RGB[][] source = imageStorage.get(imageName);
    RGB[][] output = kernel.apply(source);
    imageStorage.put(destImageName, output);
  }

  /**
   * Applies the matrix to the "imageName" image and stores it as "destImageName".
   * @param imageName source image.
   * @param destImageName exported image.
   * @param matrix matrix to apply.
   */
  public void transform(String imageName, String destImageName, AMatrix matrix) {
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
