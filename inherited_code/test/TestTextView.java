import org.junit.Test;

import java.io.IOException;

import imageprocessor.view.textview.ITextView;
import imageprocessor.view.textview.TextView;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the view.
 */
public class TestTextView {
  private Appendable output = new StringBuilder();
  private ITextView view = new TextView(output);

  @Test
  public void testRenderMessage() throws IOException {
    view.renderMessage("Hello");
    String expected = "Hello";
    String outString = output.toString();
    assertEquals(expected, outString);

    view.renderMessage("Hello");
    expected = "HelloHello";
    outString = output.toString();
    assertEquals(expected, outString);

    view.renderMessage("Hello\n");
    view.renderMessage("Hello");

    expected = "HelloHelloHello\nHello";
    outString = output.toString();
    assertEquals(expected, outString);
  }
}
