package model;

import java.awt.image.BufferedImage;

import model.enums.ComponentGreyscale;
import model.enums.Direction;
import model.kernels.AMatrix;
import model.kernels.IKernel;

/**
 * A PhotoshopGUIModelImpl is a model that saves the most recent named image that has been
 * worked on. Used for displaying the most recent image on GUI.
 */
public class PhotoshopGUIModelImpl extends PhotoshopModelProImpl implements PhotoshopGUIModelPro {
  private String recentName;

  public PhotoshopGUIModelImpl() {
    super();
  }

  @Override
  public String getRecentImageName() {
    return recentName;
  }

  @Override
  public BufferedImage getRecentImage() {
    ImageUtil util = new ImageUtil();
    return util.rGBToBufferedImage(this.imageStorage.get(recentName), BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    super.loadImage(imagePath, imageName);
    recentName = imageName;
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    super.saveImage(imagePath, imageName);
  }

  @Override
  public void flipImage(Direction flipDirection, String imageName, String destImageName) {
    super.flipImage(flipDirection, imageName, destImageName);
    recentName = destImageName;
  }

  @Override
  public void greyscaleComponent(ComponentGreyscale color, String imageName, String destImageName) {
    super.greyscaleComponent(color, imageName, destImageName);
    recentName = destImageName;
  }

  @Override
  public void brighten(int increment, String imageName, String destImageName) {
    super.brighten(increment, imageName, destImageName);
    recentName = destImageName;
  }

  @Override
  public void kernel(String imageName, String destImageName, IKernel kernel) {
    super.kernel(imageName, destImageName, kernel);
    recentName = destImageName;
  }

  @Override
  public void transform(String imageName, String destImageName, AMatrix matrix) {
    super.transform(imageName, destImageName, matrix);
    recentName = destImageName;
  }
}
