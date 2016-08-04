package domein;

import java.awt.Color;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Speler 
{
    //Schatten moeten nog verdeelt worden
    private ArrayList<Schat> schat = new ArrayList<>();
    private String naam;
    private Color kleur;
    private int geboortejaar;
    private int spelerID;
    private int xCoordinaat;
    private int yCoordinaat;
    private boolean aanDeBeurt;

    public int getSpelerID() {
        return spelerID;
    }

    public void setSpelerID(int spelerID) {
        this.spelerID = spelerID;
    }

    public boolean isAanDeBeurt() {
        return aanDeBeurt;
    }

    public void setAanDeBeurt(boolean aanDeBeurt) {
        this.aanDeBeurt = aanDeBeurt;
    }

    
    public int getxCoordinaat() {
        return xCoordinaat;
    }

    public void setxCoordinaat(int xCoordinaat) {
        this.xCoordinaat = xCoordinaat;
    }

    public int getyCoordinaat() {
        return yCoordinaat;
    }

    public void setyCoordinaat(int yCoordinaat) {
        this.yCoordinaat = yCoordinaat;
    }
    
    //Constructor
    
    public Speler(String naam) {
    
        this.naam = naam;
    }

    public Speler(int id, String naam) {
        this.naam=naam;
        this.spelerID= id;
    }
    
    public Speler(String naam, Color kleur, int geboortejaar)
    {

        this.naam=naam;
        this.kleur=kleur;
        this.geboortejaar = geboortejaar;
    }

    //Getters
    public String getNaam() 
    {
        return naam;
    }
    public String getKleurStringGUI(ResourceBundle taal){
    String tekstKleur = "";
    if (kleur == Color.BLUE)
        tekstKleur = taal.getString("tekstKleurBlauw");
    else if (kleur == Color.GREEN)
        tekstKleur = taal.getString("tekstKleurGroen");
    else if (kleur == Color.RED)
        tekstKleur = taal.getString("tekstKleurRood");
    else if (kleur == Color.YELLOW)
        tekstKleur = taal.getString("tekstKleurGeel");
    return tekstKleur;
    }
    public String getKleurString(){
    String tekstKleur = "";
    if (kleur == Color.BLUE)
        tekstKleur = "Blauw";
    else if (kleur == Color.GREEN)
        tekstKleur = "Groen";
    else if (kleur == Color.RED)
        tekstKleur = "Rood";
    else if (kleur == Color.YELLOW)
        tekstKleur = "Geel";
    return tekstKleur;
    }
    public Color getKleur() 
    {
        return kleur;
    }
    
    public int getGeboortejaar() 
    {
        return geboortejaar;
    }
    
    public int getID() 
    {
        return spelerID;
    }
    
    //Setters
    public void setNaam(String naam) 
    {
        this.naam = naam;
    }
    
    public Schat getBovensteSchat()
    {
        return schat.get(0);
    }
    
    public ArrayList<Schat> getSchat() 
    {
        return schat;
    }

    public void setSchat(ArrayList<Schat> schat) 
    {
        this.schat = schat;
    }
    
    public void voegSchatToe(Schat schat)
    {
        this.schat.add(schat);
    }

}