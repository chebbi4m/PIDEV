package Interfaces;

import java.util.List;
import Entities.LivreurInterfaceN;

/**
 *
 * @author Cheima
 */
public interface ILivreurInterfaceN {
    
  public void ajouterLivreur(LivreurInterfaceN lv);
   public void supprimerLivreur(LivreurInterfaceN lv);
  public List<LivreurInterfaceN> afficherLivreurD();
  public void chercherLivreur(LivreurInterfaceN lv);
}