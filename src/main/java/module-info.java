module edu.miracostacollege.cs112.finalexam_entrysoftwarejobs {
  requires javafx.controls;
  requires javafx.fxml;


  opens edu.miracostacollege.cs112.finalexam_entrysoftwarejobs to javafx.fxml;
  exports edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.view;
}