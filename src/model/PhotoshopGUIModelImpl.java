package model;

import java.awt.image.BufferedImage;

import model.enums.ComponentGreyscale;
import model.enums.Direction;
import model.kernels.AMatrix;
import model.kernels.IKernel;

public class PhotoshopGUIModelImpl extends PhotoshopModelProImpl implements PhotoshopGUIModelPro {
  private String recentName;

  public PhotoshopGUIModelImpl() {
    super();
  }

  @Override
  public BufferedImage getRecentImage() {
    ImageUtil util = new ImageUtil();
    return util.RGBToBufferedImage(this.imageStorage.get(recentName), BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    System.out.println("hiiiii");
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
