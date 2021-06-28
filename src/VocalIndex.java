import db.*;
import edu.cmu.sphinx.jsgf.parser.ParseException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import recog.Recognizer;


public class VocalIndex {
     
       public static  void main(String[] args) throws Exception , IOException{
            //System.out.println(VocalIndex.class.getClassLoader().getResource("d/means"));
            //System.exit(0);
            //appel de func recognization vocal
            Properties prop = new Properties();
            prop.load(VocalIndex.class.getClassLoader().getResourceAsStream("config.properties"));
            DbClass.p = prop;
            DbClass dbObj = new DbClass();
            //Recognizer rec = new Recognizer(dbObj, null, null, null , prop);
            ArrayList<Activite> listActivites = dbObj.listActivites();
            ArrayList<Fichier> listFichiers = dbObj.listFichiers();
            int c = listFichiers.size();
            String date_db = null;
            
            
            
           if(c>1) {
              
               List<Fichier> arrlist1 = listFichiers.subList(0, c/2); 
               List<Fichier> arrlist2 = listFichiers.subList(c/2, c); 
               date_db = dbObj.datetime();
               Recognizer rec1 = new Recognizer(dbObj,listActivites,arrlist1,"rec1" , prop);
               Recognizer rec2 = new Recognizer(dbObj,listActivites,arrlist2,"rec2", prop);
               Thread th1 = new Thread(rec1);
               Thread th2 = new Thread(rec2);
               th1.start();
               th2.start();
               
           }
           else if (c == 1)
           {
               List<Fichier> ar = listFichiers.subList(0, c); 
               date_db = dbObj.datetime();
               Recognizer rec1 = new Recognizer(dbObj,listActivites,ar,"rec0" , prop);
               Thread th1 = new Thread(rec1);
               th1.start();
            
           }
           dbObj.insertDate(date_db , "" , "" );
           
    }
}