/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taktak.interfaces;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import taktak.entities.Colis;
import taktak.entities.Partenaire;
import taktak.entities.Client;
import taktak.entities.Paiement;
import taktak.services.ColisService;
import taktak.services.ClientService;
import taktak.services.PaiementService;
import taktak.services.PartenaireService;
import java.text.SimpleDateFormat;
import taktak.entities.LivreurInterface;
import taktak.services.LivreurInterfaceService;
/**
 *
 * @author yasoulanda
 */
   
    public class Main {
//    static String url = "jdbc:mysql://localhost:3306/pidev";
//    static String login ="root";
//    static String password;
//    static Connection myConexBD;


    public static void main(String[] args) throws ParseException {
//    try {
//           myConexBD = DriverManager.getConnection(url, login, password);
//           System.out.println("connexion reussie");
//         Statement ste = myConexBD.createStatement();
//       } catch (SQLException ex) {
//             System.out.println(ex);
//       }



//Colis cls = new Colis ("10@hhh", 999, 30, 50, 2000,false,"Tunis", "Ariena","ahmeed", 2,3,5);
 
    // ColisService cs = new ColisService();
     //cs.ajouterColis(cls);
      //Colis cl = new Colis (13,"chapeau", 100, 100, 50, 2, true,"Tunis", "Sousse", 2, 2);
      //cs.modifierColis(cls);
     //cs.supprimerColis(cls);
    
      
     //  System.out.println(cs.afficherColis());
      
      
      
      
      Partenaire p1 = new Partenaire  (4,"salah","salah@.com", 9998762,"Voiture","Internationnale",7.58,5.45,false,true,"salah","salah123");
    PartenaireService ps= new PartenaireService() ;
     ps.ajouterPartenaire(p1);//cb
   // ps.modifierPartenaire(p1);
    //  ps.supprimerPartenaire(p1);//cb
     //System.out.println(ps.afficherPartenaire());
      
  

      
   // Paiement pa =new Paiement(2,"livraison","2023-01-11");
   // PaiementService pss=new PaiementService();
    //ps.AjouterPaiement(pa);
   // ps.ModifierPaiement(pa);
      // System.out.println(pss.AfficherPaiement());
      
  // LivreurInterface liv = new LivreurInterface (3,"ali","janjoun","ali@gmail.com","1234567");
    // LivreurInterfaceService ls= new LivreurInterfaceService();
    //  ls.chercherLivreur(liv);
     // ls.ajouterLivreur(liv);
       // System.out.println(ls.chercherLivreur(liv.getNom("ali")));
       
       
       
       
       
       
    }
}

