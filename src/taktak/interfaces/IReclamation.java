/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taktak.interfaces;

import java.util.List;
import taktak.entities.reclamation;
import taktak.services.ReclamationService;
     

/**
 *
 * @author chebbi4m
 */
public interface IReclamation {
    public void ajouterReclamation(reclamation rls);
    public void updateReclamation(reclamation rls);
    public void deleteReclamation(int id);
    public List<reclamation> afficherReclamation();
}
