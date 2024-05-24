module com.example.jeu {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.jeu to javafx.fxml;
    exports com.example.jeu;
}