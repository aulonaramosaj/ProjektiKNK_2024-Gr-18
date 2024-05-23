package App;

import interfaces.AddressAddedListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Adresa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Navigator {
    public static final String SIGNUP_PAGE = "SignUp.fxml";
    public static final String LOGIN_PAGE = "Login.fxml";
    public static final String ADRESA = "Adresa.fxml";
    public static final String HOME_PAGE = "Home.fxml";
    public static final String QYTETARI = "Qytetari.fxml";
    public static final String HELP = "Help.fxml";
    public static final String MODIFIKO_QYTETARIN = "ModifikoQytetarin.fxml";
    public static final String MODIFIKO_ADRESEN = "ModifikoAdresen.fxml";
    public static final String ADRESA_DASHBOARD = "AdresaDashboard.fxml";
    public final static String QYTETARI_DASHBOARD = "QytetariDashboard.fxml";

    private static final Map<String, Object> params = new HashMap<>();

    public static void navigate(Stage stage, String page) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page));
            Parent root = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ParametrizedController) {
                ParametrizedController parametrizedController = (ParametrizedController) controller;
                Object param = params.get(page);
                if (param != null) {
                    parametrizedController.setParams(param);
                }
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void navigate(Event event, String page) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }

    public static void navigate(Event event, String page, Object param) {
        params.put(page, param);
        navigate(event, page);
    }

    public static void navigateWithListener(AnchorPane root, String page, AddressAddedListener listener) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page));
            Parent parent = loader.load();

            Object controller = loader.getController();
            if (controller instanceof AddressAddedListener) {
                ((AddressAddedListener) controller).onAddressAdded((Adresa) listener);
            }

            Stage stage = (Stage) root.getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface ParametrizedController {
        void setParams(Object params);
    }

    public static void navigateWithParams(ActionEvent ae, String page, Object param) {
        params.put(page, param);
        navigate(ae, page);
    }
}
