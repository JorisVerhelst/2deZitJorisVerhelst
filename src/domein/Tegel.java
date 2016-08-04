
package domein;

import java.awt.Color;
import java.util.ArrayList;

public class Tegel {
    private Schat schat;
    private ArrayList<Doorgang> openingen;
    private ArrayList<Speler> spelers = new ArrayList<>();
    private Color kleur;

    public Color getKleur() 
    {
        return kleur;
    }

    public void setKleur(Color kleur) 
    {
        this.kleur = kleur;
    }
    
    public Tegel()
    {
        this.openingen = new ArrayList<>();
    }
    public Tegel(ArrayList<Doorgang> openingen)
    {
        this.openingen = openingen;
    }
    
    public Tegel(Tegel tegel){
        this(tegel.getOpeningen());
    }

    public ArrayList<Doorgang> getOpeningen()
    {
        return openingen;
    }

    public void addOpening(Doorgang doorgang)
    {
        openingen.add(doorgang);
    }
    public void setSchat(Schat schat)
    {
        this.schat=schat;
    }
    public Schat getSchat()
    {
        return schat;
    }
    
    //methodes in verband met de spelers
    public ArrayList<Speler> getSpelers() 
    {
        return spelers;
    }
    public void addSpeler(Speler speler)
    {
        spelers.add(speler);
    }
    public void removeSpeler(Speler speler)
    {
        spelers.remove(speler);
    }
    
    // Draait de tegel een aantal slagen door de doorgangen te wijzigen
    public void draaiTegel(int aantalSlagen) {
        //int slagen = (aantalSlagen % 4);
        ArrayList<Doorgang> proxy = new ArrayList<>();
        switch (aantalSlagen){
                case 0: break;
                case 1: if (this.openingen.contains(Doorgang.N)){
                            proxy.add(Doorgang.O);
                        }
                        if (this.openingen.contains(Doorgang.O)){
                            proxy.add(Doorgang.Z);
                        }
                        if (this.openingen.contains(Doorgang.Z)){
                            proxy.add(Doorgang.W);
                        }
                        if (this.openingen.contains(Doorgang.W)){
                            proxy.add(Doorgang.N);
                        }
                        this.openingen.clear();
                        this.openingen = proxy;
                break;
                case 3: if (this.openingen.contains(Doorgang.N)){
                            proxy.add(Doorgang.Z);
                        }
                        if (this.openingen.contains(Doorgang.O)){
                            proxy.add(Doorgang.W);
                        }
                        if (this.openingen.contains(Doorgang.Z)){
                            proxy.add(Doorgang.N);
                        }
                        if (this.openingen.contains(Doorgang.W)){
                            proxy.add(Doorgang.O);
                        }
                        this.openingen.clear();
                        this.openingen = proxy;
                break;
                case 2: if (this.openingen.contains(Doorgang.N)){
                            proxy.add(Doorgang.W);
                        }
                        if (this.openingen.contains(Doorgang.O)){
                            proxy.add(Doorgang.N);
                        }
                        if (this.openingen.contains(Doorgang.Z)){
                            proxy.add(Doorgang.O);
                        }
                        if (this.openingen.contains(Doorgang.W)){
                            proxy.add(Doorgang.Z);
                        }
                        this.openingen.clear();
                        this.openingen = proxy;
                break;
        }
    }
    
    @Override
    public String toString()
    {
        String tekst="";
        for (Doorgang openingen1 : openingen) {
            tekst=tekst.concat(String.format(" %s",openingen1));
        }
        if(schat!=null)
            tekst=tekst.concat(String.format(" -  %s", schat));
        return tekst;
    }
    
}
