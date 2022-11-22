import org.junit.Before;
import org.junit.Test;

import controller.PhotoshopGUIController;
import model.ImageUtil;
import model.PhotoshopGUIModelImpl;
import model.PhotoshopGUIModelPro;
import model.PhotoshopModelPro;
import view.GUIView;

import static org.junit.Assert.assertEquals;

public class PhotoshopUITest {
  PhotoshopGUIModelPro model;
  GUIView view;
  PhotoshopGUIController controller;


  @Before
  public void init() {
    model = new PhotoshopGUIModelPro();
    view = new GUIView(model);
    controller = new PhotoshopGUIController(model, new GUIView(model));
    mockModel = new ImageCollectionModel();
    mock = new UIControllerMock(mockModel);
    model.add("currentImage", ImageUtil.readImage("images/island.png"));
    originalImage = ImageUtil.readImage("images/island.png");
  }

  @Test
  public void
}
