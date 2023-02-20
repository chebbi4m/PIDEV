/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taktak.interfaces;

/**
 *
 * @author LENOVO THINKPAD E15
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taktak.services.ColisService;
import taktak.entities.Colis;
import taktak.entities.reclamation;
import taktak.services.ReclamationService;


public class Main {
    
    public static void main(String[] args) {
       
//       Colis cls = new Colis ("abcd123", 152, 243, 42, 2, true, "Tunis", 2, 2);
       reclamation rec = new reclamation(3, "colis is here", "Doe Doe", "6969");
 
//       ColisService cs = new ColisService();
       ReclamationService rs = new ReclamationService();
//       cs.ajouterColis(cls);

//        rs.ajouterReclamation(rec);
//        rs.deleteReclamation(1);
//        rs.updateReclamation(rec);
        System.out.println(rs.afficherReclamation());
//        System.out.println(cs.afficherColis());
        
    }
    
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("reclamationFXML.fxml"));
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
