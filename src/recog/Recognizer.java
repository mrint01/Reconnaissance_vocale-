package recog;
import java.io.File;
import java.net.URL;
import java.io.FileInputStream;
import java.io.InputStream;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import java.io.IOException;
import db.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
        public class Recognizer implements Runnable {
          private String name;
          private String x;
          private ArrayList<Activite> listActivites;
          private List<Fichier> listFichiers;
          private ArrayList<Index> listIndexGlobal = new ArrayList<Index>();
          private DbClass dbObj;
          private Properties p;
          private static int id=0;
          private String ac;
    
    
    
    public Recognizer(DbClass dbObj ,ArrayList<Activite> listActivites,List<Fichier> listFichiers, String name , Properties prop ) {
        this.listActivites = listActivites;
        this.listFichiers = listFichiers;
        this.dbObj = dbObj;
        this.name=name;
        this.p  =  prop;
      
    }
   
    @Override
    public void run() {
        String date_db = null;
        String nbr = null;
        id = dbObj.listtraite();
        date_db = dbObj.datetime();
        int allList = dbObj.listallfich().size();
        Iterator iterator = listFichiers.iterator();
            while(iterator.hasNext()){
              Fichier currentFichier = (Fichier)iterator.next();
              
              synchronized(this) {
                  id++;
                  nbr = " "+id+"/"+allList+" ";
                  
               for(int j=0;j<listActivites.size();j++) {
                   Activite currentActivite=listActivites.get(j);
                   ac = currentActivite.getNom();
                   ArrayList<Grammer> listGrammers = dbObj.listGrammers(currentActivite.getId());
               for(int k=0;k<listGrammers.size();k++) {
                   Grammer currentGrammer = listGrammers.get(k);
                  ArrayList<Mot> listMots = dbObj.listMots(currentGrammer.getId());
                  ArrayList<Index> contentIndex = null;
                       try {
                           contentIndex = this.rec(currentFichier, currentGrammer, listMots , ac);
                       } catch (IOException ex) {
                           ex.printStackTrace();
                           Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   listIndexGlobal.addAll(contentIndex);
                 }
                  
               }
                System.out.println(this.name + " wait()");
              try {
                  this.wait(900);
              } catch (InterruptedException ex) {
                  Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
              }
                            
          } System.out.println(nbr);

              
              
             for(int h=0; h<listIndexGlobal.size(); h++){
                 
               Index curre = listIndexGlobal.get(h);
               //System.out.println(ac);
               System.out.println(curre.getFichier() + " :::::: " + curre.getMot()+ " :::: "+ curre.getAct() );
               DbClass.insert(curre.getFichier(), curre.getMot() , curre.getAct());
               DbClass.update();
               }
             
             String date_fin = dbObj.datetime();
             dbObj.updateDatefin(date_fin , dbObj.getid(date_db) , nbr);
             listIndexGlobal.clear();
              }
             
              
    }
    public ArrayList<Index> rec(Fichier fichier,Grammer grammer,ArrayList<Mot> listMots , String ac) throws IOException 
    {       
                ArrayList<Index> listRes = new ArrayList<Index>();
                // to disable the console msg
                Logger cmRootLogger = Logger.getLogger("default.config");
                cmRootLogger.setLevel(java.util.logging.Level.OFF);
                String conFile = System.getProperty("java.util.logging.config.file");
                if (conFile == null) {
                 System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
                   }
                 //////////////////////////////////
                //recognizer of file son from 2 file or more grammar files
                Configuration configuration = new Configuration();
                configuration.setAcousticModelPath(this.p.getProperty("urldic"));
		configuration.setDictionaryPath(this.p.getProperty("urldicfr"));
                //configuration.setLanguageModelPath("src/java/fr-small.lm.bin");
                configuration.setGrammarPath(this.p.getProperty("urlgrammer"));
                configuration.setGrammarName("grammar_"+grammer.getId());
		configuration.setUseGrammar(true);
                
                StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
                InputStream is = new URL(fichier.getUrl()).openStream();
                //InputStream stream = new FileInputStream(new File(fichier.getUrl()));
                recognizer.startRecognition(is);
                SpeechResult result;
              // the result of the recognizer and showed here
                while ((result = recognizer.getResult()) != null) 
                    {
                        
                       for(int i=0;i<listMots.size();i++) {
                           Mot currentMot = listMots.get(i);
                           if(currentMot.getValeur().equals(result.getHypothesis())) {
                               
                           listRes.add(new Index(0, fichier.getId(), currentMot.getId(), ac));
                           }
                       
                        }
                     }
                     recognizer.stopRecognition();
                     
                     return listRes;
        }
}