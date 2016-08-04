package cui;

import Exceptions.NotANumberException;
import domein.DomeinController;
import domein.Speler;
import java.awt.Color;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;


public class ConsoleApplicatie 
{
    Scanner invoer= new Scanner(System.in);
    DomeinController domeinController;
    ResourceBundle taal;
    
    /*De constructor maakt een instantie van de domeinController,
      roept de methode kiesTaal en geefKeuzeSpelLadenOfMaken,
      geeft de keuze door aan de domein controller
    */
    public ConsoleApplicatie(DomeinController dC){
        this.domeinController = dC;
        domeinController.setConsoleApplicatie(this);
        
        //we kiezen eerst de taal
        domeinController.kiesTaal();
        taal = domeinController.getTaal();
        
        //spel laden of maken
        domeinController.laadSpelOfMaakSpel();
    }
    
    // methode om de console te clearen
    public static void clearConsole()
    {
        try{
            Robot robot = new Robot();
            robot.keyPress(17);
            robot.keyPress(76);
            robot.keyRelease(17);
            robot.keyRelease(76);
            Thread.sleep(10);
            System.out.println();
            }catch(Exception ex){
                
            }
    }
    
    public void toonTekstOpHetScherm(String tekst){
        System.out.println(tekst);
    }
    
    // Er wordt een tekst getoond + keuze van de taal
    public String geefKeuzeTaal(ResourceBundle startUpTaal){
        String taal;
        clearConsole();
        System.out.println(startUpTaal.getString("tekstKiesTaalBeginEN"));
        System.out.println(startUpTaal.getString("tekstKiesTaalBeginFR"));
        System.out.println(startUpTaal.getString("tekstKiesTaalBeginNL"));
        System.out.println("EN - English");
        System.out.println("FR - Français");
        System.out.println("NL - Nederlands");
        System.out.print("Geef u keuze (EN/FR/NL): ");
        taal=invoer.next();
        return taal;
    }
    
    /* Er wordt eeen menu getoond in de gekozen taal*/
    public String geefKeuzeSpelLadenOfMaken(){
        clearConsole();
        System.out.printf("%s%n%s%n",taal.getString("tekstMenuMaakNieuwSpel"),taal.getString("tekstMenuLaadSpel"));
        System.out.printf(taal.getString("tekstKeuzeIngeven"));
        return invoer.nextLine();
    }
    
    //gegevens spelers opvragen
    public String vraagAantalSpelers(){
        System.out.printf("%s",taal.getString("tekstGeefAantalSpelers"));
        return invoer.nextLine();
    }
    public String vraagNaamSpeler(int teller){
        System.out.printf("%s%d%s",taal.getString("tekstNaamSpeler"),teller+1," : ");
        return invoer.nextLine();
    }
    public String vraagGeboortejaarSpeler(int teller){
        System.out.printf("%s%d%s",taal.getString("tekstGeboortejaarSpeler"),teller+1," : ");
        return invoer.nextLine();
    }
    public String vraagKleurSpeler(List kleurOpties, int teller){
        for(int tellerK = 0; tellerK < kleurOpties.size();tellerK++)
        {
            System.out.printf("%d - %s%n",tellerK+1,kleurOpties.get(tellerK));
        }
        System.out.printf("%s%d%s",taal.getString("tekstKleurSpeler"),teller+1," : ");
        return invoer.nextLine();
    }
    
    
    //spel spelen en beurt spelen
    public String geefKeuzeSpelSpelen(){
        System.out.println(taal.getString("tekstSpeelSpel"));
        System.out.println(taal.getString("tekstSpeelStop"));
        return invoer.next();
    }
    public String vraagXCoordinaat() {
        System.out.printf("%s" + ":", taal.getString("tekstVraagXCoordinaat"));
        return invoer.next();
    }
    public String vraagYCoordinaat() {
        System.out.printf("%s" + ":", taal.getString("tekstVraagYCoordinaat"));
        return invoer.next();
    }
    public String vraagAantalSlagen(){
        System.out.println(taal.getString("tekstHoeWiltUDeTegelDraaien"));
        System.out.println(taal.getString("tekstKwartslagNaarRechts"));
        System.out.println(taal.getString("tekstKwartslagNaarLinks"));
        System.out.println(taal.getString("tekstHalveSlag"));
        System.out.println(taal.getString("tekst0-SchuifTegelIn"));
        System.out.print(taal.getString("tekstOptie"));
        return invoer.next();
    }
    public String vraagTeVerplaatsenRichting(){
        System.out.println(taal.getString("tekstNaarBoven"));
        System.out.println(taal.getString("tekstNaarBeneden"));
        System.out.println(taal.getString("tekstNaarLinks"));
        System.out.println(taal.getString("tekstNaarRechts"));
        System.out.println(taal.getString("tekstSpeelStop"));
        System.out.print(taal.getString("tekstOptie"));
        return invoer.next();
    }
    public String vraagBevestigingTegelInschuiven(int x,int y){
        System.out.println(taal.getString("tekstBentUZekerDatUDeTegelOpPositie") + "["+x+"]["+y+"] "+ taal.getString("tekstWiltInschuiven"));
        System.out.println(taal.getString("tekst1-Ja"));
        System.out.println(taal.getString("tekst2-Nee"));
        System.out.print(taal.getString("tekstOptie"));
        return invoer.next();
    }
    public String vraagSpelerVerplaatsen(){
        System.out.println(taal.getString("tekstWiltUVerplaatsen"));
        System.out.println(taal.getString("tekst1-Ja"));
        System.out.println(taal.getString("tekst2-Nee"));
        System.out.print(taal.getString("tekstOptie"));
        return invoer.next();
    }
    
    //opslaan en terughalen
    public String vraagSpelerOpslaan(){
        System.out.println(taal.getString("tekstWiltUHetSpelOpslaanOfDeBeurtBeeindigen"));
        System.out.println(taal.getString("tekst1-SlaSpelOp"));
        System.out.println(taal.getString("tekst2-EindeBeurt"));
        System.out.print(taal.getString("tekstOptie"));
        return invoer.next();
    }
    public String vraagSpelnaam(){
        System.out.println(taal.getString("tekstGelieveEenNaamTeGevenVoorHetSpel"));
        System.out.print(taal.getString("tekstNaam")+":");
        return invoer.next();
    }
    public String vraagIdSpel(){
        System.out.println(taal.getString("tekstGelieveHetIdVanHetSpel"));
        System.out.println("Id: ");
        return invoer.next();
    }
}