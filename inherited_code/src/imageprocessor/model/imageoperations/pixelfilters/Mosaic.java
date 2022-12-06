package imageprocessor.model.imageoperations.pixelfilters;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import imageprocessor.model.components.image.IImage;
import imageprocessor.model.components.pixel.IPixel;

public class Mosaic implements PixelFilter {

  private HashMap<Posn, ArrayList> seeds = new HashMap<Posn, ArrayList>();
  private IImage image;

  public Mosaic(int numSeeds, IImage image) {
    this.image = image;
    for (int i = 0; i < numSeeds; i++) {
      int randX = (int) (Math.random() * image.getWidth());
      int randY = (int) (Math.random() * image.getHeight());
      Posn seed = new Posn(randX, randY);
      seeds.put(seed, new ArrayList());
    }
  }

  private Posn findNearestSeed(int x, int y) {
    float closestDistance = -1f;
    Posn nearest = null;
    for (Posn seed : seeds.keySet()) {
      float thisDistance = (float) Math.pow(Math.abs(seed.x - x) + Math.abs(seed.y - y), 2);
      if (thisDistance < closestDistance) {
        closestDistance = thisDistance;
        nearest = seed;
      }
    }
    return nearest;
  }

  private Color averageColor(ArrayList<Posn> seedValues) {
    int totalR = 0;
    int totalG = 0;
    int totalB = 0;
    for (int i = 0; i < seedValues.size(); i++) {
      IPixel pixel = image.getPixelAt(seedValues.get(i).x, seedValues.get(i).y);
      totalR += pixel.getRedComponent();
      totalG += pixel.getGreenComponent();
      totalB += pixel.getBlueComponent();
    }
    return new Color(totalR / seedValues.size(), totalG / seedValues.size(), totalB / seedValues.size());
  }

  @Override
  public void apply(IPixel pixel, IImage image, int r, int c) {
    Posn seed = findNearestSeed(c, r);
    Color color = averageColor(seeds.get(seed));
    pixel.setComponents(color.getRed(), color.getGreen(), color.getBlue());
  }
}
