package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Objects;

import model.PhotoshopGUIModelImpl;
import model.PhotoshopModel;
import model.PhotoshopModelProImpl;

public class PhotoshopGUIView extends JFrame implements  PhotoshopView, ActionListener, GUIView {

  private PhotoshopModel model;

  JTextField input;
  JLabel image;
  JLabel terminalTextLabel;
  JButton updateButton;
  JButton quitButton;

  PhotoshopFeatures features;



  public PhotoshopGUIView(PhotoshopModel model) {
    Objects.requireNonNull(model);
    this.model = model;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new FlowLayout());
    this.setBounds(100, 200, 800, 300);

    Container c = this.getContentPane();

    image = new JLabel();
    image.setIcon(new ImageIcon("C:\\soala.jpg"));
    Dimension size = image.getPreferredSize();
    System.out.println("size: " + size.height);
    image.setBounds(50, 30, size.width, size.height);

    c.add(image);

    this.setVisible(true);


    // label for the field
    JLabel inputLabel = new JLabel("terminal:");
    c.add(inputLabel);

    // text input field
    input = new JTextField(20);
    c.add(input);

    // the current text
    terminalTextLabel = new JLabel("To be displayed");
    c.add(terminalTextLabel);

    // update button
    updateButton = new JButton("Update");
    updateButton.setActionCommand("UpdateAction");
    updateButton.addActionListener(this);
    c.add(updateButton);
    // quit button

    quitButton = new JButton("Quit");
    quitButton.setActionCommand("QuitAction");
    quitButton.addActionListener(this);
    c.add(quitButton);

    //this.addKeyListener(setupKeyboardListener());

    //this.pack();


  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
      case "UpdateAction":
        this.features.performCommand();
        break;
      case "QuitAction":
        System.exit(1);
        break;
    }
  }

  @Override
  public String getTerminalInput() {
    return this.input.getText();
  }

  @Override
  public void setFeatures(PhotoshopFeatures features) {
    this.features = features;
  }

  @Override
  public void resetTerminalText() {
    this.input.setText("");
  }

  @Override
  public void setImage(BufferedImage image) {
    this.image.setIcon(new ImageIcon(image));
  }

  public static void main(String[] args) {
    PhotoshopGUIModelImpl model = new PhotoshopGUIModelImpl();
    PhotoshopGUIView view = new PhotoshopGUIView(model);
    PhotoshopGUIController controller = new PhotoshopGUIController(model, view);
    view.setFeatures(controller);
    controller.run();

  }
}


