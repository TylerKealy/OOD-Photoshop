package controller;

import model.PhotoshopModel;

/**
 * A Photoshop command is a command to be executed on the model.
 * Used for text commands in console.
 */
public interface PhotoshopCommand {
  void run(PhotoshopModel model);
}
