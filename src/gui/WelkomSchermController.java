/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class WelkomSchermController extends GridPane {
    
    @FXML
    private ImageView btnNL;
    @FXML
    private ImageView btnFR;
    @FXML
    private ImageView btnEN;
    @FXML
    private RadioButton rbtnNieuwSpel;
    @FXML
    private RadioButton rbtnLaadSpel;
    @FXML
    private Button btnOK;

    private DomeinController dc;
    
    public WelkomSchermController(DomeinController dc)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelkomScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            this.dc=dc;
            loader.load();
            
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void btnNLClicked(MouseEvent event) {  
        dc.setTaal("NL");
        rbtnNieuwSpel.textProperty().set("Maak nieuw spel.");  
        rbtnNieuwSpel.visibleProperty().set(true);
        
        rbtnLaadSpel.textProperty().set("Laad een bestaand spel.");
        rbtnLaadSpel.visibleProperty().set(true);
       
        btnOK.visibleProperty().set(true);
        
        
        
    }

    @FXML
    private void btnFRClicked(MouseEvent event) {
        dc.setTaal("FR");
        rbtnNieuwSpel.textProperty().set("creë un nouveau jeu.");
        rbtnNieuwSpel.visibleProperty().set(true);
        
        rbtnLaadSpel.textProperty().set("Charger un jeu.");
        rbtnLaadSpel.visibleProperty().set(true);
        
        
        btnOK.visibleProperty().set(true);
    }

    @FXML
    private void btnENClicked(MouseEvent event) {
        dc.setTaal("EN");
        rbtnNieuwSpel.textProperty().set("Create new game.");
        rbtnNieuwSpel.visibleProperty().set(true);
        
        rbtnLaadSpel.textProperty().set("Load an exisiting game.");
        rbtnLaadSpel.visibleProperty().set(true);
        
        btnOK.visibleProperty().set(true);
    }

    @FXML
    private void rbtnNieuwSpel(MouseEvent event) {
        rbtnLaadSpel.selectedProperty().set(false);
    }

    @FXML
    private void rbtnLaadSpel(MouseEvent event) {
        rbtnNieuwSpel.selectedProperty().set(false);
    }

    @FXML
    private void btnOK(MouseEvent event) {
        if (rbtnNieuwSpel.selectedProperty().get()){
            //nieuw spel form
            String aantalSpelers;
            do{
            JFrame frame = new JFrame(dc.getTaal().getString("tekstAantalSpelers"));
            //variabele aantalSpelers bevat het aantal spelers
            aantalSpelers = JOptionPane.showInputDialog(frame,dc.getTaal().getString("tekstGeefAantalSpelers"),dc.getTaal().getString("tekstAantalSpelers"), JOptionPane.QUESTION_MESSAGE);
            if ((aantalSpelers == null) == false && dc.controleerAantal(dc.stringNaarInt(aantalSpelers))==false) {
              JOptionPane.showMessageDialog(null,dc.getTaal().getString("tekstErrorAantalSpelers"), "Error", JOptionPane.WARNING_MESSAGE);
            }
            }while((aantalSpelers == null) == false && dc.controleerAantal(dc.stringNaarInt(aantalSpelers))==false);
            
            if(aantalSpelers!=null){
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.close();
                stage = (Stage) (this.getScene().getWindow());
                NieuwSpelController root = new NieuwSpelController(aantalSpelers, dc);
                Scene scene = new Scene(root);
                stage.centerOnScreen();
                stage.setTitle(dc.getTaal().getString("tekstNieuwSpelMaken"));
                stage.setScene(scene);
                stage.show();
            }
        }else{
            //laad spel form
            Stage stage = (Stage) (this.getScene().getWindow());
            stage.close();
            stage = (Stage) (this.getScene().getWindow());
            BestaandSpelController root = new BestaandSpelController(dc);
            Scene scene = new Scene(root);
            stage.setTitle(dc.getTaal().getString("tekstBestaandSpelOpenen"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }
            
    }
}
