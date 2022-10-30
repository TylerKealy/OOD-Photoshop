package model;

public class PhotoshopModelImpl implements PhotoshopModel{

  public PhotoshopModelImpl() {

  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    ImageUtil util = new ImageUtil();
    util.readPPM(imagePath);
  }

  @Override
  public void saveImage(String imagePath, String imageName) {

  }

  @Override
  public void flipImage(Direction flipDirection) {

  }

  @Override
  public void colorComponent(RGBColor color, String imageName, String destImageName) {

  }

  @Override
  public void brighten(int increment, String imageName, String destImageName) {

  }
}
