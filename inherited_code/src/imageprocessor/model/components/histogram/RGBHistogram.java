package imageprocessor.model.components.histogram;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;
import imageprocessor.model.imageoperations.componentaccessers.ComponentAccesser;

/**
 * Represents a bar chart that shows the values of red, blue, green, luma, etc.
 */
public class RGBHistogram implements IHistogram {
  private IImage image;
  private int[] freq;

  /**
   * Creates a new RGB histogram with an image and frequency.
   * @param image the given image
   */
  public RGBHistogram(IImage image) {
    this.image = image;
    this.freq = new int[256];
  }

  /**
   * Goes through each pixel in the image and logs the chosen component into this frequency.
   * @param accesser the accesser that specifies the chosen component type
   */
  public void createFromComponent(ComponentAccesser accesser) {
    // go thru each pixel
    freq = new int[256];
    for (int r = 0; r < image.getHeight(); r++) {
      for (int c = 0; c < image.getWidth(); c++) {
        IPixel pixel = image.getPixelAt(r, c);
        int component = accesser.apply(pixel);

        freq[component]++;
      }
    }
  }

  /**
   * Gets the frequency array of this histogram.
   * @return this histogram frequency
   */
  public int[] getHistogram() {
    return this.freq;
  }

}
