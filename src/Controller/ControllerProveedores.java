package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControllerProveedores implements Initializable, IScreenController{
	 ScreenController myController=ScreenController.getInstancia();

	@Override
	public void setScreenParent(ScreenController screenWindows) {
		myController=screenWindows;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	@FXML public void btnMenu(){
		 myController.showScreen(Main.menu);
	 }
}
