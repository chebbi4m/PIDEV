/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.interfaces;

/**
 *
 * @author LENOVO THINKPAD E15
 */

import taktak.services.ColisService;
import taktak.entities.Colis;
import taktak.entities.Livreur;
import taktak.services.LivreurService;
import gui.LivreurController;


public class Main {
    
    public static void main(String[] args) {
       
//       Colis cls = new Colis (1,"hello", 500, 300, 42, 2, true, "Tunis", 2, 2);
//       Colis cls1 = new Colis (2,"hello", 300, 200, 42, 2, true, "Tunis", 2, 2);
       Livreur liv = new Livreur (169,"mohamed", "chebbi", "douisscheima4@gmail.com", "99781095","hello","mdp");
        Livreur liv1 = new Livreur (150,"mohamed", "douiss", "douisscheima4@gmail.com", "99781095","hello","mdp");
       
 
//       ColisService cs = new ColisService();
       LivreurService ls = new LivreurService();
//       ls.ajouterLivreur(liv);
//         ls.modifierLivreur(liv);
//           ls.chercherLivreur(liv);
//       System.out.println(ls.afficherLivreur());
//       ls.ajouterLivreur(liv1);
//       ls.ajouterLivreur(liv);
//       ls.supprimerLivreur(liv);
//       cs.modifierColis(cls1);
//       System.out.println(cs.afficherColis());
        
    }
}
