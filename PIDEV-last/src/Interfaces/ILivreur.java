/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
    import java.util.List;
import Entities.Livreur;
import Services.LivreurService;
/**
 *
 * @author yasoulanda
 */

public interface ILivreur {
    public void ajouterLivreur(Livreur liv);
    public void supprimerLivreur(Livreur liv);
    public void modifierLivreur(Livreur liv);
    public void chercherLivreur(Livreur liv);
    public List<Livreur> afficherLivreur();
    public Livreur getUserData(String username);
}

