/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.interfaces;

import java.util.List;
import taktak.entities.ColisN;

/**
 *
 * @author LENOVO THINKPAD E15
 */
public interface IColis {
    public void ajouterColis(ColisN cls);
    public void modifierColis(ColisN cls);
    public void supprimerColis(ColisN cls);
    public List<ColisN> afficherColis();
}