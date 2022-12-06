package imageprocessor.model.commands.mocks;

import java.io.IOException;

import imageprocessor.model.commands.IOCommand;

/**
 * Represents a mock of a command.
 */
public class CommandMock extends IOCommand {
  private Appendable app;
  private String cmd;

  /**
   * Creates a mock command with a given command and appendable.
   * @param cmd the given command for an operation.
   * @param app the appendable to be appended.
   */
  public CommandMock(String cmd, Appendable app) {
    this.app = app;
    this.cmd = cmd;
  }

  /**
   * Logs the given command.
   */
  public void execute() {
    this.log(this.cmd);
  }

  /**
   * Appends the given message to the appendable of this class.
   * @param msg the message to be appended
   * @throws IllegalStateException if append creates an IOException
   */
  private void log(String msg) throws IllegalStateException {
    try {
      this.app.append(msg + "\n");
    }
    catch (IOException e) {
      throw new IllegalStateException("IO exception caught; Invalid data");
    }
  }
}
