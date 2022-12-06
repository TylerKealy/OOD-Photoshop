package imageprocessor.view.mock;

import java.awt.event.ActionListener;
import java.io.IOException;
import imageprocessor.view.guiview.IGUIView;

/**
 * Mock for a view.
 */
public class MockView implements IGUIView {
  private Appendable app;

  /** Takes an appendable to log the called on methods.
   *
   * @param app the appendable to keep as a log.
   */
  public MockView(Appendable app) {
    this.app = app;
  }

  /**
   * Mock for logging a message.
   *
   * @param msg the message to be logged.
   * @throws IllegalStateException if IOException is caught.
   */
  private void log(String msg) throws IllegalStateException {
    try {
      this.app.append(msg + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("IO exception caught; Invalid data");
    }
  }

  /** Sets the mock action listener.
   *
   * @param listener the given action listener
   */
  @Override
  public void setActionListener(ActionListener listener) {
    this.log("setActionListener()");
  }

  /**
   * Sets the mock display listener.
   * @param listener the given action listener
   */
  @Override
  public void setDisplayListener(ActionListener listener) {
    this.log("setDisplayListener()");
  }

  /**
   * Updates the mock data.
   */
  @Override
  public void updateData() {
    this.log("updateData()");

  }
}
