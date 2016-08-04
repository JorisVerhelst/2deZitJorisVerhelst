package domein;
import java.util.List;
import persistentie.SpelMapper;

public class SpelRepository {
    
    SpelMapper spelMapper = new SpelMapper();
    public SpelRepository()
    {
        
    }

    public boolean maakVerbinding(){
        return spelMapper.maakVerbinding();
    }
    public void sluitVerbinding(){
        spelMapper.sluitVerbinding();
    }

    
    //Deze methode geeft een lijst terug die bevat de ID's van de spelers die het spel spelen. 
    public List<Integer> geefIdSpelersDieHetSpelSpelen(int spelId)
    {
        return spelMapper.geefIdSpelersDieHetSpelSpelen(spelId);
    }
        
    public void slaSpelOp(Spel spel, SpelerRepository spelerRepository)
    {
        int spelID = -1;    
        spelMapper.slaSpelnaamOp(spel.getNaam());
        spelID= spelMapper.geefIdBestaandSpel(spel.getNaam());
        for (int i = 0; i < spel.geefLijstSpelers().size(); i++)
        {
            for (int j = 0; j < spel.geefLijstSpelers().get(i).getSchat().size(); j++) 
            {
               spelMapper.slaGegevensSpelersSchatOp(spelID,spelerRepository.geefSpelerID(spel.geefLijstSpelers().get(i).getNaam()),spel.geefLijstSpelers().get(i).getSchat().get(j).toString());
            }

          spelMapper.slaGegevensSpelersVanHetSpelOp(spelID,spelerRepository.geefSpelerID(spel.geefLijstSpelers().get(i).getNaam()),spel.geefLijstSpelers().get(i).getxCoordinaat() , spel.geefLijstSpelers().get(i).getyCoordinaat(), spel.geefLijstSpelers().get(i).getKleurString(),spel.geefLijstSpelers().get(i).isAanDeBeurt());
        }
        //we gaan eerst de zwevende tegel opslaan
        boolean noordZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.N);
        boolean zuidZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.Z);
        boolean westZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.W);
        boolean oostZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.O);
        String schatZwevendeTegel="";
        if(spel.getDoolhof().getZwevendeTegel().getSchat() != null)
        {
            schatZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getSchat().toString();
            spelMapper.slaGegevensDoolhofSchattenVanHetSpelOp(spelID, -1, -1, schatZwevendeTegel);

        }
        spelMapper.slaGegevensDoolhofVanHetSpelOp(spelID, -1, -1, noordZwevendeTegel, oostZwevendeTegel, zuidZwevendeTegel, westZwevendeTegel);
        //opslaan van de tegels van de doolhof
        for(int x = 0;x<7;x++)
        {
            for(int y = 0;y<7;y++)
            {
                boolean noord = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.N);
                boolean zuid = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.Z);
                boolean west = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.W);
                boolean oost = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.O);
                String schat="";
                if(spel.getDoolhof().getTegel(x, y).getSchat() != null)
                {
                    schat = spel.getDoolhof().getTegel(x, y).getSchat().toString();
                    spelMapper.slaGegevensDoolhofSchattenVanHetSpelOp(spelID, x, y, schat);
                }
                spelMapper.slaGegevensDoolhofVanHetSpelOp(spelID, x, y, noord, oost, zuid, west);
            }
        }  
    }
    
    public void updateSpel(Spel spel, SpelerRepository spelerRepository)
    {
        spelMapper.updateSpel(spel.getID());
        for (int i = 0; i < spel.geefLijstSpelers().size(); i++)
        {
            for (int j = 0; j < spel.geefLijstSpelers().get(i).getSchat().size(); j++) 
            {
               spelMapper.slaGegevensSpelersSchatOp(spel.getID(),spelerRepository.geefSpelerID(spel.geefLijstSpelers().get(i).getNaam()),spel.geefLijstSpelers().get(i).getSchat().get(j).toString());
            }

          spelMapper.slaGegevensSpelersVanHetSpelOp(spel.getID(),spelerRepository.geefSpelerID(spel.geefLijstSpelers().get(i).getNaam()),spel.geefLijstSpelers().get(i).getxCoordinaat() , spel.geefLijstSpelers().get(i).getyCoordinaat(), spel.geefLijstSpelers().get(i).getKleurString(),spel.geefLijstSpelers().get(i).isAanDeBeurt());
        }
        //we gaan eerst de zwevende tegel opslaan
        boolean noordZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.N);
        boolean zuidZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.Z);
        boolean westZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.W);
        boolean oostZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getOpeningen().contains(Doorgang.O);
        String schatZwevendeTegel="";
        if(spel.getDoolhof().getZwevendeTegel().getSchat() != null)
        {
            schatZwevendeTegel = spel.getDoolhof().getZwevendeTegel().getSchat().toString();
            spelMapper.slaGegevensDoolhofSchattenVanHetSpelOp(spel.getID(), -1, -1, schatZwevendeTegel);

        }
        spelMapper.slaGegevensDoolhofVanHetSpelOp(spel.getID(), -1, -1, noordZwevendeTegel, oostZwevendeTegel, zuidZwevendeTegel, westZwevendeTegel);
        //opslaan van de tegels van de doolhof
        for(int x = 0;x<7;x++)
        {
            for(int y = 0;y<7;y++)
            {
                boolean noord = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.N);
                boolean zuid = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.Z);
                boolean west = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.W);
                boolean oost = spel.getDoolhof().getTegel(x, y).getOpeningen().contains(Doorgang.O);
                String schat="";
                if(spel.getDoolhof().getTegel(x, y).getSchat() != null)
                {
                    schat = spel.getDoolhof().getTegel(x, y).getSchat().toString();
                    spelMapper.slaGegevensDoolhofSchattenVanHetSpelOp(spel.getID(), x, y, schat);
                }
                spelMapper.slaGegevensDoolhofVanHetSpelOp(spel.getID(), x, y, noord, oost, zuid, west);
            }
        }
    }
        
    public List<String> geefNamenBestaandeSpellen()
    {
        return spelMapper.geefNamenBestaandeSpellen();
    }
    public List<Integer> geefIdsBestaandeSpellen(){
        return spelMapper.geefIdsBestaandeSpellen();
    }
    
    public int geefaantalSpelersBestaandSpel(int spelId)
    {
        return spelMapper.geefaantalSpelersBestaandSpel(spelId);
    }
    
    public boolean controleerSpelnaam(String spelnaam)
    {
        return spelMapper.controleerNaam(spelnaam);
    }
    public boolean controleerIdSpel(int spelId){
        return spelMapper.controleerIdSpel(spelId);
    }
    
    public int geefIdBestaandSpel(String spelnaam){
        return spelMapper.geefIdBestaandSpel(spelnaam);
    }
    public String geefNaamBestaandSpel(int spelId){
        return spelMapper.geefNaamBestaandSpel(spelId);
    }
    
    public String geefKleurSpeler(int spelID, int spelerID){
        return spelMapper.geefKleurSpeler(spelID, spelerID);
    }
    public List<String> geefSchattenSpeler(int spelId, int spelerId){
        return spelMapper.geefSchattenSpeler(spelId, spelerId);
    }
    public int geefXCoordinaatSpeler(int spelID, int spelerID){
        return spelMapper.geefXCoordinaatSpeler(spelID, spelerID);
    }
    public int geefYCoordinaatSpeler(int spelID, int spelerID){
        return spelMapper.geefYCoordinaatSpeler(spelID, spelerID);
    }
    public boolean geefBeurtSpeler(int spelID, int spelerID){
        return spelMapper.geefBeurtSpeler(spelID, spelerID);
    }
    public boolean geefOpeningNoordTegel(int spelId, int x, int y){
        return spelMapper.geefOpeningNoordTegel(spelId, x, y);
    }
    public boolean geefOpeningZuidTegel(int spelId, int x, int y){
        return spelMapper.geefOpeningZuidTegel(spelId, x, y);
    }
    public boolean geefOpeningOostTegel(int spelId, int x, int y){
        return spelMapper.geefOpeningOostTegel(spelId, x, y);
    }
    public boolean geefOpeningWestTegel(int spelId, int x, int y){
        return spelMapper.geefOpeningWestTegel(spelId, x, y);
    }
    
    public List<Integer> geefXCoordinatenSchatten(int spelId){
        return spelMapper.geefXCoordinatenSchatten(spelId);
    }
    public List<Integer> geefYCoordinatenSchatten(int spelId){
        return spelMapper.geefYCoordinatenSchatten(spelId);
    }
    public List<String> geefSchattenDoolhof(int spelId){
        return spelMapper.geefSchattenDoolhof(spelId);
    }
    public void verwijderSpel(int spelId)
    {
        spelMapper.verwijderSpelBijHetWinnen(spelId);
    }
}