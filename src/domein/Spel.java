package domein;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javax.swing.JOptionPane;
 
 
public class Spel
{
    private List<Speler> spelers = new ArrayList<>();
    private Speler spelerAanDeBeurt;
    private String spelNaam;
    private int spelID;
    private Doolhof doolhof;
    private DomeinController dc;
    private ResourceBundle taal;
    private int vorigeX;
    private int vorigeY;
    private boolean isSpelOpgeslaan = false;
 
    public Spel(String spelNaam)
    {
        this.spelNaam=spelNaam;
    }
   
    public Spel(Doolhof doolhof, DomeinController dC, ResourceBundle taal)
    {
        this.spelers.clear();
        this.doolhof=doolhof;
        this.dc = dC;
        this.taal = taal;
    }
   
    public Spel(int id, String spelNaam)
    {
        this.spelNaam=spelNaam;
        this.spelID=id;
    }
 
    public Doolhof getDoolhof()
    {
        return doolhof;
    }
    public void setDoolhof(Doolhof doolhof)
    {
        this.doolhof = doolhof;
    }
       
    public void setSpelers(List<Speler> spelers)
    {
        spelers.stream().forEach((speler) -> {
            this.spelers.add(speler);
            });
    }
   
    public void addSpeler(String naam, Color kleur, int geboortejaar)
    {
        this.spelers.add(new Speler(naam, kleur, geboortejaar));
    }
   
    public void voegSpelersAanDeDoolhof()
    {
        doolhof.voegSpelersAanDeDoolhof(this.spelers);
    }
       
        // Geeft een lijst terug met de spelers die het spel spelen
    public List<Speler> geefLijstSpelers()
    {
        return this.spelers;
    }
    
    public void setSpelerAanDeBeurt(Speler spelerAanDeBeurt)
    {
        this.spelerAanDeBeurt = spelerAanDeBeurt;
    }
    
    public Speler getSpelerAanDeBeurt()
    {
        return spelerAanDeBeurt;
    }
    public String getNaamSpelerAanDeBeurt()
    {
        return spelerAanDeBeurt.getNaam();
    }
    public String getGeboorteJaarSpelerAanDeBeurt()
    {
        return spelerAanDeBeurt.getGeboortejaar()+"";
    }
    public String getKleurSpelerAanDeBeurt(ResourceBundle taal)
    {
        return spelerAanDeBeurt.getKleurStringGUI(taal);
    }
    public String getNaam()
    {
        return spelNaam;
    }
    public void setNaam(String naam)
    {
        spelNaam = naam;
    }   
    public int getID()
    {
        return spelID;
    }
    
    public String berekenSpelerAanDeBeurt()
    {
        String tekst="";
        if(spelerAanDeBeurt == null)
        {
            for(int i=0;i<spelers.size();i++)
            {
                if(spelers.get(i).isAanDeBeurt()==true)
                    spelerAanDeBeurt = spelers.get(i);
            }
            if(spelerAanDeBeurt == null)
            {
                spelerAanDeBeurt = spelers.get(0);
                spelers.get(0).setAanDeBeurt(true);
            }
            tekst = spelerAanDeBeurt.getNaam() +","+ dc.getTaal().getString("tekstJeBentAanDeBeurt") +"[" +  spelerAanDeBeurt.getxCoordinaat() + "," + spelerAanDeBeurt.getyCoordinaat() + "]";
        }
        else
        {
            tekst = spelerAanDeBeurt.getNaam() + ","+ dc.getTaal().getString("jeHebtJeBeurtBeeindigd") + "\n" + dc.getTaal().getString("tekstDeVolgendeSpelerIs");
            if(spelers.indexOf(spelerAanDeBeurt)<spelers.size()-1)
            {
                spelers.get(spelers.indexOf(spelerAanDeBeurt)).setAanDeBeurt(false);
                spelerAanDeBeurt = spelers.get(spelers.indexOf(spelerAanDeBeurt)+1);
                spelers.get(spelers.indexOf(spelerAanDeBeurt)).setAanDeBeurt(true);
            }
            else {
                spelers.get(spelers.indexOf(spelerAanDeBeurt)).setAanDeBeurt(false);
                spelerAanDeBeurt = spelers.get(0);
                spelers.get(0).setAanDeBeurt(true);
            }
            tekst+=spelerAanDeBeurt.getNaam()+"\n";
            tekst+=spelerAanDeBeurt.getNaam() + ","+dc.getTaal().getString("tekstJeBevindtOpCoordinaat")+ "[" +  spelerAanDeBeurt.getxCoordinaat() + "," + spelerAanDeBeurt.getyCoordinaat() + "]";
        }
        
        return tekst;
    }
       
    public List<Speler> berekenVolgordeSpelers(List<Speler> spelers)
    {
        List<Speler> volgordeSpelers = new ArrayList<>();
        int positieEersteSpeler = 0;
        Speler jongsteSpeler = spelers.get(0);
        //we bepalen eerst de eerste speler + zijn plaats in de lijst
        for(int teller=1;teller<spelers.size();teller++)
        {
            if(spelers.get(teller).getGeboortejaar()>jongsteSpeler.getGeboortejaar())
            {
                jongsteSpeler=spelers.get(teller);
                positieEersteSpeler= teller;
            }
            else if(spelers.get(teller).getGeboortejaar() == jongsteSpeler.getGeboortejaar())
            {
                if(spelers.get(teller).getNaam().toUpperCase().compareTo(jongsteSpeler.getNaam().toUpperCase())<0)
                {
                    jongsteSpeler=spelers.get(teller);
                    positieEersteSpeler= teller;
                }
            }    
        }
        //nu voegen we de spelers in de volgorde waarin zij het spel zullen spelen...de jongste speler staat bovenaan
        volgordeSpelers.add(jongsteSpeler);
        for(int teller=positieEersteSpeler+1;teller<spelers.size();teller++)
        {
            volgordeSpelers.add(spelers.get(teller));
        }
        for(int teller=0;teller<positieEersteSpeler;teller++)
        {
            volgordeSpelers.add(spelers.get(teller));
        }
        return volgordeSpelers;
    }
   
    public String toonTekstSpelers(ResourceBundle taal)
    {
        String volledigeTekst="";
        String tekst=(char)27 +"";
        for (Speler huidigeSpeler : this.spelers)
        {
            if(huidigeSpeler.getKleur()==Color.BLUE)
                tekst += "[34m";
            else if(huidigeSpeler.getKleur()==Color.RED)
                tekst += "[31m";
            else if(huidigeSpeler.getKleur()==Color.GREEN)
                tekst += "[32m";
            else if(huidigeSpeler.getKleur()==Color.YELLOW)
                tekst += "[33m";
            tekst+=(String.format("%s %s [%d][%d]%n",huidigeSpeler.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),huidigeSpeler.getxCoordinaat(),huidigeSpeler.getyCoordinaat()));
            tekst+= (char)27+"";
        }
        return tekst;
    }
    
    public String toonTekstSpelersGUI(ResourceBundle taal)
    {   
        String tekst="";
        tekst +=String.format("%s%n", dc.getTaal().getString("tekstVolgordeSpelers"));
        for (Speler geefLijstSpeler : spelers) 
        {
            tekst += String.format("%s%n",geefLijstSpeler.getNaam());
        }
        tekst+= String.format("%n");
        
        for (Speler huidigeSpeler : this.spelers)
        {
            tekst+=(String.format("%s %s [%d][%d]%n",huidigeSpeler.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),huidigeSpeler.getxCoordinaat(),huidigeSpeler.getyCoordinaat()));
        }
        tekst+=String.format("%s%n",dc.getTaal().getString("tekstWilUHetSpelSpelen"));
        return tekst;
    }
    
    public void verdeelSchatten()
    {
        ArrayList<Schat> schatten = new ArrayList<>();
        schatten.addAll(Arrays.asList(Schat.values()));
        Random rand = new Random();
        int getal;
        while(schatten.size()>0)
        {
            for (Speler huidigeSpeler : this.spelers)
            {
                boolean controle = true;
                do{
                    getal = rand.nextInt(schatten.size());
                    if(schatten.get(getal)!=null)
                    {
                        huidigeSpeler.voegSchatToe(schatten.get(getal));
                        schatten.remove(getal);
                        controle=false;
                    }
                }while(controle);  
            }
        }
    }
    
    public String speelSpelGUI()
    {
        return berekenSpelerAanDeBeurt();
    }
    
    public void speelSpel()
    {
        vorigeX=0;
        vorigeY=0;
        Speler huidigeSpeler;
        berekenSpelerAanDeBeurt();
        do{
            huidigeSpeler = getSpelerAanDeBeurt();
            speelBeurt(huidigeSpeler);
            huidigeSpeler.setAanDeBeurt(false);
            dc.clearConsole();
        }while(isSpelOpgeslaan == false && controleerEindeSpel()==false);
        if(isSpelOpgeslaan==false) {
            dc.toonTekstOpHetScherm(String.format("%s"+dc.getTaal().getString("tekstGewonnen"), huidigeSpeler.getNaam()));
            if(getNaam()!=null)
            {
                dc.verwijderSpel();
            }
        }
        else dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstSpelOpgeslaan"));
    }
    
    private void speelBeurt(Speler spelerAanBeurt)
    {
        int aantalslagen=0;
        int aantalInschuiven=0;
        int x,y;
        do{
            //we vragen de coordinaten waar de tegel ingeschoven moet worden
            do{
                dc.clearConsole();
                dc.toonTekstOpHetScherm(doolhof.toString());
                dc.toonTekstOpHetScherm(doolhof.tekenZweevendeTegel());
                dc.toonTekstOpHetScherm(spelerAanBeurt.getNaam() + " "+ dc.getTaal().getString("tekstDeVolgendeSpelerIsOmgekeerd"));
                dc.toonTekstOpHetScherm(String.format("%s %s [%d][%d]",spelerAanBeurt.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),spelerAanBeurt.getxCoordinaat(), spelerAanBeurt.getyCoordinaat()));
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstDeTeZoeken") + spelerAanBeurt.getBovensteSchat());
                dc.toonTekstOpHetScherm("");
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstCoordinatenInschuiven"));
                x = dc.vraagXCoordinaat();
                y = dc.vraagYCoordinaat();
                if(controleerVrijeGangkaart(x, y)==false) {
                    dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstUGeenTegel"));
                    try{
                        Thread.sleep(3000);
                    }catch(Exception ex){}
                }
                else if(x==vorigeX && y==vorigeY) {
                    dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstGekozenRichting"));
                    try{
                        Thread.sleep(3000);
                    }catch(Exception ex){}
                }
            }while(controleerVrijeGangkaart(x,y)==false || (x==vorigeX && y==vorigeY));
          
            //we gaan nu vragen hoe de tegel gedraaid moet worden
            do{
                dc.clearConsole();
                dc.toonTekstOpHetScherm(doolhof.toString());
                dc.toonTekstOpHetScherm(doolhof.tekenZweevendeTegel());
                dc.toonTekstOpHetScherm(spelerAanBeurt.getNaam() + " "+ dc.getTaal().getString("tekstIsAanDeBeurt"));
                dc.toonTekstOpHetScherm(String.format("%s %s [%d][%d]",spelerAanBeurt.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),spelerAanBeurt.getxCoordinaat(), spelerAanBeurt.getyCoordinaat()));
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstDeTeZoeken")+" " + spelerAanBeurt.getBovensteSchat());
                dc.toonTekstOpHetScherm("");
                aantalslagen = dc.vraagAantalSlagen();
                doolhof.draaiTegel(aantalslagen);
            }while(aantalslagen!=0);
           
            // we gaan nu de tegel inschuiven
            if(dc.vraagBevestigingTegelInschuiven(x, y)==1)
            {
                voerTegelIn(x, y);
                aantalInschuiven++;
                dc.clearConsole();
                dc.toonTekstOpHetScherm(doolhof.toString());
                dc.toonTekstOpHetScherm(doolhof.tekenZweevendeTegel());
                dc.toonTekstOpHetScherm(spelerAanBeurt.getNaam() + " "+ dc.getTaal().getString("tekstIsAanDeBeurt") );
                dc.toonTekstOpHetScherm(String.format("%s %s [%d][%d]",spelerAanBeurt.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),spelerAanBeurt.getxCoordinaat(), spelerAanBeurt.getyCoordinaat()));
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstDeTeZoeken")+" " + spelerAanBeurt.getBovensteSchat());
                dc.toonTekstOpHetScherm("");
            }
            else {
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstInschuivenBevestigd"));
                try {
                    Thread.sleep(3000);
                } catch (Exception ex) {}
            }
        }while(aantalInschuiven == 0);
       
        if(dc.vraagSpelerVerplaatsen()==1)
        {
            int richting = 0;
            do
            {
                dc.clearConsole();
                dc.toonTekstOpHetScherm(doolhof.toString());
                dc.toonTekstOpHetScherm(doolhof.tekenZweevendeTegel());
                dc.toonTekstOpHetScherm(spelerAanBeurt.getNaam() + " "+dc.getTaal().getString("tekstIsAanDeBeurt") );
                dc.toonTekstOpHetScherm(String.format("%s %s [%d][%d]",spelerAanBeurt.getNaam(),dc.getTaal().getString("tekstToonKleurEnPositieSpeler2"),spelerAanBeurt.getxCoordinaat(), spelerAanBeurt.getyCoordinaat()));
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstDeTeZoeken")+" " + spelerAanBeurt.getBovensteSchat());
                dc.toonTekstOpHetScherm("");
                richting = dc.vraagTeVerplaatsenRichting();
                verplaatsSpeler(richting);
            }while(richting!=0 && isSchatGevonden(spelerAanBeurt)==false);
        }
        if(dc.vraagSpelerOpslaan()==1)
        {
            berekenSpelerAanDeBeurt();
            if(spelNaam!=null && !"".equals(spelNaam)){
                dc.setSpelRepository();
                dc.setSpelerRepository();
                if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep())
                {
                    dc.updateSpel();
                    dc.sluitVerbindingMetDatabank();
                    isSpelOpgeslaan=true;
                }
                else{
                    dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstGeenVerbindingMetInternetOpslaan"));
                    berekenSpelerAanDeBeurt();
                    try{
                        Thread.sleep(3000);
                    }catch(Exception ex){

                    }
                }
            } else {
                String spelnaam=dc.vraagSpelnaam();
                dc.setSpelRepository();
                dc.setSpelerRepository();
                if(dc.maakVerbindingMetSpelRep() && dc.maakVerbindingMetSpelerRep())
                {
                    dc.slaSpelOp(spelnaam);
                    dc.sluitVerbindingMetDatabank();
                    isSpelOpgeslaan=true;
                }
                else{
                    dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstGeenVerbindingMetInternetOpslaan"));
                    berekenSpelerAanDeBeurt();
                    try{
                        Thread.sleep(3000);
                    }catch(Exception ex){

                    }
                }
            }
        }
        else berekenSpelerAanDeBeurt();
   }
    
    public void verplaatsSpeler(int richting)
    {
        switch (richting)
        {
            case 1:
                if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getSpelers().contains(spelerAanDeBeurt))
                {
                    if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.N) && spelerAanDeBeurt.getxCoordinaat()>0)
                        {
                            if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()-1][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.Z))
                            {
                                doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()).removeSpeler(spelerAanDeBeurt);
                                doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat()-1, spelerAanDeBeurt.getyCoordinaat()).addSpeler(spelerAanDeBeurt);
                                spelerAanDeBeurt.setxCoordinaat(spelerAanDeBeurt.getxCoordinaat()-1);
                            }
                        }
                }
                break;
            case 2:
                if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getSpelers().contains(spelerAanDeBeurt))
                {
                    if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.Z) && spelerAanDeBeurt.getxCoordinaat()<6)
                    {
                        if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()+1][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.N))
                        {
                        doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()).removeSpeler(spelerAanDeBeurt);
                        doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat()+1, spelerAanDeBeurt.getyCoordinaat()).addSpeler(spelerAanDeBeurt);
                        spelerAanDeBeurt.setxCoordinaat(spelerAanDeBeurt.getxCoordinaat()+1);
                        }
                    }
                }
                break;
            case 3:
                if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getSpelers().contains(spelerAanDeBeurt))
                {
                    if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.W) && spelerAanDeBeurt.getyCoordinaat()>0)
                    {
                        if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()-1].getOpeningen().contains(Doorgang.O))
                        {
                            doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()).removeSpeler(spelerAanDeBeurt);
                            doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()-1).addSpeler(spelerAanDeBeurt);
                            spelerAanDeBeurt.setyCoordinaat(spelerAanDeBeurt.getyCoordinaat()-1);
                        }
                    }
                }
                break;
            case 4:
                if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getSpelers().contains(spelerAanDeBeurt))
                {
                    if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()].getOpeningen().contains(Doorgang.O) && spelerAanDeBeurt.getyCoordinaat()<6)
                    {
                        if(doolhof.getTegels()[spelerAanDeBeurt.getxCoordinaat()][spelerAanDeBeurt.getyCoordinaat()+1].getOpeningen().contains(Doorgang.W))
                        {
                            doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()).removeSpeler(spelerAanDeBeurt);
                            doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()+1).addSpeler(spelerAanDeBeurt);
                            spelerAanDeBeurt.setyCoordinaat(spelerAanDeBeurt.getyCoordinaat()+1);
                        }
                    }
                }
                break;
        }
    }
    
    private boolean isSchatGevonden(Speler spelerAanBeurt)
    {
        boolean schatGevonden = false;
        if(doolhof.getTegel(spelerAanBeurt.getxCoordinaat(), spelerAanBeurt.getyCoordinaat()).getSchat() == spelerAanBeurt.getBovensteSchat())
        {
            schatGevonden = true;
            dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstUHebtGevonden")+spelerAanBeurt.getBovensteSchat() + dc.getTaal().getString("tekstGevonden"));
            spelerAanBeurt.getSchat().remove(0);
            if(spelerAanBeurt.getSchat().size()>0)
                dc.toonTekstOpHetScherm(dc.getTaal().getString("tekstDeVolgendeSchat")+" "+spelerAanBeurt.getBovensteSchat());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
            }

        }
        return schatGevonden;
    }
    
    public boolean isSchatGevondenGUI()
    {
        boolean schatGevonden = false;
        String tekst="";
        if(doolhof.getTegel(spelerAanDeBeurt.getxCoordinaat(), spelerAanDeBeurt.getyCoordinaat()).getSchat() == spelerAanDeBeurt.getBovensteSchat())
        {
            schatGevonden = true;
            tekst =dc.getTaal().getString("tekstUHebtGevonden")+ spelerAanDeBeurt.getBovensteSchat() + dc.getTaal().getString("tekstGevonden") + "\n";
            spelerAanDeBeurt.getSchat().remove(0);
            if(spelerAanDeBeurt.getSchat().size()>0)
                tekst+= dc.getTaal().getString("tekstDeVolgendeSchat")+" " + spelerAanDeBeurt.getSchat().get(0);
            JOptionPane.showMessageDialog(null, tekst, null , JOptionPane.INFORMATION_MESSAGE);
        }
        return schatGevonden;
    }
    
    // Controleert of het een van de 12 geldige manieren is om een tegel in te schuiven
    private boolean controleerVrijeGangkaart(int x, int y){
        boolean controle = false;
        switch (x) {
            case 0: if (y == 1 || y == 3 || y == 5) {
                        controle = true;
                    }
                    break;
            case 1: if (y == 0 || y == 6) {
                        controle = true;
                    }
                    break;
            case 3: if (y == 0 || y == 6) {
                        controle = true;
                    }
                    break;
            case 5: if (y == 0 || y == 6) {
                        controle = true;
                    }
                    break;
            case 6: if (y == 1 || y == 3 || y == 5) {
                        controle = true;
                    }
                    break;
            default: controle = false;
                    break;
        }
        return controle;
    }
   
    private boolean horizontaal(int x) {
        //Kijkt of de tegel horizontaal wordt ingeschoven
        return (x == 0 || x == 6);
    }
   
    private boolean trekken (int x, int y) {
       
        //Kijkt of de tegels naar onder getrokken of naar boven geduwd worden
        return (x == 6 || y == 6);
    }
   
    public boolean voerTegelIn(int x, int y) {
        if(x==vorigeX && y==vorigeY){ return false;}
        else
        {
            //we gaan de coordinaten bijhouden
            if(x==0) vorigeX=6;
            else if(x==6) vorigeX=0;
            else vorigeX=x;

            if(y==0) vorigeY=6;
            else if(y==6) vorigeY=0;
            else vorigeY=y;

            if(controleerVrijeGangkaart(x, y)==true)
            {
                Tegel proxy = doolhof.getZwevendeTegel();
                boolean horizontaal = horizontaal(x);
                boolean trekken = trekken(x, y);
                if (horizontaal){
                    if (trekken){
                        doolhof.setZwevendeTegel(doolhof.getTegel(0, y));
                        for (int i = 0; i < 6; i++){
                            doolhof.setTegel(i, y, doolhof.getTegel(i+1, y));
                            //als er spelers op de tegel bevinden die worden meegeschoven
                            if(doolhof.getTegel(i+1, y).getSpelers().size()>0)
                            {
                                for(int teller = 0; teller < doolhof.getTegel(i+1, y).getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(i+1, y).getSpelers().get(teller).setxCoordinaat(i);
                                }
                            }

                        }
                        doolhof.setTegel(6, y, proxy);
                        //de spelers mogen niet op de losse tegel schuiven
                        if(doolhof.getZwevendeTegel().getSpelers().size()>0)
                        {
                            for(int teller = 0; teller < doolhof.getZwevendeTegel().getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(6, y).addSpeler(doolhof.getZwevendeTegel().getSpelers().get(teller));
                                    doolhof.getTegel(6, y).getSpelers().get(teller).setxCoordinaat(6);
                                    doolhof.getTegel(6, y).getSpelers().get(teller).setyCoordinaat(y);
                                    doolhof.getZwevendeTegel().getSpelers().remove(teller);
                                }
                        }
                    } else {
                        doolhof.setZwevendeTegel(doolhof.getTegel(6, y));
                        for (int i = 6; i > 0; i--){
                            doolhof.setTegel(i, y, doolhof.getTegel(i-1, y));
                            //als er spelers op de tegel bevinden die worden meegeschoven
                            if(doolhof.getTegel(i-1, y).getSpelers().size()>0)
                            {
                                for(int teller = 0; teller < doolhof.getTegel(i-1, y).getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(i, y).getSpelers().get(teller).setxCoordinaat(i);
                                }
                            }

                        }
                        doolhof.setTegel(0, y, proxy);
                        //de spelers mogen noet op de losse tegel schuiven
                        if(doolhof.getZwevendeTegel().getSpelers().size()>0)
                        {
                            for(int teller = 0; teller < doolhof.getZwevendeTegel().getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(0, y).addSpeler(doolhof.getZwevendeTegel().getSpelers().get(teller));
                                    doolhof.getTegel(0, y).getSpelers().get(teller).setxCoordinaat(0);
                                    doolhof.getTegel(0, y).getSpelers().get(teller).setyCoordinaat(y);
                                    doolhof.getZwevendeTegel().getSpelers().remove(teller);
                                }
                        }
                    }
                } else {
                    if (trekken){
                        doolhof.setZwevendeTegel(doolhof.getTegel(x, 0));
                        for (int i = 0; i < 6; i++){
                            //als er spelers op de tegel bevinden die worden meegeschoven
                            doolhof.setTegel(x, i, doolhof.getTegel(x, i+1));
                            if(doolhof.getTegel(x, i+1).getSpelers().size()>0)
                            {
                                for(int teller = 0; teller < doolhof.getTegel(x, i+1).getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(x, i).getSpelers().get(teller).setyCoordinaat(i);
                                }
                            }

                        }
                        doolhof.setTegel(x, 6, proxy);
                        //de spelers mogen noet op de losse tegel schuiven
                        if(doolhof.getZwevendeTegel().getSpelers().size()>0)
                        {
                            for(int teller = 0; teller < doolhof.getZwevendeTegel().getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(x, 6).addSpeler(doolhof.getZwevendeTegel().getSpelers().get(teller));
                                    doolhof.getTegel(x, 6).getSpelers().get(teller).setxCoordinaat(x);
                                    doolhof.getTegel(x, 6).getSpelers().get(teller).setyCoordinaat(6);
                                    doolhof.getZwevendeTegel().getSpelers().remove(teller);
                                }
                        }
                    } else {
                        doolhof.setZwevendeTegel(doolhof.getTegel(x, 6));
                        for (int i = 6; i > 0; i--){

                            doolhof.setTegel(x, i, doolhof.getTegel(x, i-1));

                            if(doolhof.getTegel(x, i-1).getSpelers().size()>0)
                            {
                                for(int teller = 0; teller < doolhof.getTegel(x, i-1).getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(x, i).getSpelers().get(teller).setyCoordinaat(i);
                                }
                            }
                        }
                        doolhof.setTegel(x, 0, proxy);
                        //de spelers mogen noet op de losse tegel schuiven
                        if(doolhof.getZwevendeTegel().getSpelers().size()>0)
                        {
                            for(int teller = 0; teller < doolhof.getZwevendeTegel().getSpelers().size() ; teller++)
                                {
                                    doolhof.getTegel(x, 0).addSpeler(doolhof.getZwevendeTegel().getSpelers().get(teller));
                                    doolhof.getTegel(x, 0).getSpelers().get(teller).setxCoordinaat(x);
                                    doolhof.getTegel(x, 0).getSpelers().get(teller).setyCoordinaat(0);
                                    doolhof.getZwevendeTegel().getSpelers().remove(teller);
                                }
                        }
                    }
                }
                return true;
            }
            else return false;
        }
    }
   
    public boolean controleerEindeSpel() {
        return spelerAanDeBeurt.getSchat().isEmpty();
    }
   
    public String tekenZweevendeTegel()
    {
        return doolhof.tekenZweevendeTegel();
    }
    
    public void tekenDoolhofGUI(GridPane gridpane, ImageView imgV)
    {
        doolhof.tekenDoolhofGUI(gridpane, imgV);
    }
    public void tekenZwevendeTegelGUI(GridPane gridpane, ImageView imgV)
    {
        doolhof.tekenZwevendeTegelGUI(gridpane, imgV);    
    }
    public void tekenPionSpeler(GridPane gridpane, ImageView imgV)
    {
        Image img;
        if(spelerAanDeBeurt.getKleur()==Color.BLUE) img = new Image(getClass().getResourceAsStream("/images/Pion_Blauw.PNG"),50,50,true,true);
        else if(spelerAanDeBeurt.getKleur()==Color.RED) img = new Image(getClass().getResourceAsStream("/images/Pion_Rood.PNG"),50,50,true,true);
        else if(spelerAanDeBeurt.getKleur()==Color.YELLOW) img = new Image(getClass().getResourceAsStream("/images/Pion_Geel.PNG"),50,50,true,true);
        else img = new Image(getClass().getResourceAsStream("/images/Pion_Groen.PNG"),50,50,true,true);
        imgV = new ImageView(img);
        gridpane.add(imgV, 0, 0);
    }
    
    public void tekenSpelerKaart(ImageView imgVVoorkantSpelerKaart, ImageView imgVAchterkantSpelerKaart, StackPane stackPaneSpelerKaart) 
    {
        if(spelerAanDeBeurt.getSchat()!=null)
        {
            imgVVoorkantSpelerKaart = new ImageView(new Image(getClass().getResourceAsStream("/images/kaart"+spelerAanDeBeurt.getBovensteSchat()+".png"),100,175,true,true));
            imgVAchterkantSpelerKaart = new ImageView(new Image(getClass().getResourceAsStream("/images/backkaart.png"),100,175,true,true));
            stackPaneSpelerKaart.getChildren().addAll(imgVVoorkantSpelerKaart,imgVAchterkantSpelerKaart);
        }
    }
    
    public void draaiTegel(int aantalSlagen) {
         doolhof.draaiTegel(aantalSlagen);
     }

    void setDomeinController(DomeinController dc) {
        this.dc = dc;
    }
    
}