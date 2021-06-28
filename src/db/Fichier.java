package db;

public class Fichier {
    
   private int id;
   private String url;
   private int traite;

    public Fichier(int id, String url, int traite) {
        this.id = id;
        this.url = url;
        this.traite = traite;
    }

    

    public Fichier() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTraite() {
        return traite;
    }

    public void setTraite(int traite) {
        this.traite = traite;
    }
    
    
    
   
}