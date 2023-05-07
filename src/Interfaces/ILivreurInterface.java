/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;
import Entities.LivreurInterface;

/**
 *
 * @author Cheima
 */
public interface ILivreurInterface {
  public void modifierLivreurD(LivreurInterface lv);  
  public List<LivreurInterface> afficherLivreurD();
}
