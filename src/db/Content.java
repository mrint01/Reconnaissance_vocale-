package db;

import java.util.ArrayList;

public class Content {
    private ArrayList<Index> listRes = new ArrayList<Index>();
    private ArrayList<Heure> listhour = new ArrayList<Heure>();

    public Content(ArrayList<Index> listRes,ArrayList<Heure> listhour) {
        this.listRes=listRes;
        this.listhour=listhour;
    }

    public ArrayList<Index> getListRes() {
        return listRes;
    }

    public void setListRes(ArrayList<Index> listRes) {
        this.listRes = listRes;
    }

    public ArrayList<Heure> getListhour() {
        return listhour;
    }

    public void setListhour(ArrayList<Heure> listhour) {
        this.listhour = listhour;
    }
    
}
