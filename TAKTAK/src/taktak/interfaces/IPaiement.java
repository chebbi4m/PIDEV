/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.interfaces;


import java.util.List;
import taktak.entities.Paiement;

/**
 *
 * @author Najet
 */
public interface IPaiement {
    public void AjouterPaiement(Paiement pa);
    public void ModifierPaiement(Paiement pa);
    public List<Paiement> AfficherPaiement();
    
}
