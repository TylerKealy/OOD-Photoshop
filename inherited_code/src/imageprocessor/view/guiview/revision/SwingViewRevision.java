package imageprocessor.view.guiview.revision;

import java.util.LinkedHashMap;

import javax.swing.*;

import imageprocessor.model.data.ImageCollectionState;
import imageprocessor.view.guiview.SwingView;

public class SwingViewRevision extends SwingView {
  /**
   * Represents a class that uses the swing application to display an interactable interface
   * that allows the user to load, view, modify, and save an image. The interface works by
   * providing buttons which the user clicks in order to send commands, which will result in the
   * associated change being made to the application view.
   *
   * @param model the provided model which the user can modify to
   */
  public SwingViewRevision(ImageCollectionState model) {
    super(model);
  }

  @Override
  protected void loadEffects() {
    this.effects = new LinkedHashMap<String, JButton>();

    this.effects.put("Flip Vertically", null);
    this.effects.put("Flip Horizontally", null);
    this.effects.put("Brighten +10", null);
    this.effects.put("Darken +10", null);
    this.effects.put("Red Greyscale", null);
    this.effects.put("Green Greyscale", null);
    this.effects.put("Blue Greyscale", null);
    this.effects.put("Intensity Greyscale", null);
    this.effects.put("Luma Greyscale", null);
    this.effects.put("Value Greyscale", null);
    this.effects.put("Greyscale", null);
    this.effects.put("Sepia Tone", null);
    this.effects.put("Gaussian Blur", null);
    this.effects.put("Sharpen", null);
    this.effects.put("Mosaic", null);
  }
}
