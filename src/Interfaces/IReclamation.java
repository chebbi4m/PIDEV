/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Entities.reclamation;
import Services.ReclamationService;
     

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
