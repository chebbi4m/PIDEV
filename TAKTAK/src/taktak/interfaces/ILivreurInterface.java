package taktak.interfaces;

import java.util.List;
import taktak.entities.LivreurInterface;

/**
 *
 * @author Cheima
 */
public interface ILivreurInterface {
  public void modifierLivreurD(LivreurInterface lv);  
  public void ajouterLivreur(LivreurInterface lv);
  public List<LivreurInterface> afficherLivreurD();
  public void chercherLivreur(LivreurInterface lv);
}