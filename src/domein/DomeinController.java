package domein;

import cui.ConsoleApplicatie;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class DomeinController {
    
    private ResourceBundle taal = java.util.ResourceBundle.getBundle("taal/Bundle");
    public List<Speler> spelers = new ArrayList<>();
    private Spel spel;
    private SpelRepository spelRepository;
    private SpelerRepository spelerRepository;
    private ConsoleApplicatie cA;
    List kleurOpties = new ArrayList();
    List kleurArray = vulKleurArray();
    //Constructor
    public DomeinController() 
    {
        
    }
    
    public void setConsoleApplicatie(ConsoleApplicatie cA){
        this.cA = cA;
    }
    
    //Methode in verband met de taal
    public ResourceBundle getTaal(){
        return taal;
    }
    public void setTaal(String taal){
        if(null != taal.toUpperCase())
            switch (taal.toUpperCase()) 
            {
                case "EN":
                    this.taal= java.util.ResourceBundle.getBundle("taal/Bundle_en_US");
                    break;
                case "FR":
                    this.taal= java.util.ResourceBundle.getBundle("taal/Bundle_fr_FR");
                    break;
                case "NL":
                    this.taal= java.util.ResourceBundle.getBundle("taal/Bundle_nl_BE");
                    break;
                }
	} 
    public void kiesTaal(){
        String keuzeTaal="";
        do{
            keuzeTaal=cA.geefKeuzeTaal(this.taal);
        }while(controleerTaal(keuzeTaal)==false);
        setTaal(keuzeTaal);
        cA.toonTekstOpHetScherm(taal.getString("tekstKiesTaalOK"));
        cA.toonTekstOpHetScherm("");
    }
    public boolean controleerTaal(String taal){
        return "EN".equals(taal.toUpperCase()) || "FR".equals(taal.toUpperCase()) || "NL".equals(taal.toUpperCase()) ;
    }
    
    
    public int stringNaarInt(String tekst){
        int number = -1;
        try {
            number = Integer.parseInt(tekst);
        } catch (Exception e) {
            //throw e;
        }
        return number;
    }
    
    // methoden om te vragen of er nieuw spel gemaakt wordt of er een bestaand spel geladen wordt
    public void laadSpelOfMaakSpel(){
        int keuze=0;
        //String strKeuze="";
        boolean controle = true;
        do
        {
            try {
                 //strKeuze = cA.geefKeuzeSpelLadenOfMaken();
                 keuze = stringNaarInt(cA.geefKeuzeSpelLadenOfMaken());
                 toonTekstOpHetScherm("");
                 if(keuze>0 && keuze<3) controle=false;
                 else controle = true;
                 
            }catch (Exception e) {
                controle = true;
                String s = (char)27 + "[31m";
                System.out.println(s + taal.getString("tekstControleAantal"));
                System.out.println((char)27 + "[30m");
            }
        }while(controle);
        
        switch(keuze){
            case 1:
                clearConsole();
                vraagGegevensSpelers();
                maakNieuwSpel(spelers);
                String strKeuzeSpelSpelen="";
                do{
                    strKeuzeSpelSpelen = cA.geefKeuzeSpelSpelen();
                }while(controleerKeuzeSpelSpelen(strKeuzeSpelSpelen));
                
                if(stringNaarInt(strKeuzeSpelSpelen)==1){
                    spel.speelSpel();
                }
                break;
            case 2:
                setSpelRepository();
                setSpelerRepository();
                if(maakVerbindingMetSpelRep() && maakVerbindingMetSpelerRep()){
                String idSpel="";
                do{
                    clearConsole();
                    toonIdEnNamenSpellen();
                    idSpel = cA.vraagIdSpel();
                }while(controlleerIdSpel(idSpel)==false);
                String spelnaam = spelRepository.geefNaamBestaandSpel(stringNaarInt(idSpel));
                laadSpel(spelnaam);
                speelSpel();
                sluitVerbindingMetDatabank();
                }
                else {
                    toonTekstOpHetScherm(taal.getString("tekstGeenVerbindingMetInternetOphalen"));
                    try{
                        Thread.sleep(2000);
                    }catch(Exception ex){
                        
                    }
                    laadSpelOfMaakSpel();
                }
        }
    }
    
    public void voegSpelerToe(String naam, int jaar, int kleur)
    {
        spelers.add(new Speler(naam, (Color) kleurArray.get(kleur), jaar));
        kleurArray.remove(kleur);
    }
    
    //controle methodes
    public boolean controleerAantal(int aantal){
        if(aantal>1 && aantal<5) return true;
        else return false;
    }
    public boolean controleerNaamSpeler(String naam){
        boolean isOk = true;
        if(naam.length()>=2)
        {
            for(int c=32; c<=64; c++)
            {
                if(naam.contains(Character.toString((char) c)))
                    isOk = false;
            }
            if(isOk == true)
            {
                for(int c=91; c<=96; c++)
                {
                    if(naam.contains(Character.toString((char) c)))
                        isOk = false;
                }
            }
            if(isOk == true)
            {
                for(int c=123; c<=126; c++)
                {
                    if(naam.contains(Character.toString((char) c)))
                        isOk = false;
                }
            }
            //We gaan nu controleren als de naam uniek is tussen de spelers
            if(isOk == true)
            {
                for (Speler geefLijstSpeler : spelers) 
                {
                    if(naam.toUpperCase().equals(geefLijstSpeler.getNaam().toUpperCase()))
                        isOk = false;
                }
            }
        }
        else isOk = false;
        return isOk;
    }
    public boolean controleerGeboortejaar(int geboortejaar){
        
        int huidigeJaar = Calendar.getInstance().get(Calendar.YEAR);
        if(huidigeJaar - geboortejaar >90) return false;
        else if(huidigeJaar<geboortejaar) return false;
        else return true;
    }
    
    public boolean controleerKeuzeSpelSpelen(String strKeuze){
        boolean controle = false;
        try {
                 int intKeuze = stringNaarInt(strKeuze);
                 if(intKeuze ==0 || intKeuze==1 ) controle=false;
                 else {
                     controle = true;
                     System.out.println((char)27 + "[31m"+taal.getString("tekstGelieve1of0")+ (char)27 + "[30m");
                 }
                 
            }catch (Exception e) {
                controle = true;
            }
        return controle;
    }
   
    
    
    
            
    
    
    private void vraagGegevensSpelers(){
        boolean controleAantal = true;
        int aantalSpelers = 0;
        kleurOpties= vuloptieArray();
        kleurArray = vulKleurArray();
        do
        {
            try {
                aantalSpelers=stringNaarInt(cA.vraagAantalSpelers());
                toonTekstOpHetScherm("");
                if(controleerAantal(aantalSpelers)) controleAantal=false;
                else
                {
                    controleAantal=true;
                    String s = (char)27 + "[31m";
                    System.out.printf("%s%s%n",s,taal.getString("tekstVerkeerdAantal"));
                    System.out.println((char)27 + "[30m");
                }
            } catch (Exception e) {
                controleAantal=true;
                String s = (char)27 + "[31m";
                System.out.printf("%s%s%n",s, taal.getString("tekstControleAantal"));
                System.out.println((char)27 + "[30m");
            }
        }while(controleAantal);
        
        int geboortejaar = 0;
        String spelerNaam="";
        Color kleur = Color.BLACK;
        boolean controleKleur = true;
        boolean controleGeboortejaar = true;
        boolean controleNaam= true;
        
        
        for(int teller=0;teller<aantalSpelers;teller++)
        {
            kleur = Color.BLACK;
            
            //naam speler
            String naam="";
            do
            {
                controleNaam = true;
                naam = cA.vraagNaamSpeler(teller);
                toonTekstOpHetScherm("");
                controleNaam = controleerNaamSpeler(naam)==false;
                if(controleNaam == true)
                {
                    String s = (char)27 + "[31m";
                    System.out.println(s+taal.getString("tekstOngeldigeNaam"));
                    System.out.println((char)27 + "[30m");
                }
            }while(controleNaam);
            spelerNaam=naam;
            
            //geboortejaarSpeler
            controleGeboortejaar = true;
            do{
                try{
                    geboortejaar = stringNaarInt(cA.vraagGeboortejaarSpeler(teller));
                    toonTekstOpHetScherm("");
                    if(controleerGeboortejaar(geboortejaar)) controleGeboortejaar=false;
                    else 
                    {
                            String s = (char)27 + "[31m";
                            System.out.printf("%s%s%n",s,taal.getString("tekstOngeldigeGeboortejaar"),teller+1," : ");
                            System.out.println((char)27 + "[30m");
                            controleGeboortejaar = true;
                    }
                }catch(Exception ex){
                    String s = (char)27 + "[31m";
                    System.out.printf("%s%s%n",s,taal.getString("tekstOngeldigeGeboortejaar"),teller+1," : ");
                    System.out.println((char)27 + "[30m");
                    controleGeboortejaar = true;
                }
            }while(controleGeboortejaar);
          //KleurSpeler
            controleKleur=true;
           
            while (controleKleur) { 
                try {
                    controleKleur = false;
                    int keuze = stringNaarInt(cA.vraagKleurSpeler(kleurOpties, teller));
                    toonTekstOpHetScherm("");
                    for(int x = 0 ; x < kleurOpties.size(); x++)
                    {
                        if(keuze==x+1)
                            {
                                kleur = (Color) kleurArray.get(x);
                                kleurOpties.remove(x);
                                kleurArray.remove(x);
                            }
                    }
                    if( kleur == Color.BLACK) {
                        String s = (char)27 + "[31m";
                        System.out.printf("%s%s%n",s,taal.getString("tekstOngeldigeKleur"),teller+1," : ");
                        System.out.println((char)27 + "[30m");
                        controleKleur = true;
                    }
                } catch (Exception e) {
                    controleKleur = true;
                    String s = (char)27 + "[31m";
                    System.out.printf("%s%s%n",s, taal.getString("tekstControleAantal"));
                    System.out.println((char)27 + "[30m");
                }
            }
            spelers.add(new Speler(spelerNaam, kleur, geboortejaar));
            clearConsole();
        }
    }
    
    public void maakNieuwSpelGUI(List<Speler> spelers)
    {
        Doolhof doolhof =new Doolhof();
        doolhof.generate();
        spel= new Spel(doolhof,this, taal);
        for (Speler speler1 : spelers) 
        {
            spel.addSpeler(speler1.getNaam(),speler1.getKleur(),speler1.getGeboortejaar());
        }
        zetSpelersInDeJuisteVolgorde();
        spel.voegSpelersAanDeDoolhof();
        spel.verdeelSchatten();
    }
    
    public void maakNieuwSpel(List<Speler> spelers)
    {
        Doolhof doolhof =new Doolhof();
        doolhof.generate();
        spel= new Spel(doolhof,this, taal);
        for (Speler speler1 : spelers) 
        {
            spel.addSpeler(speler1.getNaam(),speler1.getKleur(),speler1.getGeboortejaar());
        }
        zetSpelersInDeJuisteVolgorde();
        spel.voegSpelersAanDeDoolhof();
        spel.verdeelSchatten();
        cA.toonTekstOpHetScherm(taal.getString("tekstLosseTegel"));
        cA.toonTekstOpHetScherm(spel.tekenZweevendeTegel());
        cA.toonTekstOpHetScherm("");
        cA.toonTekstOpHetScherm(taal.getString("tekstDoolhofNaam"));
        cA.toonTekstOpHetScherm(spel.getDoolhof().toString());
        cA.toonTekstOpHetScherm(spel.toonTekstSpelers(taal));
        cA.toonTekstOpHetScherm((char)27 + "[30m");
        cA.toonTekstOpHetScherm(taal.getString("tekstVolgordeSpelers"));
        for (Speler geefLijstSpeler : spel.geefLijstSpelers()) 
        {
            cA.toonTekstOpHetScherm(geefLijstSpeler.getNaam());
        }
        cA.toonTekstOpHetScherm("");
    }
    
    public void berekenSpelerAanDeBeurt()
    {
        spel.berekenSpelerAanDeBeurt();
    }
    
    public String getNaamSpelerAanDeBeurt()
    {
        return spel.getNaamSpelerAanDeBeurt();
    }
    
    public String getGeboorteJaarSpelerAanDeBeurt()
    {
        return spel.getGeboorteJaarSpelerAanDeBeurt();
    }
    
    public String getKleurSpelerAanDeBeurt()
    {
        return spel.getKleurSpelerAanDeBeurt(taal);
    }
    
    public List<String> vuloptieArray()
    {
     List kleuropties = new ArrayList();
     //List Kleurarray = new ArrayList();
     kleuropties.add(taal.getString("tekstKleurRood"));
     kleuropties.add(taal.getString("tekstKleurBlauw"));
     kleuropties.add(taal.getString("tekstKleurGeel"));
     kleuropties.add(taal.getString("tekstKleurGroen"));
     return kleuropties;
    }
    
    public List vulKleurArray()
    { 
     List Kleurarray = new ArrayList();
     Kleurarray.add(Color.RED);
     Kleurarray.add(Color.BLUE);
     Kleurarray.add(Color.YELLOW);
     Kleurarray.add(Color.GREEN);
     return Kleurarray;
    }
    
    public SpelRepository getSpelRepository()
    {
        return spelRepository;
    }
    
    public SpelerRepository getSpelerRepository()
    {
        return spelerRepository;
    }
    
    public void setSpelerRepository()
    {
        this.spelerRepository=new SpelerRepository();
    }
    
    public void setSpelRepository()
    {
        this.spelRepository=new SpelRepository();
    }
    
    public Spel getSpel()
    {
        return this.spel;
    }
    
    public void setSpel(Spel spel)
    {
        this.spel=spel;
    }
 
    
    private void zetSpelersInDeJuisteVolgorde() {
        List<Speler> temp = new ArrayList<>();
        for(int teller=0;teller<spel.geefLijstSpelers().size();teller++)
            temp.add(spel.geefLijstSpelers().get(teller));
        spel.geefLijstSpelers().clear();
        spel.setSpelers(spel.berekenVolgordeSpelers(temp));
    }
    
    public String toonDoolhof(){
        String tekst="";
        tekst=tekst.concat(String.format("%s%n",spel.getDoolhof().toString()));
        tekst=tekst.concat(String.format("%s%n",spel.toonTekstSpelers(taal)));
        tekst=tekst.concat(String.format("%s%n",taal.getString("tekstVolgordeSpelers")));
        for (Speler geefLijstSpeler : spel.geefLijstSpelers()) 
        {
            tekst=tekst.concat(String.format("%s%n",geefLijstSpeler.getNaam()));
        }
        return tekst;
    }
    
    public void toonTekstOpHetScherm(String tekst){
        cA.toonTekstOpHetScherm(tekst);
    }
    
    public String toonTekstSpelersGUI(ResourceBundle taal){
        return spel.toonTekstSpelersGUI(taal);
    }
    
    public void speelSpel() {
        spel.speelSpel();
    }
    
    public String speelSpelGUI(){
        return spel.speelSpelGUI();
    }
    
    public void clearConsole(){
        ConsoleApplicatie.clearConsole();
    }
    
    public int vraagAantalSlagen(){
        int aantalSlagen=0;
        boolean controle = false;
        do{
            try {
                aantalSlagen= stringNaarInt(cA.vraagAantalSlagen());
                if(aantalSlagen==0 || aantalSlagen==1 || aantalSlagen==2 || aantalSlagen==3 ) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("tekstGelieveOptie0,1,2of3TeKiezen") +(char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return aantalSlagen;
    }
    public int vraagXCoordinaat(){
        int x=0;
        boolean controle = false;
        do{
            try {
                x= stringNaarInt(cA.vraagXCoordinaat());
                if(x==0 || x==1 || x==3 || x==5 || x==6 ) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+ taal.getString("tekstDeX-CoordinaatKan0,1,3Of5Zijn")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return x;
    }
    public int vraagYCoordinaat(){
        int y=0;
        boolean controle = false;
        do{
            try {
                y= stringNaarInt(cA.vraagYCoordinaat());
                if(y==0 || y==1 || y==3 || y==5 || y==6 ) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("tekstDeY-CoordinaatKan0,1,3Of5Zijn")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return y;
    }
    public int vraagBevestigingTegelInschuiven(int x,int y){
        int keuze=0;
        boolean controle = false;
        do{
            try {
                keuze = stringNaarInt(cA.vraagBevestigingTegelInschuiven(x, y));
                if(keuze==1 || keuze==2) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("GelieveOptie1Of2TeKiezen")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return keuze;
    }
    public int vraagSpelerVerplaatsen(){
        int keuze=0;
        boolean controle = false;
        do{
            try {
                keuze = stringNaarInt(cA.vraagSpelerVerplaatsen());
                if(keuze==1 || keuze==2) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("GelieveOptie1Of2TeKiezen")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return keuze;
    }
    public int vraagTeVerplaatsenRichting(){
       int keuze=0;
        boolean controle = false;
        do{
            try {
                keuze = stringNaarInt(cA.vraagTeVerplaatsenRichting());
                if(keuze==0 ||keuze==1 ||keuze==2 ||keuze==3 || keuze==4) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("tekstGelieveOptie0,1,2,3Of4TeKiezen")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return keuze;
    }
    public int vraagSpelerOpslaan(){
        int keuze=0;
        boolean controle = false;
        do{
            try {
                keuze = stringNaarInt(cA.vraagSpelerOpslaan());
                if(keuze==1 || keuze==2) controle=false;
                else {
                    controle = true;
                    System.out.println((char)27 + "[31m"+taal.getString("GelieveOptie1Of2TeKiezen")+ (char)27 + "[30m");
                }
            }catch (Exception e) {
                controle = true;
            }
        }while(controle);
        return keuze;
    }
    public String vraagSpelnaam(){
        String spelnaam ="";
        setSpelRepository();
        if(maakVerbindingMetSpelRep()){
            do{
                spelnaam=cA.vraagSpelnaam();
            }while(controleerSpelNaam(spelnaam)==false);
        }
        return spelnaam;
    }
    
    public void tekenDoolhofGUI(GridPane gridpane, ImageView imgV){
        spel.tekenDoolhofGUI(gridpane, imgV);
    }
    
    public void tekenZwevendeTegelGUI(GridPane gridpane, ImageView imgV){
        spel.tekenZwevendeTegelGUI(gridpane, imgV);
    }
    
    public void tekenPionSpeler(GridPane gridpane, ImageView imgV){
        spel.tekenPionSpeler(gridpane,imgV);
    }
    
    public void draaiTegel(int aantalSlagen) {
         spel.draaiTegel(aantalSlagen);
    }
    
    public boolean voerTegelIn(int x, int y) {
        return spel.voerTegelIn(x, y);
    }
    
    public void verplaatsSpeler(int richting){
        spel.verplaatsSpeler(richting);
    }
    
    public void tekenSpelerKaart(ImageView imgVVoorkantSpelerKaart,ImageView imgVAchterkantSpelerKaart, StackPane stackPaneSpelerKaart) {
        spel.tekenSpelerKaart(imgVVoorkantSpelerKaart, imgVAchterkantSpelerKaart, stackPaneSpelerKaart);
    }
    
    public boolean isSchatGevondenGUI(){
        return spel.isSchatGevondenGUI();
    }
    
    public boolean controleerEindeSpel(){
        return spel.controleerEindeSpel();
    }
    
    //Spel opslaan
//    public void maakVerbindingMetDatabank()
//    {
//        setSpelRepository();
//        setSpelerRepository();
//    }
    public boolean maakVerbindingMetSpelRep()
    {
        return spelRepository.maakVerbinding();
    }
    public boolean maakVerbindingMetSpelerRep()
    {
        return spelerRepository.maakVerbinding();
    }
    
    public void sluitVerbindingMetDatabank()
    {
        spelRepository.sluitVerbinding();
        spelerRepository.sluitVerbinding();
    }
    
    public void slaSpelOp(String spelnaam){
        maakVerbindingMetSpelRep();
        maakVerbindingMetSpelerRep();
        spel.setNaam(spelnaam);
        spelerRepository.voegSpelersToe(spel.geefLijstSpelers());
        spelRepository.slaSpelOp(spel,spelerRepository);
        sluitVerbindingMetDatabank();
    }
    public void updateSpel()
    {
        maakVerbindingMetSpelRep();
        maakVerbindingMetSpelerRep();
        spelRepository.updateSpel(spel, spelerRepository);
        sluitVerbindingMetDatabank();
    }
        
    public boolean controleerSpelNaam(String spelnaam){
        if(spelnaam.length()>=8)
        {
            for(int c=32; c<=47; c++)
            {
                if(spelnaam.contains(Character.toString((char) c)))
                    return false;
            }
            for(int c=58; c<=64; c++)
            {
                if(spelnaam.contains(Character.toString((char) c)))
                    return false;
            }
            for(int c=92; c<=96; c++)
            {
                if(spelnaam.contains(Character.toString((char) c)))
                    return false;
            }
            for(int c=123; c<=255; c++)
            {
                if(spelnaam.contains(Character.toString((char) c)))
                    return false;
            }
            
            int aantalcijfers=0;
            for(int i=0; i<spelnaam.length();i++)
            {
                for(int cijfer=48;cijfer<=57;cijfer++)
                {
                    if(spelnaam.charAt(i)==cijfer)
                    {
                        aantalcijfers++;
                    }
                }
            }
            if(aantalcijfers!=2) return false;
            
            return spelRepository.controleerSpelnaam(spelnaam);
        }
        else return false;
    }
    
    
    //spel terug ophalen
    public Collection geefNamenBestaandeSpellen(){
        Collection namen = new ArrayList<>();
        namen = spelRepository.geefNamenBestaandeSpellen();
        return namen;
    }
    
    public void toonGegevensGeselecteerdeSpel(String spelnaam, GridPane gridpane)
    {
        gridpane.getChildren().clear();
        gridpane.add(new Label(taal.getString("tekstNaam")),0,0);
        gridpane.add(new Label(taal.getString("tekstGeboortejaar")),2,0);
        gridpane.add(new Label(taal.getString("tekstKleur")),1,0);
        int spelId = spelRepository.geefIdBestaandSpel(spelnaam);
        int aantalSpelers = spelRepository.geefaantalSpelersBestaandSpel(spelId);
        List<Integer> idsSpelers = new ArrayList<>();
        idsSpelers = spelRepository.geefIdSpelersDieHetSpelSpelen(spelId);
        int teller=1;
        for (Integer huidigeSpeler : idsSpelers) {
            gridpane.add(new Label(spelerRepository.geefNaamSpeler(huidigeSpeler)), 0, teller);
            gridpane.add(new Label(taal.getString("tekstKleur"+spelRepository.geefKleurSpeler(spelId, huidigeSpeler))), 1, teller);
            gridpane.add(new Label(spelerRepository.geefGeboorteJaarSpeler(huidigeSpeler)+""), 2, teller);
            teller++;
        }
    }
    //we gaan nu alle gegevens van het spel terug halen en de doolhof maken
    public void laadSpel(String spelnaam)
    {
        int spelId = spelRepository.geefIdBestaandSpel(spelnaam);
        spel = new Spel(spelId, spelnaam);
        spel.setDomeinController(this);
        List<Integer> idsSpelers = new ArrayList<>();
        idsSpelers = spelRepository.geefIdSpelersDieHetSpelSpelen(spelId);
        
        //spelers toeveoegen aan spel
        int teller=0;
        for (Integer huidigeSpeler : idsSpelers) {
            String naam = spelerRepository.geefNaamSpeler(huidigeSpeler);
            int geboortejaar = spelerRepository.geefGeboorteJaarSpeler(huidigeSpeler);
            String kleur = spelRepository.geefKleurSpeler(spelId, huidigeSpeler);
            Color kleurSpeler = Color.BLACK;
            switch (kleur) {
                case "Blauw":
                    kleurSpeler=Color.BLUE;
                    break;
                case "Rood":
                    kleurSpeler=Color.RED;
                    break;
                case "Groen":
                    kleurSpeler=Color.GREEN;
                    break;
                case "Geel":
                    kleurSpeler=Color.YELLOW;
                    break;
            }
            spel.addSpeler(naam, kleurSpeler, geboortejaar);
            //schatten van de speler
            spel.geefLijstSpelers().get(teller).setSchat(vulLijstSpelerKaarten(spelRepository.geefSchattenSpeler(spelId, huidigeSpeler)));
            //positie van de speler
            spel.geefLijstSpelers().get(teller).setxCoordinaat(spelRepository.geefXCoordinaatSpeler(spelId, huidigeSpeler));
            spel.geefLijstSpelers().get(teller).setyCoordinaat(spelRepository.geefYCoordinaatSpeler(spelId, huidigeSpeler));
            //beurt
            spel.geefLijstSpelers().get(teller).setAanDeBeurt(spelRepository.geefBeurtSpeler(spelId, huidigeSpeler));
            teller++;
        }
        // doolhof maken
        
        Doolhof doolhof =new Doolhof();
        for(int x=0;x<7;x++)
        {
            for(int y=0;y<7;y++)
            {
                List<Doorgang> openingen = new ArrayList<>();
                if(spelRepository.geefOpeningNoordTegel(spelId, x, y)) openingen.add(Doorgang.N);
                if(spelRepository.geefOpeningOostTegel(spelId, x, y)) openingen.add(Doorgang.O);
                if(spelRepository.geefOpeningWestTegel(spelId, x, y)) openingen.add(Doorgang.W);
                if(spelRepository.geefOpeningZuidTegel(spelId, x, y)) openingen.add(Doorgang.Z);
                doolhof.setTegel(x, y, new Tegel((ArrayList<Doorgang>) openingen));
            }
        }
        List<Doorgang> openingen = new ArrayList<>();
        if(spelRepository.geefOpeningNoordTegel(spelId, -1, -1)) openingen.add(Doorgang.N);
        if(spelRepository.geefOpeningOostTegel(spelId, -1, -1)) openingen.add(Doorgang.O);
        if(spelRepository.geefOpeningWestTegel(spelId, -1, -1)) openingen.add(Doorgang.W);
        if(spelRepository.geefOpeningZuidTegel(spelId, -1, -1)) openingen.add(Doorgang.Z);
        doolhof.setZwevendeTegel(new Tegel((ArrayList<Doorgang>) openingen));
        
        spel.setDoolhof(doolhof);
        //schatten toevoegen
        List<Integer> xCoordinaten = new ArrayList<>();
        xCoordinaten = spelRepository.geefXCoordinatenSchatten(spelId);
        List<Integer> yCoordinaten = new ArrayList<>();
        yCoordinaten = spelRepository.geefYCoordinatenSchatten(spelId);
        List<Schat> schatten = new ArrayList<>();
        schatten = vulLijstSpelerKaarten(spelRepository.geefSchattenDoolhof(spelId));
        
        for(int aantalschatten=0;aantalschatten<24;aantalschatten++)
        {
            if(xCoordinaten.get(aantalschatten)==-1 && yCoordinaten.get(aantalschatten)==-1)
                spel.getDoolhof().getZwevendeTegel().setSchat(schatten.get(aantalschatten));
            else spel.getDoolhof().getTegel(xCoordinaten.get(aantalschatten), yCoordinaten.get(aantalschatten)).setSchat(schatten.get(aantalschatten));
        }
        
        //de spelers toevoegen op de juiste tegel
        for(int i = 0; i < spel.geefLijstSpelers().size(); i++)
        {
            spel.getDoolhof().getTegel(spel.geefLijstSpelers().get(i).getxCoordinaat(),spel.geefLijstSpelers().get(i).getyCoordinaat()).addSpeler(spel.geefLijstSpelers().get(i));
        }
        sluitVerbindingMetDatabank();
    }
    
    private ArrayList<Schat> vulLijstSpelerKaarten(List<String> kaarten){
        List<Schat> kaartenSpeler = new ArrayList<>();
        for(int teller=0;teller<kaarten.size();teller++)
        {
            for(Schat a : Schat.values())
            {
                if(kaarten.get(teller).equals(a.name()))
                    kaartenSpeler.add(a);
            }
        }
        return (ArrayList<Schat>) kaartenSpeler;
    }

    private void toonIdEnNamenSpellen() {
        List<Integer> idSpellen = new ArrayList<>();
        idSpellen = spelRepository.geefIdsBestaandeSpellen();
        List<String> namenSpellen = new ArrayList<>();
        namenSpellen = spelRepository.geefNamenBestaandeSpellen();
        String tekst =taal.getString("tekstOpgeslagenSpellen") + "\n";
        for(int teller=0;teller<idSpellen.size();teller++)
        {
            tekst+= idSpellen.get(teller) + " - " + namenSpellen.get(teller)+ "\n";
        }
        toonTekstOpHetScherm(tekst);
    }
    
    private boolean controlleerIdSpel(String idSpel)
    {
        int id = stringNaarInt(idSpel);
        return spelRepository.controleerIdSpel(id);
    }
    
    public void verwijderSpel()
    {
        maakVerbindingMetSpelRep();
        spelRepository.verwijderSpel(spel.getID());
        sluitVerbindingMetDatabank();
    }
}
