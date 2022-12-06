package imageprocessor.view.textview;

import java.io.IOException;

/**
 * A class that can render messages.
 */
public class TextView implements ITextView {
  private Appendable output;

  /**
   * Creates a textview with a given appendable.
   * @param output the appendable that can be appended to and outputted.
   */
  public TextView(Appendable output) {
    if (output == null) {
      throw new IllegalArgumentException("Invalid appendable");
    }
    this.output = output;
  }

  /**
   * Renders the desired message to the desired location.
   * @param message the message that will be rendered.
   * @throws IOException if transmission fails.
   */
  public void renderMessage(String message) throws IOException {
    this.output.append(message);
  }
}
