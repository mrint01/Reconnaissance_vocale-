package db;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import recog.Recognizer;

public class DbClass {
    public static  Properties p;
    
       
    
    //connect to database 
    public static Connection ConnectDB() throws FileNotFoundException, IOException{
        //import infos from config file
        //connection start
        String url = DbClass.p.getProperty("urllocalhost");
        String username = DbClass.p.getProperty("username");
        String password = DbClass.p.getProperty("password");

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
      //connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
    }
    
// procedure get  files traiter 
 public int listtraite(){
   ArrayList<Fichier> fichtr = new ArrayList<Fichier>();
    try{
       Connection con=ConnectDB();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id , fichier_url , fichier_traite  from fichier where fichier_traite = 1");
        while(rs.next())
        {
            // ajouter les item dans une liste
            Fichier fich = new Fichier(rs.getInt("id") , rs.getString("fichier_url") , rs.getInt("fichier_traite") );
            fichtr.add(fich);
           
        }
         } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
    return fichtr.size();
} 
// procedure get all files 
 public ArrayList<Fichier> listallfich(){
   ArrayList<Fichier> allfich = new ArrayList<Fichier>();
    try{
       Connection con=ConnectDB();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id , fichier_url , fichier_traite  from fichier");
        while(rs.next())
        {
            // ajouter les item dans une liste
            Fichier fich = new Fichier(rs.getInt("id") , rs.getString("fichier_url") , rs.getInt("fichier_traite") );
            allfich.add(fich);
           
        }
         } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
    return allfich;
}
     // get id of line date
 public int getid(String date){
     String datedb;
     datedb = date;
     int h = 0;
     
     try{
       Connection con=ConnectDB();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id  from historique where date_debut = '"+datedb+"' ");
        while(rs.next())
        {
             h = rs.getInt("id");
        }
         } catch (SQLException | IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
     return h;
 }
 
      //date time insertion
    public void updateDatefin(String date_fin , int idi , String fich){
       String dates = null;
       int  id;
       String list = null;
       list = fich;
       dates = date_fin;
       id = idi;
        try{  
        
        Connection conn=ConnectDB();
            String query;
            query = "update historique set date_fin = '"+dates+"' ,  nbr_fichier_traite = '"+list+"'  where id = '"+id+"'";
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      // execute the preparedstatement
      preparedStmt.execute();
      //JOptionPane.showMessageDialog(null, "Data added");

        }catch(SQLException e){
            System.out.println("failed to update");
            
           
        }catch(IOException iox){
            
        }
           
    }
    
    
    //function date
    public String datetime ()
    {
         java.util.Date objDate = new java.util.Date(); 
             String strDateFormat = "dd-MMM-yyyy HH:mm:ss";
             SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
             String dfin = objSDF.format(objDate);
             //System.out.println(objSDF.format(objDate));
             return objSDF.format(objDate);
    }
    
    //date time insertion
    public void insertDate(String db  , String fin , String nb){
        String datedb, datefin , nbr ;
       datedb = db;
       datefin = fin;
       nbr = nb;
       
           try{  
        
        Connection conn=ConnectDB();
       
      
      String query;
            query = "insert into historique(id , date_debut , date_fin , nbr_fichier_traite) values (null, '"+datedb+"' , '"+datefin+"' , '"+nbr+"')";
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      // execute the preparedstatement
      preparedStmt.execute();
      //JOptionPane.showMessageDialog(null, "Data added");

        }catch(SQLException e){
            System.out.println("failed to insert");
            
           
        }catch(IOException iox){
            
        }
           
    }
    
    
    
     //procedure update into DataBase
    public static void update(){
       
         try{
        Connection conn=ConnectDB();
       
      
      String query = "update fichier  set fichier_traite = "+1;
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      // execute the preparedstatement
      preparedStmt.execute();

        }catch(SQLException e){
           
        
        }catch(IOException eio){
           
        }
        
    }
  
        
    //procedure insert into DataBase
    public static void insert(int f  , int m , String a){
        int mot, fich;
        String act;
       mot = m;
       fich = f;
       act = a;
       
           try{  
        
        Connection conn=ConnectDB();
       
      
      String query;
            query = "insert into indexe(id , index_fichier , index_mot, index_activite) values (null, '"+fich+"' , '"+mot+"' , '"+act+"')";
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      // execute the preparedstatement
      preparedStmt.execute();
      //JOptionPane.showMessageDialog(null, "Data added");

        }catch(SQLException e){
            System.out.println("errrr");
            
           
        }catch(IOException iox){
            
        }
           
    }
    
    

    

 
  // procedure add activite to list 
 public ArrayList<Activite> listActivites(){
   
     ArrayList<Activite> actlist = new ArrayList<Activite>();
     try{
         Connection con=ConnectDB();
   Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id , activite_nom  from activite");
        while(rs.next())
        {
            // ajouter les item dans une liste
            Activite act = new Activite(rs.getInt("id"),rs.getString("activite_nom"));
            actlist.add(act);
            
        }
         } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
    return actlist;
}

  

 // procedure add fichier to list 
 public ArrayList<Fichier> listFichiers(){
   ArrayList<Fichier> fichlist = new ArrayList<Fichier>();
    try{
       Connection con=ConnectDB();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id , fichier_url , fichier_traite  from fichier where fichier_traite = 0");
        while(rs.next())
        {
            // ajouter les item dans une liste
            Fichier fich = new Fichier(rs.getInt("id") , rs.getString("fichier_url") , rs.getInt("fichier_traite") );
            fichlist.add(fich);
           
        }
         } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
    return fichlist;
}
   
// procedure add grammer to list 
 public ArrayList<Grammer> listGrammers(int activite){
  
     ArrayList<Grammer> gr = new ArrayList<Grammer>();
   String query="";
    try {
        Connection con=ConnectDB(); 
   Statement stmt=con.createStatement();
        if(activite>0) {
            query = "select id , grammer_nom , grammer_activite from grammer where grammer_activite="+activite;
        } else {
            query = "select id , grammer_nom , grammer_activite from grammer";
        }
        ResultSet rs=stmt.executeQuery(query);
        
        while(rs.next())
            
        {
            // ajouter les item dans une liste
            Grammer gre = new Grammer(rs.getInt("id") , rs.getString("grammer_nom") , rs.getInt("grammer_activite"));
            gr.add(gre);
                         
            
        }
                       
                   } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
   
    return gr;
}

 
 // procedure add mot to list 
 public ArrayList<Mot> listMots(int grammer){
   ArrayList<Mot> motlist = new ArrayList<>();
   String query ="";
   
   try{
       
       Connection con=ConnectDB();
   Statement stmt=con.createStatement();
        if(grammer>0) {
            query = "select id , mot_valeur , mot_grammer  from mot where mot_grammer="+grammer;
        } else {
            query = "select id , mot_valeur , mot_grammer  from mot";
        }
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next())
        {
            // ajouter les item dans une liste
            Mot mt = new Mot(rs.getInt("id") , rs.getString("mot_valeur") , rs.getInt("mot_grammer"));
            motlist.add(mt);
           
            
        }
    } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
   
    return motlist;
}


// procedure add Indexe to list 
 public ArrayList<Index> listIndex(){
   ArrayList<Index> newfilelist = new ArrayList<Index>();
   
   try{
       Connection con=ConnectDB(); 
   Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select id , index_fichier , index_mot , act  from indexe");
        while(rs.next())
        {
            // ajouter les item dans une liste
            Index newfile = new Index(rs.getInt("id") , rs.getInt("index_fichier") , rs.getInt("index_mot"), rs.getString("index_activite"));
            newfilelist.add(newfile);
        }
       
   } catch (SQLException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   } catch (IOException ex) {
                       Logger.getLogger(Recognizer.class.getName()).log(Level.SEVERE, null, ex);
                   }
   
    return newfilelist; 
}
   
}