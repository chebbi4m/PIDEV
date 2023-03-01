/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.interfaces;

import java.util.List;
import taktak.entities.Colis;

/**
 *
 * @author LENOVO THINKPAD E15
 */
public interface IColis {
    public void ajouterColis(Colis cls);
    public void modifierColis(Colis cls);
    public void supprimerColis(Colis cls);
    public List<Colis> afficherColis();
}