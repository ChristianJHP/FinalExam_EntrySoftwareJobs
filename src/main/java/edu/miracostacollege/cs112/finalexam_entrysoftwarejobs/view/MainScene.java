package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.view;

import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.controller.Controller;
import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model.EntrySoftwareJob;
import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model.JuniorDeveloper;
import edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model.QATester;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * The <code>MainScene</code> represents the very first scene for the Entry-Level Software Jobs application.
 * The <code>MainScene</code> also allows for a user to add a new job or remove an existing job,
 *
 * @author <Your Name Here>
 * @version 1.0
 */
public class MainScene extends Scene {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 800;

    private ImageView jobIV = new ImageView();
    private ComboBox<String> jobTypeCB = new ComboBox<>();
    private TextField companyTF = new TextField();
    private Label companyErrLabel = new Label("Company is required.");

    private TextField payTF = new TextField();
    private Label payErrLabel = new Label("Pay is required ;)");

    private CheckBox remoteCkB = new CheckBox();

    private TextField requirementsTF = new TextField();
    private Label requirementsErrLabel = new Label("Job requirements are...required!");

    private Label languagesAutomationsLabel = new Label("Languages Used:");
    private TextField languagesAutomationsTF = new TextField();
    private Label languagesAutomationsErrLabel = new Label("Programming language is required.");

    private ListView<EntrySoftwareJob> jobsLV = new ListView<>();

    private Button removeButton = new Button("- Remove Job");
    private Button addButton = new Button("+ Add Job");

    private Controller controller = Controller.getInstance();
    private ObservableList<EntrySoftwareJob> jobsList;
    private EntrySoftwareJob selectedJob;

    /**
     * Constructs a new <code>MainScene</code>, representing the very first scene for the entry level software jobs application.
     * The <code>MainScene</code> also allows for a user to add a new job or remove an existing job
     */
    public MainScene() {
        super(new GridPane(), WIDTH, HEIGHT);

        GridPane pane = new GridPane();
        pane.setHgap(10.0);
        pane.setVgap(5);
        pane.setPadding(new Insets(5));

        jobIV.setImage(new Image("EntrySoftwareJobs.png"));
        jobIV.setFitWidth(WIDTH);
        pane.add(jobIV, 0, 0, 3, 1);

        pane.add(new Label("Job Type:"), 0, 1);
        pane.add(jobTypeCB, 1, 1);

        pane.add(new Label("Company:"), 0, 2);
        pane.add(companyTF, 1, 2);
        pane.add(companyErrLabel, 2, 2);
        companyErrLabel.setTextFill(Color.RED);
        companyErrLabel.setVisible(false);

        pane.add(new Label("Pay $:"), 0, 3);
        pane.add(payTF, 1, 3);
        pane.add(payErrLabel, 2, 3);
        payErrLabel.setTextFill(Color.RED);
        payErrLabel.setVisible(false);

        pane.add(new Label("Remote?:"), 0, 4);
        pane.add(remoteCkB, 1, 4);

        pane.add(new Label("Job Requirements:"), 0, 5);
        pane.add(requirementsTF, 1, 5);
        pane.add(requirementsErrLabel, 2, 5);
        requirementsErrLabel.setTextFill(Color.RED);
        requirementsErrLabel.setVisible(false);

        pane.add(languagesAutomationsLabel, 0, 6);
        pane.add(languagesAutomationsTF, 1, 6);
        pane.add(languagesAutomationsErrLabel, 2, 6);
        languagesAutomationsErrLabel.setTextFill(Color.RED);
        languagesAutomationsErrLabel.setVisible(false);

        pane.add(addButton, 1, 7);
        jobsLV.setPrefWidth(WIDTH);
        pane.add(jobsLV, 0, 8, 3, 1);
        pane.add(removeButton, 0, 9);

        //TODO: Uncomment the following line after implementing the Controller specifications
        jobsList = controller.getAllJobs();
        jobsLV.setItems(jobsList);


        //TODO: Add the Junior Developer and QA Tester items to the job type combo box
        jobTypeCB.getItems().addAll("Junior Developer", "QA Tester");
        jobTypeCB.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> changeNameLabel(newVal));
        //TODO: Then add a listener on the selectedItemProperty
        jobsLV.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> selectJob(newVal));
        //TODO: If the user selects Junior Developer, change the text of the languagesAutomationLabel to "Languages Used:"
        //TODO: Otherwise, change the text to "Test Automation:"
        //TODO: Finally, select Junior Developer in the combo box by default.
        jobTypeCB.getSelectionModel().select("Junior Developer");
        //TODO: For the jobs list view, add a listener when the selected item changes.
        //TODO: In the changed(..) method, assign the selectedJob to the new value
        //TODO: Disable/enable the removeButton according to whether the selectedJob is null
        if (selectedJob == null)
            removeButton.setDisable(true);
        addButton.setOnAction(event -> addJob());
        removeButton.setOnAction(event -> removeJob());

        this.setRoot(pane);

    }

    private void changeNameLabel(String newVal){
        if (newVal.equals("Junior Developer"))
            languagesAutomationsLabel.setText("Languages Used");
        else
            languagesAutomationsLabel.setText("Test Automation");

        updateDisplay();
    }

    private void selectJob(EntrySoftwareJob newVal) {
        selectedJob = newVal;
        removeButton.setDisable(selectedJob == null);
    }

    /**
     * Allows the user to add a new job to the list view.
     * Make sure to update the display (list view and combo boxes) after adding the new job.
     */
    private void addJob() {
        String company, requirements, languagesAutomation;
        double pay=0.0;
        boolean remote;

        company = companyTF.getText();
        companyErrLabel.setVisible(company.isEmpty());

        try {
            pay = Double.parseDouble(payTF.getText());
            payErrLabel.setVisible(pay < 0.0);
        } catch (NumberFormatException e) {
            payErrLabel.setVisible(true);
        }

        remote = remoteCkB.isSelected();

        requirements = requirementsTF.getText();
        requirementsErrLabel.setVisible(requirements.isEmpty());

        languagesAutomation = languagesAutomationsTF.getText();
        languagesAutomationsErrLabel.setVisible(languagesAutomation.isEmpty());

        if (companyErrLabel.isVisible() || payErrLabel.isVisible() ||
                languagesAutomationsErrLabel.isVisible())
            return;

        //TODO: If the selected item in the job type combo box is "Junior Developer", then instantiate new JuniorDeveloper object and add it to the jobs list
        if (jobTypeCB.equals("Junior Developer"))
            jobsList.add(0, new JuniorDeveloper(company, pay, remote, requirements, languagesAutomation));
            //TODO: Otherwise, instantiate new QATester object and add it to the list of jobs
        else
            jobsList.add(0, new QATester(company, pay, remote, requirements, languagesAutomation));
        //TODO: Update the display when done.
        updateDisplay();
        //TODO: Clear the inputs (all text fields are cleared and the combo box is set to “Junior Developer”)
        jobTypeCB.getSelectionModel().select("Junior Developer");
        //TODO: Please use the clearInputs() method below
        clearInputs();
    }

    /**
     * Allows the user to remove an existing job.
     * However, if the selected job is null, just return (do nothing)
     * Make sure to update the display (list view and combo boxes) after removing the job.
     */
    private void removeJob() {
        //TODO:	If the selected job is null, return

        if (selectedJob == null)
            return;
        //TODO: Otherwise, remove the selected job from the list
        jobsList.remove(selectedJob);
        //TODO:	Update the display when done.
        jobsLV.refresh();
        jobsLV.getSelectionModel().select(-1);
        updateDisplay();
    }

    /**
     * Updates the display after adding/removing a job.
     */
    private void updateDisplay()
    {
        // TODO: Uncomment after implementing the Comparable interface in EntrySoftwareJob.java
        FXCollections.sort(jobsList);
        jobsLV.refresh();
    }

    /**
     * Inputs (text fields and checkbox) are all cleared and the job type is
     * restored back to Junior Developer.
     */
    private void clearInputs()
    {
        // TODO: Select the "Junior Developer" item in the combo box
        // TODO: Clear all text boxes
        payTF.setText("");
        languagesAutomationsTF.setText("");
        companyTF.setText("");
        requirementsTF.setText("");
        remoteCkB.setSelected(false);
        // TODO: Make the remoteCkB (check box) be unselected
        remoteCkB.setSelected(false);
        updateDisplay();

    }

}
