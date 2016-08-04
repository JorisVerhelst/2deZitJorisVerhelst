
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelerMapper;

public class SpelerRepository 
{
    private List<Speler> alleSpelers;
    SpelerMapper spelerMapper = new SpelerMapper();
    
    public SpelerRepository()
    {
        alleSpelers = new ArrayList<>();
    }
    
    public void vulLijstSpelers(){
        alleSpelers=spelerMapper.geefSpelers();
    }
    
    //Deze methode geeft een lijst terug van spelers die dezelfde ID's hebben zoals in de list spelersIDs staan. 
    public boolean maakVerbinding(){
        return spelerMapper.maakVerbinding();
    }
    public void sluitVerbinding(){
        spelerMapper.sluitVerbinding();
    }
    
    public void voegSpelersToe(List<Speler> spelers)
    {
        vulLijstSpelers();
        boolean bestaat;
        for (int teller = 0 ; teller<spelers.size();teller++)
        {
            bestaat = false;
         
            for (int x = 0 ; x<alleSpelers.size(); x++)
            {
                if(spelers.get(teller).getNaam() == null ? alleSpelers.get(x).getNaam() == null : spelers.get(teller).getNaam().equals(alleSpelers.get(x).getNaam()))
                {
                    bestaat = true;
                }
            }
            if (bestaat == false)
            {
                spelerMapper.voegSpelerToe(spelers.get(teller).getNaam(), spelers.get(teller).getGeboortejaar());
            }
        }
    }
    
    public int geefSpelerID(String naam){
        return spelerMapper.geefSpelerID(naam);
    }
    public String geefNaamSpeler(int spelerID){
        return spelerMapper.geefNaamSpeler(spelerID);
    }
    public int geefGeboorteJaarSpeler(int spelerID){
        return spelerMapper.geefGeboorteJaarSpeler(spelerID);
    }
}
