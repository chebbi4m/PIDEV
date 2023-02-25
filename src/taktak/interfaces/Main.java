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


public class Main {
    
    public static void main(String[] args) {
       
       Colis cls = new Colis ("5588jde", 14, 20, 10, 300, true, true,"Nabeul", "Sfax", "Livré","internationale",false, 2,1);
 
       
       

       ColisService cs = new ColisService();
       cs.ajouterColis(cls);
       //cs.supprimerColis(cls);
        
       System.out.println(cs.afficherColis());
        
    }
}
