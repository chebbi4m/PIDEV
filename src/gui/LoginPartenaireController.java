/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
import Entities.Livreur;
import Utils.MyConnection;
import GUI.HistoriquePartenaireController;
import Services.PartenaireService;
import Session.UserSession;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;


/**
 * FXML Controller class
 *
 * @author yasoulanda
 */
public class LoginPartenaireController implements Initializable {
//    @FXML private JFXButton cl  ;
//    @FXML private JFXButton lv  ;
    @FXML private JFXTextField username; 
    @FXML private JFXPasswordField mdp;
    @FXML private JFXButton loginButton;
    @FXML private JFXButton fermer;
    PartenaireService ps = new PartenaireService ();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
loginButton.setOnAction(e -> {
    try {
        login(e);
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (SQLException ex) {
        Logger.getLogger(LoginClientController.class.getName()).log(Level.SEVERE, null, ex);
    }
});


    }    
    
    public void login(ActionEvent  event) throws IOException, SQLException{
        //String loginName = username.getText();

    // Save the login name in a variable
        // client.setLoginName(loginName);
        
        
         String usernameText = username.getText();
         String mdpText = mdp.getText();
        

             if (usernameText.isEmpty()==false && mdpText.isEmpty()==false) 
              { 
                      Connection myconn = MyConnection.getInstance().getConnexion();
                      String sql = "SELECT count(1) FROM partenaire WHERE login = '"+usernameText+"' AND mdp = '"+mdpText+"'"; //System.out.println(usernameText);
                      try { 
                         Statement stmt = myconn.createStatement();
                         ResultSet rs = stmt.executeQuery(sql);
                         while (rs.next()) 
                         {
                            if( rs.getInt(1) == 1 ){
                                System.out.println(usernameText);
                                UserSession.INSTANCE.put("partenaire",ps.getUserData(usernameText));
                               // System.out.println(ps.getUserData(usernameText)); 
                                Stage stage = new Stage ();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("AccueilPartenaire.fxml"));
                                Parent root = loader.load();
                               // HistoriquePartenaireController controller = loader.getController();
                                // controller.afficherClients (usernameText);
                                Scene scene = new Scene (root);
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.UNDECORATED);
                                stage.show();
                                ((Node)event.getSource()).getScene().getWindow().hide();
                                check();
                            } else
                               {
                                   error();
                               }
                         }
                                                
                      }catch (Exception e){}
                      
                      
                  
                  
              } else{
                  error();
                  }}

    
    public void error (){
    Image image = new Image("Images/cross.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Erreur");
     notification.text("Erreur : Nom d'utilisateur ou mot de passe incorrect ");
     notification.hideAfter(Duration.seconds(3));
     notification.position(Pos.CENTER);
     notification.show();
    }
    
        public void check () throws IOException{
      String usernameText = username.getText();

     Image image = new Image("Images/accept.png");
     Notifications notification = Notifications.create();
     notification.graphic(new ImageView(image));
     notification.title("Succès");
     notification.text("Bienvenue "+ usernameText );
     notification.hideAfter(Duration.seconds(1));
     notification.position(Pos.CENTER);
     notification.show();
    }

        public void cancel (ActionEvent e){

               Stage stage = (Stage) fermer.getScene().getWindow();
               stage.close();
    }
        
        public void creer (ActionEvent e) throws IOException{

          Stage stage = new Stage ();
          Parent root = FXMLLoader.load(getClass().getResource("CreatePartenaire.fxml"));  
          Scene scene = new Scene (root);
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
          ((Node)e.getSource()).getScene().getWindow().hide();
    }
//        
//public String getSession(ActionEvent  event) {
//
//      HttpSession session = ((HttpServletRequest) event.getSource()).getSession(true);
//      //session.setAttribute("username", usernameText);
//
//    return session.getAttribute("username").toString();
//}
        
//        @FXML
//private void handleLogin(ActionEvent event) {
//
//   //username.getText(); 
//    String loggedInUsername = username.getText();
//    
//   
//}
public void loginclient (ActionEvent e) throws IOException{

          Stage stage = new Stage ();
          Parent root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));  
          Scene scene = new Scene (root);
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
          ((Node)e.getSource()).getScene().getWindow().hide();
    }
public void loginlv (ActionEvent e) throws IOException{

          Stage stage = new Stage ();
          Parent root = FXMLLoader.load(getClass().getResource("LoginLivreur.fxml"));  
          Scene scene = new Scene (root);
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
          ((Node)e.getSource()).getScene().getWindow().hide();
    }

public void loginpr (ActionEvent e) throws IOException{

          Stage stage = new Stage ();
          Parent root = FXMLLoader.load(getClass().getResource("LoginPartenaire.fxml"));  
          Scene scene = new Scene (root);
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
          ((Node)e.getSource()).getScene().getWindow().hide();
    }

 public void forgotpassword (ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPartenaire.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
