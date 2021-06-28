
package db;

public class Grammer {
    private int id;
    private String nom;
    private int activite;

    public Grammer(int id, String nom, int activite) {
        this.id = id;
        this.nom = nom;
        this.activite = activite;
    }

   

     public Grammer() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getActivite() {
        return activite;
    }

    public void setActivite(int activite) {
        this.activite = activite;
    }
     
   
     
     
}