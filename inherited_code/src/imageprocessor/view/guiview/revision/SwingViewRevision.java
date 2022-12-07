package imageprocessor.view.guiview.revision;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

import javax.swing.*;

import imageprocessor.model.data.ImageCollectionState;
import imageprocessor.view.guiview.IGUIViewRevision;
import imageprocessor.view.guiview.SwingView;

public class SwingViewRevision extends SwingView implements IGUIViewRevision {
  private JTextField dialogText;
  private JDialog dialog;

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

  /**
   * Diplays Dialog Text field titled the given name, and subscribes the done button
   * to the given listener.
   *
   * @param name     message to be displayed to the user.
   * @param listener the listener to be called when the done button is hit.
   */
  public void dialogBox(String name, ActionListener listener) {
    // create a dialog Box
    dialog = new JDialog(this, "Dialog Box");

    //button and adding listeners
    JButton button = new JButton("Enter.");
    button.setActionCommand("DialogBox");
    button.addActionListener(this);
    button.addActionListener(listener);

    // create a panel
    JPanel p = new JPanel();
    JLabel l = new JLabel(name);
    dialogText = new JTextField(5);
    p.add(dialogText);
    p.add(l);
    p.add(button);

    // adds panel to dialog box and sets it visible.
    dialog.add(p);
    dialog.setSize(200, 200);
    dialog.pack();
    dialog.setVisible(true);
  }

  @Override
  public String getDialogBoxText() {
    return dialogText.getText();
  }

  @Override
  public void actionPerformed(ActionEvent argument) {
    super.actionPerformed(argument);
    if(argument.getActionCommand().equals("DialogBox")) {
      dialog.setVisible(false);
    }
  }

}
