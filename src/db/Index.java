package db;

public class Index {
    private int id;
    private int fichier;
    private int mot;
    private String act;

   

     public Index() {
       
    }

    public Index(int id, int fichier, int mot, String act) {
        this.id = id;
        this.fichier = fichier;
        this.mot = mot;
        this.act = act;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFichier() {
        return fichier;
    }

    public void setFichier(int fichier) {
        this.fichier = fichier;
    }

    public int getMot() {
        return mot;
    }

    public void setMot(int mot) {
        this.mot = mot;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }


    @Override
    public String toString() {
        return "ID : "+this.id+" Mot : "+this.mot+" Fichier : "+this.fichier+" Activite : "+this.act;
    }
      

    
    
    
    
}
