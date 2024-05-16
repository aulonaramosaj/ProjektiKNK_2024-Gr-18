package ModifikoQytetarin;

import Database.DatabaseUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ModifikoQytetarin extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(ModifikoQytetarin.class.getResource("ModifikoQytetarin.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane, pane.getWidth()-100, pane.getHeight()-100);
        stage.setTitle("Aplikacion per regjistrimin e adresave dhe vendbanimeve");
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[]args) throws SQLException {
        launch();
    }
}

