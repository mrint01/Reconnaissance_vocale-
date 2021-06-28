
package db;
public class Heure {
    
    private int id ;
    private  int fichier;
    private  String hour;
    
    public Heure() {
        
    }

    public Heure(int id, int fichier, String hour) {
        this.id = id;
        this.fichier = fichier;
        this.hour = hour;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    
    
    
    
    
    
}
