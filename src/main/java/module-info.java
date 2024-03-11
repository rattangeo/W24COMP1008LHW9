module com.example.w24comp1008lhw9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w24comp1008lhw9 to javafx.fxml;
    exports com.example.w24comp1008lhw9;
}