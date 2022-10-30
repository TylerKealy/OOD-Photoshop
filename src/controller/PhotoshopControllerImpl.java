package controller;

import model.PhotoshopModel;
import model.PhotoshopModelImpl;
import view.PhotoshopView;

public class PhotoshopControllerImpl implements PhotoshopController {
  PhotoshopModel model;
  PhotoshopView view;

  public PhotoshopControllerImpl(PhotoshopModel model, PhotoshopView view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void run() {

  }
}
