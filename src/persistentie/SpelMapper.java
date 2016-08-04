
package persistentie;

import domein.Spel;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpelMapper 
{
    Connection conn;
    public boolean maakVerbinding(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://78.23.196.95:3306/dbp1g31","doolhof","doolhof");
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public void sluitVerbinding(){
        try{
            conn.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    //nog niet klaar
    public List<Spel> geefSpellen() {
        List<Spel> spellen = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM Spel");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("Naam");
                    int id=rs.getInt("SpelID");
                    spellen.add(new Spel(id, naam));
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return spellen;
    }
    
    public void voegSpelNaamToe(String naam){
        try {
        System.out.println("Connection established!");
        PreparedStatement query = conn.prepareStatement("INSERT INTO Spel (Naam)"
                    + "VALUES (?)");
            query.setString(1, naam);
            query.executeUpdate();
        }catch (Exception ex) {
        ex.printStackTrace();
   }
  }
    
    public List<Integer> geefIdsBestaandeSpellen(){
        List<Integer> ids = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT SpelID FROM Spel");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt("SpelID"));
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
      return ids;
    }
    public List<String> geefNamenBestaandeSpellen(){
    List<String> namen = new ArrayList<>();
            try {
                PreparedStatement query = conn.prepareStatement("SELECT Naam FROM Spel");
                try (ResultSet rs = query.executeQuery()) {
                    while (rs.next()) {
                     namen.add(rs.getString("Naam"));
                    }
                    }
            } catch (Exception ex){
            ex.printStackTrace();
        }
      return namen;
  }
    
    public int geefaantalSpelersBestaandSpel(int spelID){
        int aantalSpelers=0; 
            try {
            PreparedStatement query = conn.prepareStatement("SELECT COUNT(SpelID) FROM Speelt WHERE SpelID=?");
            query.setInt(1, spelID);
                try (ResultSet rs = query.executeQuery()) {
                    while (rs.next()) {
                        aantalSpelers = rs.getInt(1);
                    }
                }
            }catch (Exception ex){
            ex.printStackTrace();
        }
      return aantalSpelers;
    }
    
    public List<Integer> geefIdSpelersDieHetSpelSpelen(int spelId) {
            List<Integer> spelerId = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM Speelt WHERE SpelID = ? ");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     spelerId.add(rs.getInt("SpelerID"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return spelerId;
        } 
    
    public boolean controleerNaam(String naam){
     try {
            PreparedStatement query = conn.prepareStatement("SELECT Naam FROM Spel WHERE Naam = ? ");
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
               if (rs.first()== true){
                   return false;                     
               }
            }
            }catch (Exception ex){
            ex.printStackTrace();
        }
            return true;
    }
    
    public boolean controleerIdSpel(int spelId){
     try {
            PreparedStatement query = conn.prepareStatement("SELECT SpelID FROM Spel WHERE SpelID = ? ");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
               if (rs.first()== true){
                   return true;                     
               }
            }
            }catch (Exception ex){
            ex.printStackTrace();
        }
            return false;
    }
    
    public void slaSpelnaamOp(String naam){
        //naam opslaan
        try {
        PreparedStatement query = conn.prepareStatement("INSERT INTO Spel (Naam)"
                    + "VALUES (?)");
            query.setString(1, naam);
            query.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
    
    public int geefIdBestaandSpel(String naam){
        int spelID=0;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT SpelID FROM Spel WHERE Naam = ? ");
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     spelID = rs.getInt("SpelID");
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return spelID;
    }
    public String geefNaamBestaandSpel(int spelId){
        String naam="";
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Naam FROM Spel WHERE SpelID = ? ");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     naam = rs.getString("Naam");
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return naam;
    }
    
    public void slaGegevensSpelersVanHetSpelOp(int spelID,int spelerID,int x,int y,String kleur,boolean beurt){
     try {
        PreparedStatement query = conn.prepareStatement("INSERT INTO Speelt (SpelerID,SpelID,X,Y,Kleur,Beurt)"+ "VALUES (?,?,?,?,?,?)");
            query.setInt(1, spelerID);
            query.setInt(2, spelID);
            query.setInt(3, x);
            query.setInt(4, y);
            query.setString(5, kleur);
            //kan Tinyint zijn 
            query.setBoolean(6, beurt);
            query.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
    
    public void slaGegevensSpelersSchatOp(int spelID,int spelerID,String schatten){
     try {
        PreparedStatement query = conn.prepareStatement("INSERT INTO SpelerSchatten (SpelerID,SpelID,Schat)"+ "VALUES (?,?,?)");
            query.setInt(1, spelerID);
            query.setInt(2, spelID);
            query.setString(3, schatten);
            query.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
    
    public void slaGegevensDoolhofVanHetSpelOp(int spelID,int x,int y, boolean noord, boolean oost, boolean zuid, boolean west){
     try {
        PreparedStatement query = conn.prepareStatement("INSERT INTO Doolhof (spelID,X,Y,Noord,Oost,Zuid,West)"+ "VALUES (?,?,?,?,?,?,?)");
            query.setInt(1, spelID);
            query.setInt(2, x);
            query.setInt(3, y);
            query.setBoolean(4, noord);
            query.setBoolean(5, oost);
            query.setBoolean(6, zuid);
            query.setBoolean(7, west);
            query.executeUpdate();
        }catch (Exception ex) {
        ex.printStackTrace();
        }
        finally{
         
        }
    }
    
    public void slaGegevensDoolhofSchattenVanHetSpelOp(int spelID,int x,int y, String schat){
        try {
        PreparedStatement query = conn.prepareStatement("INSERT INTO DoolhofSchatten (SpelID,X,Y,Schat)"+ "VALUES (?,?,?,?)");
            query.setInt(1,spelID);
            query.setInt(2, x);
            query.setInt(3, y);
            query.setString(4, schat);
            query.executeUpdate();
        } catch (Exception ex) {
        ex.printStackTrace();
        }
    }
    
    public String geefKleurSpeler(int spelID, int spelerID){
        String kleurSpeler = "";
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Kleur FROM Speelt WHERE SpelID = ? and SpelerID = ?");
            query.setInt(1, spelID);
            query.setInt(2, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     kleurSpeler = rs.getString("Kleur");
                }
            }
            
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return kleurSpeler;
    }
    public List<String> geefSchattenSpeler(int spelId, int spelerId){
        List<String> schatten = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Schat FROM SpelerSchatten WHERE SpelID = ? and SpelerID = ?");
            query.setInt(1, spelId);
            query.setInt(2, spelerId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     schatten.add(rs.getString("Schat"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return schatten;
    }
    public int geefXCoordinaatSpeler(int spelID, int spelerID){
        int x=0;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT X FROM Speelt WHERE SpelID = ? and SpelerID = ?");
            query.setInt(1, spelID);
            query.setInt(2, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     x = rs.getInt("X");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return x;
    }
    public int geefYCoordinaatSpeler(int spelID, int spelerID){
        int y=0;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Y FROM Speelt WHERE SpelID = ? and SpelerID = ?");
            query.setInt(1, spelID);
            query.setInt(2, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     y = rs.getInt("Y");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return y;
    }
    public boolean geefBeurtSpeler(int spelID, int spelerID){
        int i=0;
        boolean beurt=false;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Beurt FROM Speelt WHERE SpelID = ? and SpelerID = ?");
            query.setInt(1, spelID);
            query.setInt(2, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     i = rs.getInt("Beurt");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==0) beurt =false;
        else beurt = true;
        return beurt;
    }
    
    public boolean geefOpeningNoordTegel(int spelId, int x, int y)
    {
        int i=0;
        boolean beurt=false;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Noord FROM Doolhof WHERE SpelID = ? AND X = ? AND Y = ?");
            query.setInt(1, spelId);
            query.setInt(2, x);
            query.setInt(3, y);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     i = rs.getInt("Noord");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==0) beurt =false;
        else beurt = true;
        return beurt;
    }
    public boolean geefOpeningZuidTegel(int spelId, int x, int y)
    {
        int i=0;
        boolean beurt=false;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Zuid FROM Doolhof WHERE SpelID = ? AND X = ? AND Y = ?");
            query.setInt(1, spelId);
            query.setInt(2, x);
            query.setInt(3, y);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     i = rs.getInt("Zuid");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==0) beurt =false;
        else beurt = true;
        return beurt;
    }
    public boolean geefOpeningOostTegel(int spelId, int x, int y)
    {
        int i=0;
        boolean beurt=false;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Oost FROM Doolhof WHERE SpelID = ? AND X = ? AND Y = ?");
            query.setInt(1, spelId);
            query.setInt(2, x);
            query.setInt(3, y);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     i = rs.getInt("Oost");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==0) beurt =false;
        else beurt = true;
        return beurt;
    }
    public boolean geefOpeningWestTegel(int spelId, int x, int y)
    {
        int i=0;
        boolean beurt=false;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT West FROM Doolhof WHERE SpelID = ? AND X = ? AND Y = ?");
            query.setInt(1, spelId);
            query.setInt(2, x);
            query.setInt(3, y);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     i = rs.getInt("West");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        if(i==0) beurt =false;
        else beurt = true;
        return beurt;
    }
    
    //schatten terug halen
    public List<Integer> geefXCoordinatenSchatten(int spelId){
        List<Integer> xCoordinaten = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT X FROM DoolhofSchatten WHERE SpelID = ?");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     xCoordinaten.add(rs.getInt("X"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return xCoordinaten;
    } 
    public List<Integer> geefYCoordinatenSchatten(int spelId){
        List<Integer> yCoordinaten = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Y FROM DoolhofSchatten WHERE SpelID = ?");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     yCoordinaten.add(rs.getInt("Y"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return yCoordinaten;
    }
    public List<String> geefSchattenDoolhof(int spelId){
        List<String> schatten = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Schat FROM DoolhofSchatten WHERE SpelID = ?");
            query.setInt(1, spelId);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     schatten.add(rs.getString("Schat"));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return schatten;
    }
    
    
    public void verwijderSpelBijHetWinnen(int spelID){
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM Spel WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM Doolhof WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM DoolhofSchatten WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM Speelt WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM SpelerSchatten WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void updateSpel(int spelID){
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM Doolhof WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM DoolhofSchatten WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM Speelt WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        try {
            PreparedStatement query = conn.prepareStatement("DELETE FROM SpelerSchatten WHERE SpelID = ?");
            query.setInt(1, spelID);
            query.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
