import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static void main(String[] args) throws IOException {
        FileHandler fileHandler = new FileHandler("error.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        Scene mainScreen = new Scene(root);
        primaryStage.setScene(mainScreen);
        primaryStage.centerOnScreen();
        primaryStage.show();
        primaryStage.setTitle("Liquor Stock Management");
    }
}
