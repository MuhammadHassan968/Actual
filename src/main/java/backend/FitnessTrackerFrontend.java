package backend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FitnessTrackerFrontend extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fitnessTrackerFrontend2.fxml"));
        stage.setScene(new Scene(root, 984, 492));
        stage.setTitle("Fitness Tracker");
        stage.show();
// slider bnanana hy
        Parent root1 = FXMLLoader.load(getClass().getResource("UserData.fxml"));
        stage.setScene(new Scene(root1, 979, 500));
        stage.setTitle("Fitness Tracker");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}