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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class NieuwSpelController extends AnchorPane {
    int teller = 1;
    int aantalSpelersSpel = 0;
    private DomeinController dc = new DomeinController();
    @FXML
    private TextField txtNaam;
    @FXML
    private ChoiceBox<String> cbKleur;
    @FXML
    private Button btnTerug;
    @FXML
    private Button btnStartSpel;
    @FXML
    private TextField txtGeboortejaar;
    @FXML
    private Label lblSpeler;
    @FXML
    private Label lblAantalSpelers;
    @FXML
    private Label lblTekstKleur;
    @FXML
    private Label lblTekstGeboortejaar;
    @FXML
    private Label lblTekstNaam;
    @FXML
    private Label lblTekstSpelInformatie;
    @FXML
    private Label lblTekstSpeler;
    @FXML
    private Label lblTekstAantalSpelers;
    
    public NieuwSpelController(String aantalSpelers, DomeinController dc) {
        this.dc= dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NieuwSpel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            btnStartSpel.setText(dc.getTaal().getString("tekstVolgendeSpeler"));
            btnTerug.setText(dc.getTaal().getString("tekstTerug"));
            lblAantalSpelers.setText(dc.getTaal().getString("tekstAantalSpelers"));
            lblSpeler.setText(dc.getTaal().getString("tekstTerug"));
            lblTekstAantalSpelers.setText(dc.getTaal().getString("tekstAantalSpelers")+":");
            lblTekstGeboortejaar.setText(dc.getTaal().getString("tekstGeboortejaar")+":");
            lblTekstKleur.setText(dc.getTaal().getString("tekstKleur")+":");
            lblTekstNaam.setText(dc.getTaal().getString("tekstNaam"));
            lblTekstSpelInformatie.setText(dc.getTaal().getString("tekstSpelGegevens")+":");
            lblTekstSpeler.setText(dc.getTaal().getString("tekstSpeler"));
            aantalSpelersSpel = Integer.parseInt(aantalSpelers);  
            cbKleur.getItems().addAll(dc.getTaal().getString("tekstKleurRood"),dc.getTaal().getString("tekstKleurBlauw"),dc.getTaal().getString("tekstKleurGeel"),dc.getTaal().getString("tekstKleurGroen"));
            cbKleur.setValue(cbKleur.getItems().get(0));
            lblAantalSpelers.setText(aantalSpelers);
            lblSpeler.setText("" + teller);
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
}

    @FXML
    private void btnTerug(MouseEvent event) {
            dc.spelers.clear();
            Stage stage = (Stage) (this.getScene().getWindow());
            WelkomSchermController root = new WelkomSchermController(dc);
            Scene scene = new Scene(root);
            stage.setTitle(dc.getTaal().getString("tekstDeBetoverdeDoolhof"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
    }

    @FXML
    private void btnVolgende(MouseEvent event) {
       
        if(dc.controleerNaamSpeler(txtNaam.getText(0, txtNaam.getLength())) && dc.controleerGeboortejaar(Integer.parseInt(txtGeboortejaar.getText())))
        {
            teller ++;
            dc.voegSpelerToe(txtNaam.getText().trim(), (Integer.parseInt(txtGeboortejaar.getText())), cbKleur.getItems().indexOf(cbKleur.getValue()));
            cbKleur.getItems().remove(cbKleur.getValue());
        }
        else JOptionPane.showMessageDialog(null, "Naam moet uniek zijn en mag geen cijfers, speciale karakters of spaties bevatten" + "\n" + "U mag niet ouder zijn dan 90 jaar.","Error", JOptionPane.WARNING_MESSAGE);
        txtNaam.setText("");
        txtGeboortejaar.setText("");
        if(cbKleur.getItems().size()>0)
            cbKleur.setValue(cbKleur.getItems().get(0));
        lblSpeler.setText("" + teller);
        if(teller == aantalSpelersSpel)
        {
            btnStartSpel.setText(dc.getTaal().getString("tekstStartSpel"));
        }
        if(teller>aantalSpelersSpel)
        {
            dc.maakNieuwSpelGUI(dc.spelers);
            int n = JOptionPane.showConfirmDialog(
            null,
            dc.toonTekstSpelersGUI(dc.getTaal()),
            dc.getTaal().getString("tekstNieuwSpelMaken"),
            JOptionPane.YES_NO_OPTION);

            if(n==0){
                //JOptionPane.showMessageDialog(null, "HELLO");
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
            }
            else 
            {
                //JOptionPane.showMessageDialog(null, "GOODBYE");
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.close();
            }
        }
        
    }  
}
