package imageprocessor.view.guiview;

import java.awt.event.ActionListener;

import imageprocessor.view.IView;

/**
 * Represents a GUI view.
 */
public interface IGUIView extends IView {
  /**
   * Takes in an action listener and sets a listener for each effect button.
   * The action listener listens for when the button is clicked.
   * @param listener the given action listener
   */
  public void setActionListener(ActionListener listener);

  /**
   * Takes in an action listener and sets a listener for the file open and file save buttons.
   * The action listener listens for when the button is clicked.
   * @param listener the given action listener
   */
  public void setDisplayListener(ActionListener listener);

  /**
   * Takes in data from the controller in order to update the data of the image.
   */
  public void updateData();

}
