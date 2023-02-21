/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taktak.interfaces;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import taktak.entities.Compte;
import taktak.entities.Colis;
import taktak.entities.Partenaire;
import taktak.entities.Client;
import taktak.entities.Paiement;
import taktak.services.ColisService;
import taktak.services.ClientService;
import taktak.services.PaiementService;
import taktak.services.PartenaireService;
import java.text.SimpleDateFormat;
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



//Colis cls = new Colis (10,"latifa", 999, 30, 50, 2,false,"Tunis", "Ariena", 2, 2);
 
    //  ColisService cs = new ColisService();
     //cs.ajouterColis(cls);
      //Colis cl = new Colis (13,"chapeau", 100, 100, 50, 2, true,"Tunis", "Sousse", 2, 2);
      //cs.modifierColis(cls);
     //cs.supprimerColis(cls);
    
      
      // System.out.println(cs.afficherColis());
      
      
      
      
      Partenaire p1 = new Partenaire  (1,"salah","123 Main St","johnpleàà@.com", 4587662,"najetcheebi","ddvgg4dg");
    PartenaireService ps= new PartenaireService() ;
     //ps.ajouterPartenaire(p1);//cb
    //ps.modifierPartenaire(p1);
      //ps.supprimerPartenaire(p1);//cb
     // System.out.println(ps.afficherPartenaire());
      
  

      
   // Paiement pa =new Paiement(2,"livraison","2023-01-11");
   // PaiementService pss=new PaiementService();
    //ps.AjouterPaiement(pa);
   // ps.ModifierPaiement(pa);
      // System.out.println(pss.AfficherPaiement());
      
      
      
    }
}

