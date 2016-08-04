/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Matthias
 */
public class BestaandSpelController extends AnchorPane
{
    @FXML
    private Button btnStartSpel;
    @FXML
    private AnchorPane GridPane;
    @FXML
    private Button btnTerug;
   
    DomeinController dc;
    @FXML
    private Label lbltekstNaam;
    @FXML
    private Label lblTekstAantalSpelers;
    @FXML
    private Label lblTesktNaam1;
    @FXML
    private Label lblTekstKleur;
    @FXML
    private Label lblTekstGeboortejaar;
    @FXML
    private Label lblTekstSpelGegevens;
    @FXML
    private ListView<?> listViewSpelnamen;
    @FXML
    private GridPane gridPaneGegevensSpel;
    
    public BestaandSpelController(DomeinController dc) {
        this.dc=dc;
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BestaandSpel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        
        try
        {
            loader.load();
            lbltekstNaam.setText(dc.getTaal().getString("tekstNaam"));
            lblTesktNaam1.setText(dc.getTaal().getString("tekstNaam"));
            lblTekstKleur.setText(dc.getTaal().getString("tekstKleur"));
            lblTekstGeboortejaar.setText(dc.getTaal().getString("tekstGeboortejaar"));
            lblTekstSpelGegevens.setText(dc.getTaal().getString("tekstSpelGegevens"));
            btnStartSpel.setText(dc.getTaal().getString("tekstStartSpel"));
            btnTerug.setText(dc.getTaal().getString("tekstTerug"));
            vulListView();
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
}

    @FXML
    private void btnStart(MouseEvent event) {
        
        if(listViewSpelnamen.getSelectionModel().getSelectedItem()!=null)
        {
            if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep())
            {
                dc.laadSpel(listViewSpelnamen.getSelectionModel().getSelectedItem().toString());
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.close();
                stage = (Stage) (this.getScene().getWindow());
                DoolhofController root = new DoolhofController(dc);
                Scene scene = new Scene(root);
                stage.setTitle(dc.getTaal().getString("tekstDeBetoverdeDoolhof"));
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.setResizable(false);
                stage.show();
            }else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGeenVerbindingMetInternetOphalen"), null, JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void btnTerug(MouseEvent event) {
            Stage stage = (Stage) (this.getScene().getWindow());
            WelkomSchermController root = new WelkomSchermController(dc);
            Scene scene = new Scene(root);
            stage.setTitle(dc.getTaal().getString("tekstDeBetoverdeDoolhof"));
            stage.setScene(scene);
            stage.show();
    }

    private void vulListView()
    {
        Collection names = new ArrayList<>();
        
        dc.setSpelRepository();
        dc.setSpelerRepository();
        if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep()){
            names = dc.geefNamenBestaandeSpellen();
            dc.sluitVerbindingMetDatabank();
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGeenVerbindingMetInternetOphalen"), null, JOptionPane.ERROR_MESSAGE);
        
        listViewSpelnamen.getItems().setAll(names);
        
        //listViewSpelnamen.setItems(" "," ");
    }
    
    @FXML
    private void selectSpelnaam(MouseEvent event) {
        if(listViewSpelnamen.getSelectionModel().getSelectedItem()!=null){
            if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep())
            {
                dc.toonGegevensGeselecteerdeSpel(listViewSpelnamen.getSelectionModel().getSelectedItem().toString(), gridPaneGegevensSpel);
                dc.sluitVerbindingMetDatabank();
            }else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGeenVerbindingMetInternetOphalen"), null, JOptionPane.ERROR_MESSAGE);

        }
            
    }
    
}
