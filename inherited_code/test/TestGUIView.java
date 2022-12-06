import org.junit.Before;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;


import imageprocessor.controller.GUIController;
import imageprocessor.controller.IGUIController;
import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.image.RGBImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.components.pixel.RGBPixel;
import imageprocessor.model.data.ImageCollection;
import imageprocessor.model.data.ImageMapCollection;
import imageprocessor.view.guiview.HistogramDisplay;
import imageprocessor.view.guiview.HistogramWrapper;
import imageprocessor.view.guiview.IGUIView;
import imageprocessor.view.guiview.SwingView;

import static org.junit.Assert.assertFalse;

/**
 * Tests for GUI Views.
 */
public class TestGUIView {
  private IImage image1;

  @Before
  public void setup() {
    List<List<IPixel>> pixelGrid = new ArrayList<>();
    List<IPixel> arr = new ArrayList<IPixel>();
    List<IPixel> arr2 = new ArrayList<IPixel>();
    pixelGrid.add(arr);
    pixelGrid.add(arr2);
    pixelGrid.get(0).add(new RGBPixel(1,2,3));
    pixelGrid.get(0).add(new RGBPixel(4,5,6));
    pixelGrid.get(1).add(new RGBPixel(1,8,9));
    pixelGrid.get(1).add(new RGBPixel(10,11,12));
    this.image1 = new RGBImage("sample", pixelGrid);

  }

  @Test
  public void testUpdateData() {
    ImageCollection model = new ImageMapCollection();
    IGUIView view = new SwingView(model);
    IGUIController controller = new GUIController(view, model);
    JFrame viewPanel = (JFrame) view;
    viewPanel.setSize(new Dimension(800,800));
    BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    viewPanel.paint(img.getGraphics());
    IImage imgConv = new RGBImage("one", img);

    model.addImage(this.image1);
    model.setSelectedImage("sample");
    view.updateData();

    BufferedImage img2 = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    viewPanel.paint(img2.getGraphics());
    IImage imgConv2 = new RGBImage("two", img2);

    assertFalse(imgConv.equals(imgConv2));

  }

  @Test
  public void testHistogramDiagram() {
    HistogramDisplay hist1 = new HistogramWrapper();
    HistogramDisplay hist2 = new HistogramWrapper();

    int[] expectedRed = new int[256];
    expectedRed[1] = 2;
    expectedRed[4] = 1;
    expectedRed[10] = 1;
    hist1.updateFrequencies(expectedRed);

    int[] expectedGreen = new int[256];
    expectedGreen[2] = 1;
    expectedGreen[5] = 1;
    expectedGreen[8] = 1;
    expectedGreen[11] = 1;
    hist2.updateFrequencies(expectedGreen);

    JPanel hist1Panel = hist1.getPanel();
    hist1Panel.setSize(new Dimension(300, 300));
    BufferedImage bufImg1 = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
    hist1Panel.paint(bufImg1.getGraphics());
    IImage imgConv = new RGBImage("one", bufImg1);

    JPanel hist2Panel = hist2.getPanel();
    hist1Panel.setSize(new Dimension(300, 300));
    BufferedImage bufImg2 = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
    hist2Panel.paint(bufImg2.getGraphics());
    IImage imgConv2 = new RGBImage("one", bufImg2);

    assertFalse(imgConv.equals(imgConv2));

  }

}
