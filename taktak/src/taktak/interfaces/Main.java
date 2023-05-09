/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taktak.interfaces;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import taktak.entities.ColisN;
import taktak.entities.PartenaireN;

import taktak.services.ColisService;

import taktak.services.PartenaireServiceN;
import java.text.SimpleDateFormat;
import taktak.entities.LivreurInterfaceN;
import taktak.services.LivreurInterfaceServiceN;
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



//Colis cls = new ColisN ("10@hhh", 999, 30, 50, 2000,false,"Tunis", "Ariena","ahmeed", 2,3,5);
 
    // ColisService cs = new ColisService();
     //cs.ajouterColis(cls);
      //Colis cl = new ColisN (13,"chapeau", 100, 100, 50, 2, true,"Tunis", "Sousse", 2, 2);
      //cs.modifierColis(cls);
     //cs.supprimerColis(cls);
    
      
     //  System.out.println(cs.afficherColis());
      
      
      
      
      PartenaireN p1 = new PartenaireN  (21,"salah","salah@.com", 9998762,"Internationnale",7.58,5.45,false,true);
    PartenaireServiceN ps= new PartenaireServiceN() ;
   //  ps.ajouterPartenaire(p1);//cb
  ps.modifierPartenaire(p1);
    // ps.supprimerPartenaire(p1);//cb
     //System.out.println(ps.afficherPartenaire());
      
  

      
//
      
//LivreurInterface liv =new LivreurInterfaceN(2, "Ali", "Janjoun", "ali.j@example.com", "55522234", "arienaa");
  //  LivreurInterfaceServiceN ls= new LivreurInterfaceServiceN();
    //  ls.chercherLivreur(liv);
  // ls.ajouterLivreur(liv);
  // ls.supprimerLivreur(liv);
       // System.out.println(ls.chercherLivreur(liv.getNom("ali")));
       
       
       
       
       
       
    }
}

