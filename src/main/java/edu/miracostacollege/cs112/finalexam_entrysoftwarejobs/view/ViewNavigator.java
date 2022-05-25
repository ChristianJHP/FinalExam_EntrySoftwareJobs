package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This static class is used to navigate between scenes (e.g. MainScene).
 *
 * @author Michael Paulding
 * @version 1.0
 */
public class ViewNavigator {

    private static Stage mainStage;

    /**
     * Sets the main stage (window) when the application first loads (in the View class)
     * @param stage The main stage
     */
    public static void setStage(Stage stage) {
        mainStage = stage;
        // Sets the icon to the EntrySoftwareJobsIcon.png file in the res directory
        mainStage.getIcons().add(new Image("EntrySoftwareJobsIcon.png"));
    }

    /**
     * Navigates from one scene to another
     * @param title The title to display on the stage
     * @param scene The new scene to load
     */
    public static void loadScene(String title, Scene scene) {
        mainStage.setTitle(title);
        mainStage.setScene(scene);
        mainStage.show();
    }

}
