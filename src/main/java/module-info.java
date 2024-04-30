module com.projekti.projektiknk_2024gr18 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.projekti.projektiknk_2024gr18 to javafx.fxml;
    exports com.projekti.projektiknk_2024gr18;

    exports App;
    opens controller to javafx.fxml;
}