package imageprocessor.view.guiview;

import java.awt.event.ActionListener;

public interface IGUIViewRevision extends IGUIView{

  public String getDialogBoxText();

  public void dialogBox(String name, ActionListener listener);

}
