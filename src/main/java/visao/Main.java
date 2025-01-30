package visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Carrega a interface inicial do menu
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/menu.fxml"));
            Parent root = fxmlLoader.load();

            primaryStage.setTitle("Academia Smash Fitness - Menu");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
