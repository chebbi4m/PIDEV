/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Services.PartenaireService;
import Entities.Partenaire;
import java.util.List;

/**
 *
 * @author yasoulanda
 */
public interface IPartenaire {
      public void ajouterPartenaire( Partenaire p );
      public void modifierPartenaire( Partenaire p );
      public void supprimerPartenaire(Partenaire p);
      public List<Partenaire> afficherPartenaire( );
      public Partenaire getUserData(String username);
}
