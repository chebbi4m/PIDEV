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
       
       Colis cls = new Colis ("abcd123", 152, 243, 42, 2, false,"Sousse", "Tunis", "Moi", 2, 2);
 
       
       

       ColisService cs = new ColisService();
       cs.ajouterColis(cls);
        
       System.out.println(cs.afficherColis());
        
    }
}
