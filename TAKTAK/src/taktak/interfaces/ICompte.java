package taktak.interfaces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */



import taktak.entities.Compte;

/**
 *
 * @author yasoulanda
 */
public interface ICompte {
    
     public void ajouterCompte( Compte c );
     public void modifierCompte( Compte c );
     public void supprimerCompte();
     public void afficherCompte();
     
     
}
