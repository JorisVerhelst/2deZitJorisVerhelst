
package domein;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Generator {
    private enum Hoeken 
    {
        NW, NO, ZO, ZW;
    }
    private List<Schat> schat = new ArrayList<>();
    public Generator() 
    {
        
    }
    
    public void generate(Doolhof d) 
    {
        //we maken de tegels die vast zijn :)
        vulListSchat();
        maakVasteTegels(d);
        //we maken de tegels die los zijn :)
        maakLosseTegels(d);
        voegSchatAanHoekTegels(d);
    }
    private void vulListSchat()
    {
        for(Schat a : Schat.values())
        {
            schat.add(a);
        }
    }
    
    private void maakVasteTegels(Doolhof doolhof) {
        //hoek links bovenaan
        doolhof.setTegel(0, 0, maakHoekTegel(Hoeken.ZO));
        doolhof.getTegel(0, 0).setKleur(Color.YELLOW);
        //hoek rechts bovenaan
        doolhof.setTegel(0, 6, maakHoekTegel(Hoeken.ZW));
        doolhof.getTegel(0, 6).setKleur(Color.BLUE);
        //hoek links onderaan
        doolhof.setTegel(6, 0, maakHoekTegel(Hoeken.NO));
        doolhof.getTegel(6, 0).setKleur(Color.RED);
        //hoek rechts onderaan
        doolhof.setTegel(6, 6, maakHoekTegel(Hoeken.NW));
        doolhof.getTegel(6, 6).setKleur(Color.GREEN);
        
        // T-tegels buiteste rand
        Random random = new Random();
        int getal;
        for (int x = 2; x < 6;x+=2){
           // T-tegels eerste rij
           doolhof.setTegel(0,x, maakTTegel(Doorgang.N));
           getal=random.nextInt(schat.size());
           doolhof.getTegel(0, x).setSchat(schat.get(getal));
           schat.remove(getal);
           // T-tegels laatste rij
           doolhof.setTegel(6,x, maakTTegel(Doorgang.Z));
           getal=random.nextInt(schat.size());
           doolhof.getTegel(6,x).setSchat(schat.get(getal));
           schat.remove(getal);
        }
        for (int x = 2; x < 6;x+=2){
           // T-tegels eerste kolom
           doolhof.setTegel(x,0, maakTTegel(Doorgang.W));
           getal=random.nextInt(schat.size());
           doolhof.getTegel(x, 0).setSchat(schat.get(getal));
           schat.remove(getal);
           // T-tegels laatste kolom
           doolhof.setTegel(x,6, maakTTegel(Doorgang.O));
           getal=random.nextInt(schat.size());
           doolhof.getTegel(x, 6).setSchat(schat.get(getal));
           schat.remove(getal);
        }
        
        // T-tegels midden doolhof
        doolhof.setTegel(2,2, maakTTegel(Doorgang.W));
        getal=random.nextInt(schat.size());
        doolhof.getTegel(2,2).setSchat(schat.get(getal));
        schat.remove(getal);
        
        doolhof.setTegel(2,4, maakTTegel(Doorgang.N));
        getal=random.nextInt(schat.size());
        doolhof.getTegel(2,4).setSchat(schat.get(getal));
        schat.remove(getal);
        
        doolhof.setTegel(4,2, maakTTegel(Doorgang.Z));
        getal=random.nextInt(schat.size());
        doolhof.getTegel(4,2).setSchat(schat.get(getal));
        schat.remove(getal);
        
        doolhof.setTegel(4,4, maakTTegel(Doorgang.O));
        getal=random.nextInt(schat.size());
        doolhof.getTegel(4,4).setSchat(schat.get(getal));
        schat.remove(getal);
    }
    
    
    private void maakLosseTegels(Doolhof doolhof){
        Random getal = new Random();
        int i,j;
        int aantalHoek = 0,aantalRechteTegels=0, aantalTTegel=0;
        //we maken eerst de zwevende tegel
        switch (getal.nextInt(3)){
            case 0: 
                doolhof.setZwevendeTegel(maakTTegel(Doorgang.values()[getal.nextInt(Doorgang.values().length)]));
                int x = getal.nextInt(schat.size());
                doolhof.getZwevendeTegel().setSchat(schat.get(x));
                schat.remove(x);
                aantalTTegel++;
                break;
            case 1: 
                doolhof.setZwevendeTegel(maakRechteTegel(getal.nextBoolean()));
                aantalRechteTegels++;
                break;
            case 2: 
                doolhof.setZwevendeTegel(maakHoekTegel(Hoeken.values()[getal.nextInt(Hoeken.values().length)]));
                aantalHoek++;
                break;          
        }
        
        //we maken 15 of 16 hoek tegels die geplaatst worden op random platsen in de array
        do{
            i=getal.nextInt(7);
            j=getal.nextInt(7);
           
            if(doolhof.getTegel(i, j)==null)
            {
                doolhof.setTegel(i, j, maakHoekTegel(Hoeken.values()[getal.nextInt(Hoeken.values().length)]));
                aantalHoek++;
            }
        }while (aantalHoek<16);
        //we maken 9 of 10 rechte tegels die geplaatst worden op random platsen in de array
        do{
            i=getal.nextInt(7);
            j=getal.nextInt(7);
            
            if(doolhof.getTegel(i, j)==null)
            {
                doolhof.setTegel(i, j, maakRechteTegel(getal.nextBoolean()));
                aantalRechteTegels++;
            }
            
        }while (aantalRechteTegels<12);
        int x;
        do{
            i=getal.nextInt(7);
            j=getal.nextInt(7);
            
            if(doolhof.getTegel(i, j)==null)
            {
                doolhof.setTegel(i, j, maakTTegel(Doorgang.values()[getal.nextInt(Doorgang.values().length)]));
                x=getal.nextInt(schat.size());
                doolhof.getTegel(i, j).setSchat(schat.get(x));
                schat.remove(x);
                aantalTTegel++;
            }
        }while (aantalTTegel<6);
     }
    
    private void voegSchatAanHoekTegels(Doolhof doolhof)
    {
        Random getal = new Random();
        int i,j,x;
        int aantalHoekSchatten = 0;
        
        do{
            i=getal.nextInt(7);
            j=getal.nextInt(7);
            if((i==0 && j==0) || (i==0 && j==6) || (i==6 && j==0) || (i==6 && j==6))
            {
            }
            else{
                if(doolhof.getTegel(i, j).getOpeningen().size()==2)
                {
                    if((doolhof.getTegel(i, j).getOpeningen().get(0)!=Doorgang.N && doolhof.getTegel(i, j).getOpeningen().get(0)!= Doorgang.Z) || (doolhof.getTegel(i, j).getOpeningen().get(0)!=Doorgang.W && doolhof.getTegel(i, j).getOpeningen().get(0)!= Doorgang.O))
                    {
                        if(doolhof.getTegel(i, j).getSchat()==null)
                        {
                            x=getal.nextInt(schat.size());
                            doolhof.getTegel(i, j).setSchat(schat.get(x));
                            schat.remove(x);
                            aantalHoekSchatten++;
                        }
                    }
                }
            }
            
        }while (aantalHoekSchatten<6);
    }
    private Tegel maakTTegel(Doorgang gesloten) {
        Tegel tegel = new Tegel();
        switch(gesloten)
        {
            case N: 
                tegel.addOpening(Doorgang.O);
                tegel.addOpening(Doorgang.W);
                tegel.addOpening(Doorgang.Z);
                break;
            case Z:
                tegel.addOpening(Doorgang.N);
                tegel.addOpening(Doorgang.W);
                tegel.addOpening(Doorgang.O);
                break;
            case O:
                tegel.addOpening(Doorgang.N);
                tegel.addOpening(Doorgang.W);
                tegel.addOpening(Doorgang.Z);
                break;
            case W:
                tegel.addOpening(Doorgang.N);
                tegel.addOpening(Doorgang.Z);
                tegel.addOpening(Doorgang.O);
                break;
        }
        return tegel;
    }
    
    private Tegel maakRechteTegel(boolean isHorizontaal){
        Tegel tegel = new Tegel();
        if(isHorizontaal){
            tegel.addOpening(Doorgang.O);
            tegel.addOpening(Doorgang.W);
        }else{
            tegel.addOpening(Doorgang.N);
            tegel.addOpening(Doorgang.Z);
        }
        return tegel;
    }
   
    private Tegel maakHoekTegel(Hoeken hoektype){
        Tegel tegel = new Tegel();
        switch (hoektype)
        {
            case NW : 
                tegel.addOpening(Doorgang.N);
                tegel.addOpening(Doorgang.W);
                break;
            case NO :
                tegel.addOpening(Doorgang.N);
                tegel.addOpening(Doorgang.O);
                break;
            case ZW :
                tegel.addOpening(Doorgang.Z);
                tegel.addOpening(Doorgang.W);
                break;
            case ZO :
                tegel.addOpening(Doorgang.Z);
                tegel.addOpening(Doorgang.O);
                break;
        }
        return tegel;
    }
}
