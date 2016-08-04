
package persistentie;
import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpelerMapper 
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
    public List<Speler> geefSpelers() {
        List<Speler> spelers = new ArrayList<>();
        try {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM Speler");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("Naam");
                    int id=rs.getInt("SpelerID");
                    spelers.add(new Speler(id, naam));
                }
            }
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return spelers;
    }
    public void voegSpelerToe(String naam,int geboortejaar){
        try {
            PreparedStatement query = conn.prepareStatement("INSERT INTO Speler (Naam,Geboortejaar)"+ "VALUES (?,?)");
                query.setString(1, naam);
                query.setInt(2, geboortejaar);
                query.executeUpdate();
                
        } catch (Exception ex) {
            ex.printStackTrace();
       }
    }
    public boolean controleerNaamSpeler(String naam){
            try {
            PreparedStatement query = conn.prepareStatement("SELECT Naam FROM Speler WHERE Naam = ? ");
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
               if (rs.first()== false){
                   return false;       
               }
            }
            
        } catch (Exception ex){
            ex.printStackTrace();
        }
            return true;
    }
    public int geefSpelerID(String naam){
        int spelerID = -1;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT SpelerID FROM Speler WHERE Naam = ? ");
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     spelerID = rs.getInt("SpelerID");
                }
            }
            } catch (Exception ex){
            ex.printStackTrace();
        }
        return spelerID;
    }
    public int geefGeboorteJaarSpeler(int spelerID){
    int geboortejaar = 0;
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Geboortejaar FROM Speler WHERE SpelerID = ? ");
            query.setInt(1, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                     geboortejaar = rs.getInt("Geboortejaar");
                }
            }
            }catch (Exception ex){
            ex.printStackTrace();
        }
        return geboortejaar;
        }
    public String geefNaamSpeler(int spelerID){
        String naam="";
        try {
            PreparedStatement query = conn.prepareStatement("SELECT Naam FROM Speler WHERE SpelerID = ? ");
            query.setInt(1, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) 
                {
                    naam= rs.getString("Naam");
                }
            }
            }catch (Exception ex){
            ex.printStackTrace();
        }
        return naam;
    }
}

