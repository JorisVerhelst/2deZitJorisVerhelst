/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.awt.Component;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class DoolhofController extends AnchorPane{
    @FXML
    private Button btnCoordinaat01;
    @FXML
    private Button btnCoordinaat03;
    @FXML
    private Button btnCoordinaat05;
    @FXML
    private Button btnCoordinaat10;
    @FXML
    private Button btnCoordinaat30;
    @FXML
    private Button btnCoordinaat50;
    @FXML
    private Button btnCoordinaat16;
    @FXML
    private Button btnCoordinaat36;
    @FXML
    private Button btnCoordinaat56;
    @FXML
    private Button btnCoordinaat61;
    @FXML
    private Button btnCoordinaat65;
    @FXML
    private Button btnCoordinaat63;
    @FXML
    private Label lblZwevendeTegel;
    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane gridPaneZwevendeTegel;
    @FXML
    private Label lblTextSpelerAanDeBeurt;
    @FXML
    private Label lblTextNaamSpelerAanDeBeurt;
    @FXML
    private GridPane gridPanePionSpeler;
    @FXML
    private Label lblTextNaam;
    @FXML
    private Label lblTextGeboortejaar;
    @FXML
    private Label lblTextKleur;
    @FXML
    private Label lblNaamSpeler;
    @FXML
    private Label lblGeboortejaar;
    @FXML
    private Label lblKleur;
    @FXML
    private Label lblTextPion;
    @FXML
    private StackPane stackPaneSpelerKaart;
    @FXML
    private Label lblTextTeVindenSchatOfVolgendeSchat;
    @FXML
    private Button btnSchuifTegelIn;
    @FXML
    private Button btnVerplaatsSpeler;
    @FXML
    private Button btnSlaSpelOp;
    @FXML
    private Button btnEindeBeurt;
    
    
    private StackPane stackPane;
    private DomeinController dc;
    
    @FXML
    private Button btnDraaiTegelLinks90;
    @FXML
    private Button btnDraaiTegelRechts90;
    @FXML
    private Button btnVerplaatsBoven;
    @FXML
    private Button btnVerplaatsLinks;
    @FXML
    private Button btnVerplaatsBeneden;
    @FXML
    private Button btnVerplaatsRechts;
    @FXML
    private AnchorPane mainAnchorPane;
    
    
    public DoolhofController(DomeinController dc) {
        this.dc=dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Doolhof.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
            lblTextGeboortejaar.setText(dc.getTaal().getString("tekstGeboortejaar")+":");
            lblTextKleur.setText(dc.getTaal().getString("tekstKleur")+":");
            lblTextNaam.setText(dc.getTaal().getString("tekstNaam")+":");
            lblTextSpelerAanDeBeurt.setText(dc.getTaal().getString("tekstSpelerAanDeBeurt"));
            lblTextTeVindenSchatOfVolgendeSchat.setText(dc.getTaal().getString("tekstSchatVinden"));
            lblZwevendeTegel.setText(dc.getTaal().getString("tekstLosseTegel"));
            btnSchuifTegelIn.setText(dc.getTaal().getString("tekstSchuifTegelIn"));
            btnEindeBeurt.setText(dc.getTaal().getString("tekstEindeBeurt"));
            btnSlaSpelOp.setText(dc.getTaal().getString("tekstSlaSpelOp"));
            btnVerplaatsSpeler.setText(dc.getTaal().getString("tekstButtonVerplaatsSpeler"));
            FadeTransition ft = new FadeTransition(Duration.millis(2000), gridPane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
            tekenDoolhof();
            tekenZwevendeTegel();
            speelBeurt();
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
}
    private void toonGegevensSpeler()
    {
        lblTextNaamSpelerAanDeBeurt.setText(dc.getNaamSpelerAanDeBeurt());
        lblNaamSpeler.setText(dc.getNaamSpelerAanDeBeurt());
        lblGeboortejaar.setText(dc.getGeboorteJaarSpelerAanDeBeurt());
        lblKleur.setText(dc.getKleurSpelerAanDeBeurt());
        tekenPionSpeler();
        tekenSpelerKaart();
    }
    private void tekenDoolhof() 
    {
        ImageView imgV = new ImageView();
        gridPane.getChildren().clear();
        dc.tekenDoolhofGUI(gridPane,imgV);
    }
    private void tekenZwevendeTegel()
    {
        ImageView imgVZwevendeTegel = new ImageView();
        gridPaneZwevendeTegel.getChildren().clear();
        dc.tekenZwevendeTegelGUI(gridPaneZwevendeTegel, imgVZwevendeTegel);
    }
    private void tekenPionSpeler()
    {
        ImageView imgVPion = new ImageView();
        gridPanePionSpeler.getChildren().clear();
        dc.tekenPionSpeler(gridPanePionSpeler, imgVPion);
    }
    
    private void speelBeurt()
    {
        JOptionPane.showMessageDialog(null, dc.speelSpelGUI(), null, JOptionPane.INFORMATION_MESSAGE, null);
        toonGegevensSpeler();
        zetButtonsInschuivenFalse();
        zetButtonsVerplaatsenFalse();
        btnEindeBeurt.setDisable(true);
        btnSlaSpelOp.setDisable(true);
        btnVerplaatsSpeler.setDisable(true);
        btnSchuifTegelIn.setDisable(false); 
    }
    
    ImageView imgVVoorkantSpelerKaart; //= new ImageView();
    ImageView imgVAchterkantSpelerKaart; //= new ImageView();
    private void tekenSpelerKaart()
    { 
        stackPaneSpelerKaart.getChildren().clear();
        imgVVoorkantSpelerKaart = new ImageView();
        imgVAchterkantSpelerKaart = new ImageView();
        dc.tekenSpelerKaart(imgVVoorkantSpelerKaart,imgVAchterkantSpelerKaart,stackPaneSpelerKaart);
        SequentialTransition animation = new SequentialTransition(
                flip(stackPaneSpelerKaart.getChildren().get(1), stackPaneSpelerKaart.getChildren().get(0)) // L'animation de 0° à 180° 
        );
        animation.play();
    }
    
    double rotatiePositie = 0;
    @FXML
    private void draaiTegel(MouseEvent event) {
        
        if (event.getButton() == MouseButton.SECONDARY){        //bij rechtsklikken op de tegel draait 90° naar rechts
            dc.draaiTegel(1);
            rotatiePositie+=90;
            RotateTransition rt = new RotateTransition(Duration.millis(500), gridPaneZwevendeTegel);
            rt.setByAngle(rotatiePositie-gridPaneZwevendeTegel.getRotate());
            rt.play();
        }
        else if (event.getButton() == MouseButton.PRIMARY){        //bij rechtsklikken op de tegel draait 90° naar rechts
            dc.draaiTegel(2);
            rotatiePositie-=90;
            RotateTransition rt = new RotateTransition(Duration.millis(500), gridPaneZwevendeTegel);
            rt.setByAngle(rotatiePositie-gridPaneZwevendeTegel.getRotate());
            rt.play();
        }
    }
    
    @FXML
    private void draaiTegelLinks90(MouseEvent event) {
        dc.draaiTegel(2);
        rotatiePositie-=90;
        RotateTransition rt = new RotateTransition(Duration.millis(500), gridPaneZwevendeTegel);
        rt.setByAngle(rotatiePositie-gridPaneZwevendeTegel.getRotate());
        rt.play();
    }
    
    @FXML
    private void draaiTegelRechts90(MouseEvent event) {
        dc.draaiTegel(1);
        rotatiePositie+=90;
        RotateTransition rt = new RotateTransition(Duration.millis(500), gridPaneZwevendeTegel);
        rt.setByAngle(rotatiePositie-gridPaneZwevendeTegel.getRotate());
        rt.play();
    }

    private Transition flip(Node front, Node back) { 
        final RotateTransition rotateOutFront = new RotateTransition(Duration.millis(500), front); 
        rotateOutFront.setInterpolator(Interpolator.LINEAR); 
        rotateOutFront.setAxis(Rotate.Y_AXIS); 
        rotateOutFront.setFromAngle(0); 
        rotateOutFront.setToAngle(90); 
        // 
        final RotateTransition rotateInBack = new RotateTransition(Duration.millis(500), back); 
        rotateInBack.setInterpolator(Interpolator.LINEAR); 
        rotateInBack.setAxis(Rotate.Y_AXIS); 
        rotateInBack.setFromAngle(-90); 
        rotateInBack.setToAngle(0); 
        // 
        return new SequentialTransition(rotateOutFront, rotateInBack); 
    }

    @FXML
    private void schuifTegelInCoordinaat05(MouseEvent event) {
        if(dc.voerTegelIn(0,5))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat03(MouseEvent event) {
        if(dc.voerTegelIn(0,3))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false); 
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat01(MouseEvent event) {
        if(dc.voerTegelIn(0,1))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat65(MouseEvent event) {
        if(dc.voerTegelIn(6,5))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat63(MouseEvent event) {
        if(dc.voerTegelIn(6,3))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false); 
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat61(MouseEvent event) {
        if(dc.voerTegelIn(6,1))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat50(MouseEvent event) {
        if(dc.voerTegelIn(5,0))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat30(MouseEvent event) {
        if(dc.voerTegelIn(3,0))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat10(MouseEvent event) {
        if(dc.voerTegelIn(1,0))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat56(MouseEvent event) {
        if(dc.voerTegelIn(5,6))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat36(MouseEvent event) {
        if(dc.voerTegelIn(3,6))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelInCoordinaat16(MouseEvent event) {
        if(dc.voerTegelIn(1,6))
        {
            tekenDoolhof();
            tekenZwevendeTegel();
            gridPaneZwevendeTegel.setRotate(0);
            rotatiePositie=0;
            zetButtonsInschuivenFalse();
            btnVerplaatsSpeler.setDisable(false);
            btnEindeBeurt.setDisable(false);
            btnSlaSpelOp.setDisable(false);
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGekozenRichting"), null, JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void schuifTegelIn(MouseEvent event) {
        btnSchuifTegelIn.setDisable(true);
        btnCoordinaat01.setDisable(false);
        btnCoordinaat03.setDisable(false);
        btnCoordinaat05.setDisable(false);
        btnCoordinaat10.setDisable(false);
        btnCoordinaat16.setDisable(false);
        btnCoordinaat30.setDisable(false);
        btnCoordinaat36.setDisable(false);
        btnCoordinaat50.setDisable(false);
        btnCoordinaat56.setDisable(false);
        btnCoordinaat61.setDisable(false);
        btnCoordinaat63.setDisable(false);
        btnCoordinaat65.setDisable(false);
    }
    
    
    private void zetButtonsInschuivenFalse(){
        btnCoordinaat01.setDisable(true);
        btnCoordinaat03.setDisable(true);
        btnCoordinaat05.setDisable(true);
        btnCoordinaat10.setDisable(true);
        btnCoordinaat16.setDisable(true);
        btnCoordinaat30.setDisable(true);
        btnCoordinaat36.setDisable(true);
        btnCoordinaat50.setDisable(true);
        btnCoordinaat56.setDisable(true);
        btnCoordinaat61.setDisable(true);
        btnCoordinaat63.setDisable(true);
        btnCoordinaat65.setDisable(true);
    }
    
    private void zetButtonsVerplaatsenFalse()
    {
        btnVerplaatsBoven.setDisable(true);
        btnVerplaatsBeneden.setDisable(true);
        btnVerplaatsLinks.setDisable(true);
        btnVerplaatsRechts.setDisable(true);
        mainAnchorPane.setOnKeyPressed(null);
    }
    private void zetButtonsVerplaatsenTrue()
    {
        btnVerplaatsBoven.setDisable(false);
        btnVerplaatsBeneden.setDisable(false);
        btnVerplaatsLinks.setDisable(false);
        btnVerplaatsRechts.setDisable(false);
        mainAnchorPane.setOnKeyPressed(this::verplaats);
    }

    @FXML
    private void verplaatsSpeler(MouseEvent event) {
        zetButtonsVerplaatsenTrue();
        btnVerplaatsSpeler.setDisable(true);
    }
    
    
    String spelnaam;
    @FXML
    private void slaSpelOp(MouseEvent event) {
        
        dc.setSpelRepository();
        dc.setSpelerRepository();
        if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep()){
            if(dc.getSpel().getNaam()==null || "".equals(dc.getSpel().getNaam()))
            {
                do{
                    JFrame frame = new JFrame("Spel opslaan");
                    //variabele aantalSpelers bevat het aantal spelers
                    spelnaam = JOptionPane.showInputDialog(frame,"Geef een naam in:",frame.getTitle(), JOptionPane.QUESTION_MESSAGE);
                }
                while((spelnaam == null) == false && dc.controleerSpelNaam(spelnaam)==false);
                if(spelnaam !=null){
                    Task task = new Task<Void>() {
                        @Override public Void call() {
                            dc.berekenSpelerAanDeBeurt();
                            dc.slaSpelOp(spelnaam);
                            return null;
                        }
                    };
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                    int n = JOptionPane.showConfirmDialog(null,dc.getTaal().getString("tekstSpelOpgeslaan"), null,JOptionPane.OK_OPTION);
                    if(n==0){
                        //JOptionPane.showMessageDialog(null, "GOODBYE");
                        Stage stage = (Stage) (this.getScene().getWindow());
                        stage.close();
                    }
                    else 
                    {
                        //JOptionPane.showMessageDialog(null, "GOODBYE");
                        Stage stage = (Stage) (this.getScene().getWindow());
                        stage.close();
                    }
                }
            }
            else 
            {
                Task task = new Task<Void>() {
                        @Override public Void call() {
                            dc.berekenSpelerAanDeBeurt();
                            dc.updateSpel();
                            return null;
                        }
                    };
                    Thread th = new Thread(task);
                    th.setDaemon(true);
                    th.start();
                    int n = JOptionPane.showConfirmDialog(null,dc.getTaal().getString("tekstSpelOpgeslaan"), null,JOptionPane.OK_OPTION);
                    if(n==0){
                        //JOptionPane.showMessageDialog(null, "GOODBYE");
                        Stage stage = (Stage) (this.getScene().getWindow());
                        stage.close();
                    }
                    else 
                    {
                        //JOptionPane.showMessageDialog(null, "GOODBYE");
                        Stage stage = (Stage) (this.getScene().getWindow());
                        stage.close();
                    }
            }
        }
        else JOptionPane.showMessageDialog(null, dc.getTaal().getString("tekstGeenVerbindingMetInternetOpslaan"), null, JOptionPane.ERROR_MESSAGE);
    }
  

    @FXML
    private void eindeBeurt(MouseEvent event) {
        speelBeurt();
    }

    @FXML
    private void verplaatsNaarBoven(MouseEvent event) {
        dc.verplaatsSpeler(1);
        tekenDoolhof();
        if(dc.isSchatGevondenGUI())
        {
            zetButtonsVerplaatsenFalse();
            btnVerplaatsSpeler.setDisable(true);
            if(dc.controleerEindeSpel())
            {
                JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                btnEindeBeurt.setDisable(true);
                btnSlaSpelOp.setDisable(true);
                if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
            }
            else tekenSpelerKaart();
        }
    }

    @FXML
    private void verplaatsNaarLinks(MouseEvent event) {
        dc.verplaatsSpeler(3);
        tekenDoolhof();
        if(dc.isSchatGevondenGUI())
        {
            zetButtonsVerplaatsenFalse();
            btnVerplaatsSpeler.setDisable(true);
            if(dc.controleerEindeSpel())
            {
                JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                btnEindeBeurt.setDisable(true);
                btnSlaSpelOp.setDisable(true);
                if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
            }
            else tekenSpelerKaart();
        }
    }

    @FXML
    private void verplaatsNaarBeneden(MouseEvent event) {
        dc.verplaatsSpeler(2);
        tekenDoolhof();
        if(dc.isSchatGevondenGUI())
        {
            zetButtonsVerplaatsenFalse();
            btnVerplaatsSpeler.setDisable(true);
            if(dc.controleerEindeSpel())
            {
                JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt()+ " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                btnEindeBeurt.setDisable(true);
                btnSlaSpelOp.setDisable(true);
                if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
            }
            else tekenSpelerKaart();
        }
    }

    @FXML
    private void verplaatsNaarRechts(MouseEvent event) {
        dc.verplaatsSpeler(4);
        tekenDoolhof();
        if(dc.isSchatGevondenGUI())
        {
            zetButtonsVerplaatsenFalse();
            btnVerplaatsSpeler.setDisable(true);
            if(dc.controleerEindeSpel())
            {
                JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                btnEindeBeurt.setDisable(true);
                btnSlaSpelOp.setDisable(true);
                if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
            }
            else tekenSpelerKaart();
        }
    }

    @FXML
    private void verplaats(KeyEvent event) {
        if(event.getCode()==KeyCode.LEFT)
        {
            dc.verplaatsSpeler(3);
            tekenDoolhof();
            if(dc.isSchatGevondenGUI())
            {
                zetButtonsVerplaatsenFalse();
                btnVerplaatsSpeler.setDisable(true);
                if(dc.controleerEindeSpel())
                {
                    JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                    btnEindeBeurt.setDisable(true);
                    btnSlaSpelOp.setDisable(true);
                    if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
                }
                else tekenSpelerKaart();
            }
        }
        else if(event.getCode()==KeyCode.RIGHT)
        {
            dc.verplaatsSpeler(4);
            tekenDoolhof();
            if(dc.isSchatGevondenGUI())
            {
                zetButtonsVerplaatsenFalse();
                btnVerplaatsSpeler.setDisable(true);
                if(dc.controleerEindeSpel())
                {
                    JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                    btnEindeBeurt.setDisable(true);
                    btnSlaSpelOp.setDisable(true);
                    if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
                }
                else tekenSpelerKaart();
            }
        }
        else if(event.getCode()==KeyCode.UP)
        {
            dc.verplaatsSpeler(1);
            tekenDoolhof();
            if(dc.isSchatGevondenGUI())
            {
                zetButtonsVerplaatsenFalse();
                btnVerplaatsSpeler.setDisable(true);
                if(dc.controleerEindeSpel())
                {
                    JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                    btnEindeBeurt.setDisable(true);
                    btnSlaSpelOp.setDisable(true);
                    if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
                }
                else tekenSpelerKaart();
            }
        }
        else if(event.getCode()==KeyCode.DOWN)
        {
            dc.verplaatsSpeler(2);
            tekenDoolhof();
            if(dc.isSchatGevondenGUI())
            {
                zetButtonsVerplaatsenFalse();
                btnVerplaatsSpeler.setDisable(true);
                if(dc.controleerEindeSpel())
                {
                    JOptionPane.showMessageDialog(null, dc.getNaamSpelerAanDeBeurt() + " " + dc.getTaal().getString("tekstGewonnen"), null, JOptionPane.INFORMATION_MESSAGE);
                    btnEindeBeurt.setDisable(true);
                    btnSlaSpelOp.setDisable(true);
                    if(dc.getSpel().getNaam()!=null)
                {
                    dc.verwijderSpel();
                }
                }
                else tekenSpelerKaart();
            }
            
        }
    }
}
