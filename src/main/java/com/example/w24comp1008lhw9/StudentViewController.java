package com.example.w24comp1008lhw9;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ResourceBundle;

public class StudentViewController implements Initializable {

    @FXML
    private Label ageLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private ListView<Student> listView;

    @FXML
    private Label numStudentsLabel;

    @FXML
    void exportToCSV(ActionEvent event) {
    try (
            Formatter formatter = new Formatter("students.csv");
        ){
        formatter.format("Student Num,First Name, Last Name, Age%n");
        for (Student student : listView.getItems()){
            formatter.format("%d,%s,%s,%d%n",student.getStudentNum(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getAge());
        }
    }catch (Exception e){
        e.printStackTrace();
    }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<Student> students = DBUtility.getStudentsFromDB();
        listView.getItems().addAll(students);

        // add a listener for listView
        listView.getSelectionModel()
                .selectedItemProperty()
                .addListener(((observable, oldValue, studentSelected) -> {
                    firstNameLabel.setText("First Name: " + studentSelected.getFirstName());
                    lastNameLabel.setText("Last Name: " + studentSelected.getLastName());
                    ageLabel.setText("Age: " + studentSelected.getAge());
                }));



    }
}
