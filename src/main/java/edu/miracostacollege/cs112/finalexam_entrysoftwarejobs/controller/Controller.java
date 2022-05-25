package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.controller;


import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model.Model;
import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model.EntrySoftwareJob;
import javafx.collections.ObservableList;

/**
 * The <code>Controller</code> is a Singleton object that relays all commands between the Model and View
 * (and vice versa).  There is only one Controller object, accessible by a call to the static getInstance()
 * method.
 *
 * @author <Your Name Here>
 * @version 1.0
 */
public class Controller {

	private static Controller theInstance;
	private ObservableList<EntrySoftwareJob> mAllJobsList;

	//TODO: Create a private constructor to enforce Singleton pattern (separate from getInstance())
	private Controller() {
	}
	/**
	 * Gets the one instance of the Controller.
	 * @return The instance
	 */
	public static Controller getInstance() {
		//TODO: If the instance is null, assign it to a new Controller, then check to see if the binary file has data
		if (theInstance == null)
			theInstance = new Controller();
		theInstance.mAllJobsList = Model.populateListFromBinaryFile();
		//TODO: If so, assign the mAllJobsList to the populateListFromBinaryFile() method
		//TODO: Otherwise, assign it to a new observableArrayList()
		return theInstance;
	}

	/**
	 * Gets the list of all jobs.
	 * @return The list of all jobs.
	 */
	public ObservableList<EntrySoftwareJob> getAllJobs() {
		return mAllJobsList;
	}

	/**
	 * Makes a request for the model to save all data (the list of all jobs) to
	 * a persistent binary file.
	 */
	public void saveData() {
		Model.writeDataToBinaryFile(mAllJobsList);
	}
}
