package App;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Navigator {
    public final static String SIGNUP_PAGE = "SignUp.fxml";
    public final static String LOGIN_PAGE = "Login.fxml";
    public final static String ADRESA = "Adresa.fxml";
    public final static String HOME_PAGE = "Home.fxml";
    public final static String QYTETARI="Qytetari.fxml";
    public final static String HELP = "Help.fxml";
    public final static String MODIFIKO_QYTETARIN = "ModifikoQytetarin.fxml";
    public final static String MODIFIKO_ADRESEN = "ModifikoAdresen.fxml";


    private static final Map<String, Object> params = new HashMap<>();

    public static void navigate(Stage stage, String page){
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ParametrizedController && params.containsKey(page)) {
                ((ParametrizedController) controller).setParams(params.get(page));
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void navigate(Event event, String page) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }


    public static void navigate(Event event, String page, Object param) {
        params.put(page, param); // Store parameter
        navigate(event, page);
    }


    public interface ParametrizedController {
        void setParams(Object params);
    }
}
