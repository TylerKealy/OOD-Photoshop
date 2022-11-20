package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import model.PhotoshopModel;

public class PhotoshopGUIView extends JFrame implements  PhotoshopView{

  private PhotoshopModel model;

  JTextField input;
  JLabel currentTextLabel;
  JButton updateButton;
  JButton quitButton;

  //SwingAppFeatures features;

  public PhotoshopGUIView(PhotoshopModel model) {
    Objects.requireNonNull(model);
    this.model = model;

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setBounds(100, 200, 350, 300);

    Container c = this.getContentPane();

    JLabel label = new JLabel();
    label.setIcon(new ImageIcon("C:\\soala.jpg"));
    Dimension size = label.getPreferredSize();
    System.out.println("size: " + size.height);
    label.setBounds(50, 30, size.width, size.height);

    c.add(label);

    this.setVisible(true);

/*
    // label for the field
    JLabel inputLabel = new JLabel("Input:");
    this.add(inputLabel);

    // text input field
    input = new JTextField(20);
    this.add(input);

    // the current text
    currentTextLabel = new JLabel("To be displayed");
    this.add(currentTextLabel);

    // update button
    updateButton = new JButton("Update");
    updateButton.setActionCommand("UpdateAction");
    //updateButton.addActionListener(this);
    this.add(updateButton);
    // quit button

    quitButton = new JButton("Quit");
    quitButton.setActionCommand("QuitAction");
    //quitButton.addActionListener(this);
    this.add(quitButton);*/

    //this.addKeyListener(setupKeyboardListener());

    //this.pack();


  }

}
