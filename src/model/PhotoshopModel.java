package model;

enum Direction {Horizontal, Vertical};
enum RGBColor {Red, Green, Blue};

/**
 * The model for the Photoshop Program. Will handle all computations, adjustments to an iamge.
 */
public interface PhotoshopModel {

  /**
   * Loads image at the imagePath and gives it the name imageName.
   * @param imagePath where the image is located.
   * @param imageName the images new name.
   */
  void loadImage(String imagePath, String imageName);

  /**
   * Saves image to given imagePath and names it imageName.
   * @param imagePath where to save the image.
   * @param imageName the name to give the newly saved image.
   */
  void saveImage(String imagePath, String imageName);

  /**
   * Flips image in flipDirection direction.
   * @param flipDirection horiztonal or vertical.
   */
  void flipImage(Direction flipDirection, String imageName, String destImageName);

  /**
   * Saves a new version of the given imageName image that only has the given color
   * in greyscale and names it destImageName name.
   * @param color the color to greyscale
   * @param imageName the source image.
   * @param destImageName the output image.
   */
  void colorComponent(RGBColor color, String imageName, String destImageName);

  /**
   * Brightens the given imageName by the given increment and saves it as
   * destImageName.
   * @param increment
   * @param imageName
   * @param destImageName
   */
  void brighten(int increment, String imageName, String destImageName);
}
