package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.ModelLogin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ControllerMenu implements Initializable, IScreenController{
 ScreenController myController=ScreenController.getInstancia();
 @FXML Pane login;
 @FXML TextField txtuser;
 @FXML PasswordField txtpassword;
 @FXML Label lblMensaje,lbluser;
 @FXML Button btnLogin,btnExit,btnSucursales,btnProveedores,btnClientes;
 private ModelLogin loginuser;
 
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
 @FXML public void btnLogin(){
	 login.setVisible(true);
 }
 @FXML public void btnSalir(){
	 System.exit(0);
 }

 @FXML public void btnEntrar(){
	 
	 txtpassword.getText();
	 if(txtuser.getText().trim().isEmpty()){
		 lblMensaje.setText("El usuario es necesario");
		 txtuser.setFocusTraversable(true);
	 }
	 else{
		 if(txtpassword.getText().trim().isEmpty()){
			 lblMensaje.setText("El usuario es necesario");
			 txtpassword.setFocusTraversable(true);
		 }
		 else{
			loginuser=new ModelLogin();
			loginuser.setUser(txtuser.getText());
			loginuser.setPassword(txtpassword.getText());
			loginuser=loginuser.Login(loginuser);
			if(loginuser==null){
				lblMensaje.setVisible(true);
				lblMensaje.setText("Credenciales no válidas");
				txtuser.clear();
				txtpassword.clear();
				login.setDisable(false);
			}
			else{
				lbluser.setDisable(false);
				lbluser.setVisible(true);
				lbluser.setText("Bienvenido"+ " " +loginuser.getUser());
				btnLogin.setDisable(true);
				login.setDisable(true);
				btnSucursales.setDisable(false);
				btnProveedores.setDisable(false);
				btnClientes.setDisable(false);
			}
		 }
	 }
 }
}
