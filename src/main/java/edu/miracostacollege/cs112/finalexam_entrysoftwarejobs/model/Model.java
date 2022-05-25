package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

/**
 * The <code>Model</code> class represents the business logic (data and calculations) of the application.
 * In the Entry Software Jobs app, it loads previously stored data from a binary file.
 * It is also responsible for saving data to a binary file.
 *
 * @author <Christian Park>
 * @version 1.0
 */
public class Model {
	
	public static final File BINARY_FILE = new File("EntrySoftwareJobs.dat");

	/**
	 * Determines whether the binary file exists and has data (size/length > 1).
	 * @return True if the binary file exists and has data, false otherwise.
	 */
	public static boolean binaryFileHasData()
	{
		return (BINARY_FILE.exists() && BINARY_FILE.length() > 1L);
	}

	/**
	 * Populates the list of all jobs from the binary file. This will be called everytime the application loads.
	 * @return The list of all jobs populated from the binary file
	 */
	public static ObservableList<EntrySoftwareJob> populateListFromBinaryFile()
	{
		ObservableList<EntrySoftwareJob> allJobsList = FXCollections.observableArrayList();

		//TODO: Check to see if the binary file has data
		try {
			//TODO: If so, instantiate an ObjectInputStream reference to the binary file for reading
			ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
			// Read from binary file into an array
			//TODO: Create a temp array of Entry Software Job objects to read from the binary file
			//TODO: Initialize the temp array from the binary file reader.
			EntrySoftwareJob[] array = (EntrySoftwareJob[]) fileReader.readObject();
			//TODO: Add the temp array to the collection of all entry software jobs (list)
			for (EntrySoftwareJob nl : array)
				allJobsList.add(nl);
			//TODO: Close the binary file reader.
			fileReader.close();
		} catch (IOException | ClassNotFoundException e) {
			//TODO: If an exception occurs, print the message "Error opening binary file for reading."
			System.out.println("Error opening binary file for reading.");
		}
		return allJobsList;
	}

	/**
	 * Saves the list of all jobs to the binary file. This will be called each time the application stops,
	 * when the user exits/closes the app.  Note this method is called in the View, by the controller,
	 * during the stop() method.
	 * @return True if the data were saved to the binary file successfully, false otherwise.
	 */
	public static boolean writeDataToBinaryFile(ObservableList<EntrySoftwareJob> allJobsList)
	{
		//TODO: Create a temp array of Entry Software Job objects to read from binary file (length should match list size)
		EntrySoftwareJob[] array = new EntrySoftwareJob[allJobsList.size()];
		//TODO: Loop through the temp array and initialize each element to the corresponding one in the list
		for (int i = 0; i < array.length; i++) {
			array[i] = allJobsList.get(i);
		}
		//TODO: Write the temp array object to the binary file writer
		try {
			//TODO: Instantiate an ObjectOutputStream reference to the binary file for writing
			ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
			fileWriter.writeObject(array);
			fileWriter.close();
		} catch (IOException e) {
			//TODO: If an exception occurs, print its message and return false.
			System.out.println("Error: " + e.getMessage());
			return false;
		}
		//TODO: Close the binary file writer and return true.
		return true;
	}

}
