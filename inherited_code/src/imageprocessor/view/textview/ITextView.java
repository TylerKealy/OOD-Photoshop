package imageprocessor.view.textview;

import java.io.IOException;
import imageprocessor.view.IView;

/**
 * Represents a text view that can render messages.
 */
public interface ITextView extends IView {
  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  public void renderMessage(String message) throws IOException;
}
