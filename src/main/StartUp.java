
package main;

import domein.DomeinController;
import gui.WelkomSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application  {
    DomeinController dc = new DomeinController();
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        WelkomSchermController root = new WelkomSchermController(dc);
        Scene scene = new Scene(root);
        primaryStage.setTitle("De betoverde doolhof");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
