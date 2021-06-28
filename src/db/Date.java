package db;

public class Date {
    
    private int id;
    private String date_db;
    private String date_fin;
    private String nbr_execution;
    
    public Date()
    {
        
    }

    public Date(int id, String date_db, String date_fin, String nbr_execution) {
        this.id = id;
        this.date_db = date_db;
        this.date_fin = date_fin;
        this.nbr_execution = nbr_execution;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_db() {
        return date_db;
    }

    public void setDate_db(String date_db) {
        this.date_db = date_db;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getNbr_execution() {
        return nbr_execution;
    }

    public void setNbr_execution(String nbr_execution) {
        this.nbr_execution = nbr_execution;
    }
    
    
    
    
}
