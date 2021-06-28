package db;

public class Mot {
    
    private int id;
    private String valeur;
    private int grammer;

    public Mot(int id, String valeur, int grammer) {
        this.id = id;
        this.valeur = valeur;
        this.grammer = grammer;
    }

   
    public Mot() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public int getGrammer() {
        return grammer;
    }

    public void setGrammer(int grammer) {
        this.grammer = grammer;
    }

    
   

    
}
