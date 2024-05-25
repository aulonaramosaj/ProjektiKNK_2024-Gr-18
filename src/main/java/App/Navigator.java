package App;

import interfaces.AddressAddedListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Adresa;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

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
    public final static String STATISTICS = "Statistics.fxml";

    private static final Map<String, Object> params = new HashMap<>();
    private static ResourceBundle bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());

    public interface ParametrizedController {
        void setParams(Object params);
    }

    public static void navigate(Stage stage, String page) {
        Pane formPane = loadPane(page);
        if (formPane != null) {
            Scene newScene = new Scene(formPane);
            stage.setScene(newScene);
            stage.show();
        }
    }

    public static void navigate(Event event, String page) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }

    public static void navigate(ActionEvent ae, String page) {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        navigate(stage, page);
    }

    public static void navigate(ActionEvent ae, String page, Object param) {
        params.put(page, param);
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page), bundle);
            Parent parent = loader.load();
            Object controller = loader.getController();
            if (controller instanceof ParametrizedController) {
                ((ParametrizedController) controller).setParams(param);
            }
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void navigateWithListener(AnchorPane root, String page, AddressAddedListener listener) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(page), bundle);
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

    public static void navigate(Pane pane, String form) {
        Pane formPane = loadPane(form);
        if (formPane != null) {
            pane.getChildren().clear();
            pane.getChildren().add(formPane);
        }
    }

    public static void changeLanguage() {
        Locale defaultLocale = Locale.getDefault();
        if (defaultLocale.getLanguage().equals("en")) {
            Locale.setDefault(new Locale("sq"));
        } else {
            Locale.setDefault(Locale.ENGLISH);
        }
        bundle = ResourceBundle.getBundle("translations.content", Locale.getDefault());
    }

    public static Pane loadPane(String form) {
        try {
            FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(form), bundle);
            Parent formPane = loader.load();

            Object controller = loader.getController();
            if (controller instanceof ParametrizedController && params.containsKey(form)) {
                ((ParametrizedController) controller).setParams(params.get(form));
            }

            return (Pane) formPane;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}