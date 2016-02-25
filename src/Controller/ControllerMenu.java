package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerMenu implements Initializable, IScreenController{
 ScreenController myController=ScreenController.getInstancia();
 
 @Override
 public void initialize(URL location, ResourceBundle resources) {
 	// TODO Auto-generated method stub
 	
 }
 
 public void setScreenParent(ScreenController screenWindows){
	 myController=screenWindows;
 }
 
 @FXML public void btnSucursales(){
	 myController.showScreen(Main.sucursales);
 }
 
 @FXML public void btnProveedores(){
	 myController.showScreen(Main.proveedores);
 }
 @FXML public void btnClientes(){
	 myController.showScreen(Main.clientes);
 }
}
