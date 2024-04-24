import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
    
    
  
  Parent root;
try {
    root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
    Scene scene = new Scene(root);
    primaryStage.setTitle("Chat viewer");
    primaryStage.setScene(scene);
    primaryStage.show();
} catch (IOException e) {
    e.printStackTrace();
}

}
 
 public static void main(String[] args) {
        launch(args);
    }
}