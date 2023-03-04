/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.interfaces;

/**
 *
 * @author LENOVO THINKPAD E15
 */

import java.util.Random;
import taktak.services.ColisService;
import taktak.entities.Colis;
import taktak.entities.RandomGenerator;


public class Main {
    
    public static void main(String[] args) {
       
       RandomGenerator generator = new RandomGenerator();
       String randomString = generator.generateRandomString();
       
         
       Colis cls = new Colis();
       cls.setRef(randomString);
       cls.setHauteur(10);
       cls.setLargeur(15);
       cls.setPoids(10);
       cls.setFragile(true);
       cls.setInflammable(true);
       cls.setDepart("Sousse");
       cls.setDestination("Paris");
       cls.setEtat_colis(cls.getEtat_colis());
       cls.setZone("Nationale");
       cls.setUrgent(false);
       cls.setId_client(1);
       cls.setId_livreur(0);
       
       if(cls.getPoids()<= 5 && "Internationale".equals(cls.getZone())){
           int[] array = new int[] {170, 150, 100, 80};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       else if(cls.getPoids()<= 5 && "Nationale".equals(cls.getZone())){
           int[] array = new int[] {10, 9, 8, 7};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       
       else if(cls.getPoids()> 5 && "Internationale".equals(cls.getZone())){
           int[] array = new int[] {180, 185, 186, 187};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       
       else if(cls.getPoids()> 5 && "Nationale".equals(cls.getZone())){
           int[] array = new int[] {15, 16, 17, 18};
           Random rand = new Random();
           cls.setPrix(array[rand.nextInt(array.length)]);
       }
       
       else{
           System.out.println("Vérifier le prix et la zone");
       }
       cls.setPrix(cls.getPrix());
       System.out.println("Votre prix est de " +cls.getPrix() + "dt");
       
       ColisService cs = new ColisService();
       //cs.ajouterColis(cls);
       //cs.supprimerColis(cls);
        
       System.out.println(cs.afficherColis());
        
    }
}
