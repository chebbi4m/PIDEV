/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.interfaces;
import java.util.List;
import taktak.entities.Partenaire;

/**
 *
 * @author Najet
 */
public interface IPartenaire {
   
    
     public void ajouterPartenaire( Partenaire p );
     public void modifierPartenaire( Partenaire p );
     public void supprimerPartenaire(Partenaire p);
     public List<Partenaire> afficherPartenaire( );
     
     


}
