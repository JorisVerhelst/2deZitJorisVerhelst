/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cui.ConsoleApplicatie;
import domein.DomeinController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;

/**
 *
 * @author Daniel
 */
public class StartUpConsole extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        DomeinController dc = new DomeinController();
        ConsoleApplicatie ca = new ConsoleApplicatie(dc);
    }
    
}
