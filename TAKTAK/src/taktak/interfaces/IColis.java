package taktak.interfaces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */



import taktak.entities.Colis;
import java.util.List;

/**
 *
 * @author yasoulanda
 */
public interface IColis {
     public void ajouterColis(Colis cls);
    public void modifierColis(Colis cls);
    public void supprimerColis(Colis cls);
    public List<Colis> afficherColis();
}
