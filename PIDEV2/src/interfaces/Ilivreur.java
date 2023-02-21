package interfaces;




import entities.colis;
import entities.livreur;

import java.util.List;

public interface Ilivreur {
    public List<livreur> afficherleslivreurs();
    public void ajouterlivreur(livreur ls);

}
