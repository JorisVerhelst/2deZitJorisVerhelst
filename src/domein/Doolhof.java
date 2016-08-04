
package domein;

import java.awt.Color;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Doolhof 
{
    private final Tegel tegels[][];
    private Tegel zwevendeTegel;
    private final Generator generator;
    
    public Doolhof(){
        tegels=new Tegel[7][7];
        generator=new Generator();
    }
    public void generate(){
        generator.generate(this);
    }
    public Tegel getTegel(int x,int y){
        return tegels[x][y];
    }

    public Tegel[][] getTegels(){
        return tegels;
    }

    public void setTegel(int x,int y, Tegel tegel){
        tegels[x][y] = tegel;
    }
    public void setZwevendeTegel(Tegel tegel)
    {
        this.zwevendeTegel=tegel;
    }
    public Tegel getZwevendeTegel()
    {
        return zwevendeTegel;
    }
    
    public void voegSpelersAanDeDoolhof(List<Speler> spelers)
    {
        for (Speler speler : spelers) 
        {
            for(int y=0;y<tegels.length;y+=6)
            {
                if(speler.getKleur()==tegels[0][y].getKleur())
                {
                    tegels[0][y].addSpeler(speler);
                    speler.setxCoordinaat(0);
                    speler.setyCoordinaat(y);
                }
            }
            for(int y=0;y<tegels.length;y+=6)
            {
                if(speler.getKleur()==tegels[6][y].getKleur())
                {
                    tegels[6][y].addSpeler(speler);
                    speler.setxCoordinaat(6);
                    speler.setyCoordinaat(y);
                }
            }
        }
    }
    
    public String tekenZweevendeTegel()
    {
        String tekst="";
        for(int a=1;a<=5;a++)
        {
            if(a==1)
            {
                if(zwevendeTegel.getOpeningen().contains(Doorgang.N))
                {
                    tekst+=(char)27 + "[42m   "+(char)27 + "[47m     "+(char)27 + "[42m   ";
                }
                else tekst+=(char)27 + "[42m           ";
                }
            else if(a==2 || a==3 || a==4)
            {
                if(zwevendeTegel.getOpeningen().contains(Doorgang.W) && zwevendeTegel.getOpeningen().contains(Doorgang.O))
                {
                    if(a==2)
                    {
                        tekst+=(char)27 + "[47m           ";
                    }
                    if(a==3)
                    {
                        tekst+=(char)27 + "[47m           ";
                    } 
                            
                    if(a==4)
                    {
                        if(zwevendeTegel.getSchat()!=null)
                        {
                            tekst+=(char)27 + "[47m  " + zwevendeTegel.getSchat() + "  ";
                        }
                        else tekst+=(char)27 + "[47m           ";
                    }
                }
                else if(zwevendeTegel.getOpeningen().contains(Doorgang.W))
                {
                    if(a==2)
                    {
                        tekst+=(char)27 + "[47m         "+ (char)27 + "[42m  ";
                    }
                    if(a==4)
                    {
                        if(zwevendeTegel.getSchat()!=null)
                        {
                            tekst+=(char)27 + "[47m  " + zwevendeTegel.getSchat() +(char)27 + "[42m  ";
                        }
                        else tekst+=(char)27 + "[47m         "+(char)27 + "[42m  ";
                    }
                    if(a==3)
                    {
                        tekst+=(char)27 + "[47m         "+(char)27 + "[42m  ";
                    }
                }
                else if(zwevendeTegel.getOpeningen().contains(Doorgang.O))
                {
                    if(a==2)
                    {
                        tekst+=(char)27 + "[42m  " +(char)27 + "[47m         ";
                    }
                    if(a==4)
                    {
                        if(zwevendeTegel.getSchat()!=null)
                        {
                            tekst+=(char)27 + "[42m  "+ (char)27 + "[47m" + zwevendeTegel.getSchat() + "  ";
                        }
                        else tekst+=(char)27 + "[42m  "+(char)27 + "[47m         ";
                    }
                    if(a==3)
                    {
                        tekst+=(char)27 + "[42m  "+(char)27 + "[47m         ";
                    }
                }
                else
                {
                    if(a==2)
                    {
                        tekst+=(char)27 + "[42m  "+ (char)27 + "[47m       "+(char)27 + "[42m  ";
                    }
                    if(a==4)
                    {
                        if(zwevendeTegel.getSchat()!=null)
                        {
                            tekst+=(char)27 + "[42m  "+(char)27 + "[47m" + zwevendeTegel.getSchat() +(char)27 + "[42m  ";
                        }
                        else tekst+=(char)27 + "[42m  "+(char)27 + "[47m       "+(char)27 + "[42m  ";
                    }
                    if(a==3)
                    {
                        tekst+=(char)27 + "[42m  "+(char)27 + "[47m       "+(char)27 + "[42m  ";
                    }            
                }
            }
            else if(a==5)
            {
                if(zwevendeTegel.getOpeningen().contains(Doorgang.Z))
                {
                    tekst+=(char)27 + "[42m   "+(char)27 + "[47m     "+(char)27 + "[42m   ";
                }
                else tekst+=(char)27 + "[42m           ";
            }
            tekst+=(char)27 + "[0m\n";
        }
        return tekst;
    }
    
    public void draaiTegel(int aantalSlagen)
    {
        zwevendeTegel.draaiTegel(aantalSlagen);
    }
    
     @Override
    public String toString()
    {
        String blauw = (char)27 + "[44m";
        String rood = (char)27 + "[41m";
        String groen = (char)27 + "[42m";
        String geel = (char)27 + "[43m";
        String tekst="";
        for(int i=0;i<7;i++)
        {
            for(int a=1;a<=5;a++)
            {
                for(int j=0;j<7;j++)
                {
                    if(a==1)
                    {
                        if(tegels[i][j].getOpeningen().contains(Doorgang.N))
                        {
                            tekst+=(char)27 + "[42m   "+(char)27 + "[47m     "+(char)27 + "[42m   ";
                        }
                        else tekst+=(char)27 + "[42m           ";
                    }
                    else if(a==2 || a==3 || a==4)
                    {
                        if(tegels[i][j].getOpeningen().contains(Doorgang.W) && tegels[i][j].getOpeningen().contains(Doorgang.O))
                        {
                            if(a==2)
                            {
                                tekst+=(char)27 + "[47m   ["+ i + ","+j+"]   ";
                            }
                            if(a==3)
                            {
                                if(tegels[i][j].getSpelers().isEmpty()==false)
                                {
                                    switch(tegels[i][j].getSpelers().size())
                                    {
                                        case 1: 
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+=(char)27 + "[47m    "+ blauw +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+=(char)27 + "[47m    "+ rood +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+=(char)27 + "[47m    "+ groen +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+=(char)27 + "[47m    "+ geel +"   "+ (char)27+"[47m    ";
                                            break;
                                        case 2:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +"  "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +"  "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  "+ (char)27+"[47m   ";
                                            break;
                                        case 3:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m   ";
                                            break;
                                        case 4:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m   ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m   ";
                                            break;
                                    }
                                }
                                else tekst+=(char)27 + "[47m           ";
                            } 
                            
                            if(a==4)
                            {
                                if(tegels[i][j].getSchat()!=null)
                                {
                                    tekst+=(char)27 + "[47m  " + tegels[i][j].getSchat() + "  ";
                                }
                                else tekst+=(char)27 + "[47m           ";
                            }
                        }
                        else if(tegels[i][j].getOpeningen().contains(Doorgang.W))
                        {
                            if(a==2)
                            {
                                tekst+=(char)27 + "[47m   ["+ i + ","+j+"] "+ (char)27 + "[42m  ";
                            }
                            if(a==4)
                            {
                                if(tegels[i][j].getSchat()!=null)
                                {
                                    tekst+=(char)27 + "[47m  " + tegels[i][j].getSchat() +(char)27 + "[42m  ";
                                }
                                else tekst+=(char)27 + "[47m         "+(char)27 + "[42m  ";
                            }
                            if(a==3)
                            {
                                if(tegels[i][j].getSpelers().isEmpty()==false)
                                {
                                    switch(tegels[i][j].getSpelers().size())
                                    {
                                        case 1: 
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+=(char)27 + "[47m    "+ blauw +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+=(char)27 + "[47m    "+ rood +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+=(char)27 + "[47m    "+ groen +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+=(char)27 + "[47m    "+ geel +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            break;
                                        case 2:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                        case 3:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                        case 4:
                                            tekst+=(char)27 + "[47m   ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                    }
                                }
                                else tekst+=(char)27 + "[47m         "+(char)27 + "[42m  ";
                            }
                        }
                        else if(tegels[i][j].getOpeningen().contains(Doorgang.O))
                        {
                            if(a==2)
                            {
                                tekst+=(char)27 + "[42m  " +(char)27 + "[47m ["+ i + ","+j+"]   ";
                            }
                            if(a==4)
                            {
                                if(tegels[i][j].getSchat()!=null)
                                {
                                    tekst+=(char)27 + "[42m  "+ (char)27 + "[47m" + tegels[i][j].getSchat() + "  ";
                                }
                                else tekst+=(char)27 + "[42m  "+(char)27 + "[47m         ";
                            }
                            if(a==3)
                            {
                                if(tegels[i][j].getSpelers().isEmpty()==false)
                                {
                                    switch(tegels[i][j].getSpelers().size())
                                    {
                                        case 1: 
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+=(char)27 + "[42m  "+(char)27 + "[47m  "+ blauw +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+=(char)27 + "[42m  "+(char)27 + "[47m  "+ rood +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+=(char)27 + "[42m  "+(char)27 + "[47m  "+ groen +"   "+ (char)27+"[47m    ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+=(char)27 + "[42m  "+(char)27 + "[47m  "+ geel +"   "+ (char)27+"[47m    ";
                                            break;
                                        case 2:
                                            tekst+=(char)27 + "[42m  " + (char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +"  "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +"  "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            break;
                                        case 3:
                                            tekst+=(char)27 + "[42m  " + (char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            break;
                                        case 4:
                                            tekst+=(char)27 + "[42m  " + (char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[47m  ";
                                            break;
                                            
                                    }
                                }else tekst+=(char)27 + "[42m  "+(char)27 + "[47m         ";
                            }
                        }
                        else
                        {
                            if(a==2)
                            {
                                tekst+=(char)27 + "[42m  "+ (char)27 + "[47m ["+ i + ","+j+"] "+(char)27 + "[42m  ";
                            }
                            if(a==4)
                            {
                                if(tegels[i][j].getSchat()!=null)
                                {
                                    tekst+=(char)27 + "[42m  "+(char)27 + "[47m" + tegels[i][j].getSchat() +(char)27 + "[42m  ";
                                }
                                else tekst+=(char)27 + "[42m  "+(char)27 + "[47m       "+(char)27 + "[42m  ";
                            }
                            if(a==3)
                            {
                                if(tegels[i][j].getSpelers().isEmpty()==false)
                                {
                                    switch(tegels[i][j].getSpelers().size())
                                    {
                                        case 1: 
                                            tekst+=(char)27 + "[42m  "+(char)27 + "[47m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +"   "+ (char)27+"[47m  "+(char)27+"[42m  ";
                                            break;
                                        case 2:
                                            tekst+=(char)27 + "[42m  "+(char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +"  ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +"  "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                        case 3:
                                            tekst+=(char)27 + "[42m  "+(char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                        case 4:
                                            tekst+=(char)27 + "[42m  "+(char)27 + "[47m ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(0).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(1).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            tekst +=(char)27+"[47m ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.BLUE)
                                                tekst+= blauw +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.RED)
                                                tekst+= rood +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.GREEN)
                                                tekst+= groen +" ";
                                            if(tegels[i][j].getSpelers().get(2).getKleur()==Color.YELLOW)
                                                tekst+= geel +" ";
                                            
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.BLUE)
                                                tekst+= blauw +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.RED)
                                                tekst+= rood +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.GREEN)
                                                tekst+= groen +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            if(tegels[i][j].getSpelers().get(3).getKleur()==Color.YELLOW)
                                                tekst+= geel +" "+ (char)27+"[47m "+(char)27+"[42m  ";
                                            break;
                                    }
                                }
                                else tekst+=(char)27 + "[42m  "+(char)27 + "[47m       "+(char)27 + "[42m  ";
                            }
                            
                        }
                    }
                    else if(a==5)
                    {
                        if(tegels[i][j].getOpeningen().contains(Doorgang.Z))
                        {
                            tekst+=(char)27 + "[42m   "+(char)27 + "[47m     "+(char)27 + "[42m   ";
                        }
                        else tekst+=(char)27 + "[42m           ";
                    }
                    tekst+=(char)27 + "[0m  ";
                }
                tekst+="\n";
            }
            tekst+="\n";
        }
        return tekst;
    }
    
    public void tekenZwevendeTegelGUI(GridPane gridpane, ImageView imgV)
    {
        imgV= new ImageView(geefFotoOpBasisVanOpeningen(getZwevendeTegel().getOpeningen(),80));
        gridpane.add(imgV, 0, 0);
        if(getZwevendeTegel().getSchat()!=null)
        {
            imgV = new ImageView(new Image(getClass().getResourceAsStream("/images/"+getZwevendeTegel().getSchat()+".png"),35,35,true,true));
            gridpane.add(imgV, 0, 0);
            GridPane.setHalignment(imgV, HPos.CENTER);
            GridPane.setValignment(imgV, VPos.CENTER);
        }
    }
    
    public void tekenDoolhofGUI(GridPane gridpane, ImageView imgV)
    {
        
        String schat="";
        for(int x=0;x<7;x++)
        {
            for(int y=0;y<7;y++)
            {
                imgV = new ImageView(geefFotoOpBasisVanOpeningen(tegels[x][y].getOpeningen(),80));
                gridpane.add(imgV, y, x);
                if(tegels[x][y].getSchat()!=null)
                {
                    schat = "/images/"+tegels[x][y].getSchat()+".png";
                    imgV = new ImageView(new Image(getClass().getResourceAsStream(schat),35,35,true,true));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.CENTER);
                    GridPane.setValignment(imgV, VPos.CENTER);
                }
                if(tegels[x][y].getSpelers()!=null && !tegels[x][y].getSpelers().isEmpty())
                {
                    if(tegels[x][y].getSchat()!=null) 
                        tekenSpelersOpTegelGUI(gridpane, imgV, x, y, tegels[x][y].getSpelers(),true);
                    else
                        tekenSpelersOpTegelGUI(gridpane, imgV, x, y, tegels[x][y].getSpelers(),false);
                }
            }
        }
    }
    
    private void tekenSpelersOpTegelGUI(GridPane gridpane, ImageView imgV, int x, int y, List<Speler> spelers, boolean schat)
    {
        switch (spelers.size())
        {
            case 1: 
                    if(schat == true)
                    {
                        imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                        imgV.setOpacity(1);
                        gridpane.add(imgV, y, x);
                        GridPane.setHalignment(imgV, HPos.LEFT);
                        GridPane.setValignment(imgV, VPos.CENTER);
                    }
                    else 
                    {
                        imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                        gridpane.add(imgV, y, x);
                        GridPane.setHalignment(imgV, HPos.CENTER);
                        GridPane.setValignment(imgV, VPos.CENTER);
                    }
                break;
            case 2: imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.LEFT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(1).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.RIGHT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                break;
            case 3: imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.LEFT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(1).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.RIGHT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(2).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.CENTER);
                    GridPane.setValignment(imgV, VPos.TOP);
            case 4: imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.LEFT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.RIGHT);
                    GridPane.setValignment(imgV, VPos.CENTER);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.CENTER);
                    GridPane.setValignment(imgV, VPos.TOP);
                    imgV = new ImageView(geefFotoSpelerOpBasisVanKleur(spelers.get(0).getKleur(), 35));
                    gridpane.add(imgV, y, x);
                    GridPane.setHalignment(imgV, HPos.CENTER);
                    GridPane.setValignment(imgV, VPos.BOTTOM);
        }
    }
    
    private Image geefFotoSpelerOpBasisVanKleur(Color kleur, int imageSize)
    {
        Image image;
        if(kleur==Color.BLUE) image = new Image(getClass().getResourceAsStream("/images/Pion_Blauw.PNG"),imageSize,imageSize,true,true);
        else if(kleur==Color.RED) image = new Image(getClass().getResourceAsStream("/images/Pion_Rood.PNG"),imageSize,imageSize,true,true);
        else if(kleur==Color.GREEN) image = new Image(getClass().getResourceAsStream("/images/Pion_Groen.PNG"),imageSize,imageSize,true,true);
        else image = new Image(getClass().getResourceAsStream("/images/Pion_Geel.PNG"),imageSize,imageSize,true,true);
        return image;
    }
    
    private Image geefFotoOpBasisVanOpeningen(List<Doorgang> openingen, int imageSize)
    {
        Image image;
        if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==false && openingen.contains(Doorgang.W)==true && openingen.contains(Doorgang.O)==true)
        {
            image = new Image(getClass().getResourceAsStream("/images/TtegelN.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==false && openingen.contains(Doorgang.O)==true)
        {
            image = new Image(getClass().getResourceAsStream("/images/TtegelO.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==true && openingen.contains(Doorgang.O)==false)
        {
            image = new Image(getClass().getResourceAsStream("/images/TtegelW.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==false && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==true && openingen.contains(Doorgang.O)==true)
        {
            image = new Image(getClass().getResourceAsStream("/images/TtegelZ.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==false && openingen.contains(Doorgang.W)==false && openingen.contains(Doorgang.O)==true)
        {
            image = new Image(getClass().getResourceAsStream("/images/hoektegelNO.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==false && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==false && openingen.contains(Doorgang.O)==true)
        {
            image = new Image(getClass().getResourceAsStream("/images/hoektegelOZ.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==false && openingen.contains(Doorgang.W)==true && openingen.contains(Doorgang.O)==false)
        {
            image = new Image(getClass().getResourceAsStream("/images/hoektegelWN.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==false && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==true && openingen.contains(Doorgang.O)==false)
        {
            image = new Image(getClass().getResourceAsStream("/images/hoektegelZW.png"),imageSize,imageSize,true,true);
        }
        else if(openingen.contains(Doorgang.N) ==true && openingen.contains(Doorgang.Z)==true && openingen.contains(Doorgang.W)==false && openingen.contains(Doorgang.O)==false)
        {
            image = new Image(getClass().getResourceAsStream("/images/rechtetegelNZ.png"),imageSize,imageSize,true,true);
        }
        else image = new Image(getClass().getResourceAsStream("/images/rechtetegelOW.png"),imageSize,imageSize,true,true);
        
        return image;
    }
}
