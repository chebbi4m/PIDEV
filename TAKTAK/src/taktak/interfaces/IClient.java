package taktak.interfaces;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */



import taktak.entities.Client;

/**
 *
 * @author yasoulanda
 */
public interface IClient {
     public void ajouterClient( Client c );
     public void modifierClient( Client c );
     public void supprimerClient();
     public void afficherClients();
     


}
