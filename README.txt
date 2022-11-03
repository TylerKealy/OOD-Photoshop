An overview and some description about the program.

Interfaces: 

PhotoshopController
	runs the program. Takes in a Model, View, and readable.
PhotoshopModel
	holds the functionality/logic of the program. Has methods like brigthen,flip,load,save etc.
PhotoshopView
	currently empty. Will hold functionality for viewing/displaying the program.
PhotoshopCommand
	using command design pattern. These commands are used through the controller.
	Saved in a hashmap, and runs corresponding methods in the given model.

Classes:

PhotoshopModelImpl
	does as the interface says. Simply an implementation of the interfacec as detailed. may
	pass in your own hashmap to use for testing purposes and allows for accessing outside of the
	class.
PhotoshopControllerImpl
	does as the interface says. Uses command design pattern in a Hashmap of commands. Uses purely
	text based commands currently.
MockPhotoshopModel
	this class simply holds a log of which functions are being called by the controller. Used to
	test the controller.
Direction
	An enum that is either horiztonal or vertical. used in the model for Flip.
RGB
	A class that holds data for RGB componenets of each pixel. Has a field for red, green, blue.
ComponentGreyscale
	An enum of all possible greyscale options supported by the model. Red,Green,Blue,Luma,Value,Intensity.
