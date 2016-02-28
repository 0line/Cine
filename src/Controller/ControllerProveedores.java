package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.MProveedores;
import Model.MSucursales;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class ControllerProveedores implements Initializable, IScreenController{
	@FXML private Button btnNew,btnSave,btnDelete,btnUpdate,btnCancel;
	@FXML private ToggleButton btnEstatus;
	@FXML private TextField txtrz,txtname,txtapellidos,txttel,txtcorreo,txtcp,txtcity;
	@FXML private TextArea txtdir;
	@FXML private Label lblMensaje;
	@FXML private CheckBox ckbinactivos;
	@FXML private TableView<Model.MProveedores>tvproveedores;
	private ObservableList<Model.MProveedores> listclientes;
	 ScreenController myController=ScreenController.getInstancia();
	 private MProveedores ms;
	 
	public ControllerProveedores() {
		ms=new MProveedores();
	} 
	 
	@Override
	public void setScreenParent(ScreenController screenWindows) {
		myController=screenWindows;
		
	}
	
	public void llenarTabla(Boolean estatus){
		listclientes.clear();
		if(estatus==true){listclientes=ms.llenarTabla("select * from proveedor where estatus='1'");
		}
		else{listclientes=ms.llenarTabla("select * from proveedor where estatus='0'");}
		tvproveedores.setItems(listclientes);
	}
	
	
	@FXML public void btnMenu(){
		 myController.showScreen(Main.menu);
	 }
	
	
	//Método para activar el boton guardar
		@FXML public void btnNew(){
			btnNew.setDisable(true);
			btnSave.setDisable(false);
			txtrz.setDisable(false);
			txtname.setDisable(false);
			txtapellidos.setDisable(false);
			txttel.setDisable(false);
			txtcorreo.setDisable(false);
			txtcp.setDisable(false);
			txtcity.setDisable(false);
			txtdir.setDisable(false);
		}
		
		//Método para cancelar
		@FXML public void btnCancel(){
			btnNew.setDisable(false);
			btnSave.setDisable(true);
			btnEstatus.setDisable(true);
			btnEstatus.setSelected(true);
			btnEstatus.setVisible(false);
			txtrz.setDisable(true);
			txtname.setDisable(true);
			txtapellidos.setDisable(true);
			txttel.setDisable(true);
			txtcorreo.setDisable(true);
			txtcp.setDisable(true);
			txtcity.setDisable(true);
			txtdir.setDisable(true);

			txtrz.clear();
			txtname.clear();
			txtapellidos.clear();
			txttel.clear();
			txtcorreo.clear();
			txtcp.clear();
			txtcity.clear();
			txtdir.clear();
		}
		
		//Método para guardar datos
		@FXML public void btnSave(){
			if(txtrz.getText().isEmpty()){
				lblMensaje.setText("Razón Social obligatoria");
				txtrz.isFocused();
				
			}
			else{
				if (txtname.getText().isEmpty()) {
					lblMensaje.setText("Dirreción obligatoria");
					txtname.isFocused();
				}
				else{
					if (txtapellidos.getText().isEmpty()) {
						lblMensaje.setText("Apellidos importantes");
						txtapellidos.isFocused();
					}
					else{
						if (txttel.getText().isEmpty()) {
							lblMensaje.setText("Teléfono obligatorio");
						}
						else{
							if (txtcorreo.getText().isEmpty()) {
								lblMensaje.setText("Correo obligatorio");
							}
							else{
								if (txtdir.getText().isEmpty()) {
									lblMensaje.setText("Direccion obligatoria");
								}
								else{
									if (txtcity.getText().isEmpty()) {
										lblMensaje.setText("Ciudad obligatoria");
									}
									else{
										if (txtcp.getText().isEmpty()) {
											lblMensaje.setText("C.P obligatoria");
										}
										else{
											Boolean result=ms.insert(new MProveedores(0,txtrz.getText(),txtname.getText(),txtapellidos.getText(),txttel.getText(),txtcorreo.getText(),txtdir.getText(),txtcity.getText(),Integer.parseInt(txtcp.getText())));
											if(result){
												lblMensaje.setText("Los datos se han "
														+ "insertado satisfactoriamente");
												llenarTabla(true);
											}
											else{
												lblMensaje.setText("Ha Ocurrido un"
														+ " error consulte a su administrador");
											}
										}
									}
								}
							}
						}
					}
				}	
			}
		}
		
		//Método para ver sucursales activas e inactivas
		@FXML public void seeDisable(){
			listclientes.clear();
			if(ckbinactivos.isSelected()){
				//Mostrar inactivos
				llenarTabla(false);
			}
			else{
				//Mostrar Activos
				llenarTabla(true);
			}
		}
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			llenarTabla(true);
				
		}
}

