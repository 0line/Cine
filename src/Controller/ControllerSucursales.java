package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import com.sun.xml.internal.ws.api.Cancelable;

import Model.MSucursales;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class ControllerSucursales implements Initializable, IScreenController{
	@FXML private Button btnNew,btnSave,btnDelete,btnUpdate,btnCancel;
	@FXML private ToggleButton btnEstatus;
	@FXML private TextField txtciudad,txttelefono;
	@FXML private TextArea txtdireccion;
	@FXML private Label lblMensaje;
	@FXML private ComboBox<String> cmbestado;
	@FXML private CheckBox ckbinactivos;
	@FXML private TableView<Model.MSucursales>tvsucursales;
	public ObservableList<String> estados;	
	private ObservableList<Model.MSucursales> listSucursales;
	private MSucursales ms;
	private MSucursales update;
	ScreenController myController;
	public int id;
	
	 public ControllerSucursales() {
		 listSucursales=FXCollections.observableArrayList();
		 myController=ScreenController.getInstancia();	
		 ms=new MSucursales();
		 estados=FXCollections.observableArrayList("Veracruz","Yucatan", "Guerrero","Ciudad de México");	
	}
	 
	@Override
	public void setScreenParent(ScreenController screenWindows) {
		myController=screenWindows;
		
	}

	
	
	public void llenarTabla(Boolean estatus){
		listSucursales.clear();
		if(estatus==true){listSucursales=ms.llenarTabla("select * from sucursales where estatus='a'");
		}
		else{listSucursales=ms.llenarTabla("select * from sucursales where estatus='i'");}
		tvsucursales.setItems(listSucursales);
	}
	
	
	
	@FXML public void btnMenu(){
		 myController.showScreen(Main.menu);
	 }
		
	//Método para activar el boton guardar
	@FXML public void btnNew(){
		btnNew.setDisable(true);
		btnSave.setDisable(false);
		txtciudad.setDisable(false);
		txtdireccion.setDisable(false);
		txttelefono.setDisable(false);
		cmbestado.setDisable(false);
	}
	
	//Método para cancelar
	@FXML public void btnCancel(){
		btnNew.setDisable(false);
		btnSave.setDisable(true);
		txtciudad.setDisable(true);
		txtdireccion.setDisable(true);
		txttelefono.setDisable(true);
		cmbestado.setDisable(true);
		//Limpiar datos
		txtciudad.clear();
		txtdireccion.clear();
		txttelefono.clear();
		cmbestado.setValue(null);
		btnEstatus.setDisable(true);
		btnEstatus.setSelected(true);
		btnEstatus.setVisible(false);
	}
	
	//Método para guardar datos
	@FXML public void btnSave(){
		if(txtdireccion.getText().trim().isEmpty()){
			lblMensaje.setText("Dirreción obligatoria");
			txtdireccion.setFocusTraversable(true);
		}
		else{
			if(txtciudad.getText().trim().isEmpty()){
				lblMensaje.setText("Ciudad obligatoria");
				txtciudad.setFocusTraversable(true);
			}
			else{
				if(txttelefono.getText().trim().isEmpty()){
					lblMensaje.setText("Teléfono obligatorio");
					txttelefono.setFocusTraversable(true);
				}
				else{
					if (cmbestado.getValue()==null) {
						lblMensaje.setText("Estado obligatorio");
						cmbestado.setFocusTraversable(true);
					}
					else{
						Boolean result=ms.insert(new MSucursales(0, txtdireccion.getText(), txtciudad.getText(), cmbestado.getValue(), txttelefono.getText(), "a"));
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
	
	
	
	//Método para ver sucursales activas e inactivas
	@FXML public void seeDisable(){
		listSucursales.clear();
		if(ckbinactivos.isSelected()){
			//Mostrar inactivos
			llenarTabla(false);
		}
		else{
			//Mostrar Activos
			llenarTabla(true);
		}
	}
	
	//Método para seleccionar datos de la tabla
		@FXML public void clickTableView(){
			if (tvsucursales.getSelectionModel().getSelectedItem()!=null) {
				ms=tvsucursales.getSelectionModel().getSelectedItem();
				//Habilitar las cajas de texto
				txtciudad.setDisable(false);
				txtdireccion.setDisable(false);
				txttelefono.setDisable(false);
				cmbestado.setDisable(false);
				//Habilitar los botones para modificar y eliminar
				btnUpdate.setDisable(false);
				btnDelete.setDisable(false);
				//Desabilitar el nuevo
				btnNew.setDisable(true);
				//Cargar datos para modificar
				id=ms.getId_sucursal();
				txtciudad.setText(ms.getCiudad());
				txtdireccion.setText(ms.getDireccion());
				txttelefono.setText(ms.getTelefono());
				cmbestado.setValue(ms.getEstado());
				/*String estatus=ms.getEstatus();
				System.out.println(estatus);
				if(estatus=="i".trim()){
					System.out.println(estatus+"1");
					btnEstatus.setVisible(true);
				}
				else{
					System.out.println(estatus+"2");
					btnEstatus.setVisible(false);
				}*/
			}
			
		}
		
		/*@FXML public void clickActivar(boolean b){
			if(b != false){
				btnEstatus.setVisible(true);
				btnEstatus.setText("Activo");
				btnEstatus.getStyleClass().remove("btnInactivo");
				btnEstatus.getStyleClass().add("btnActivo");
			}
			else{
				btnEstatus.setVisible(true);
				btnEstatus.setText("Desactivo");
				btnEstatus.getStyleClass().remove("btnActivo");
				btnEstatus.getStyleClass().add("btnInactivo");
			}
			
		}*/
		
		@FXML public void btnDelete(){
			if (id != 0) {
				Boolean result= ms.delete(id);
				if(result==true){
					lblMensaje.setText("Se ha eliminado la categoria");
					llenarTabla(true);
					//Habilitar botones
					btnCancel();
				}
				else{
					lblMensaje.setText("Ha ocurrido un error,consultar a tu proveedor de servicios");
				}
			} else {
				lblMensaje.setText("No ha seleccionado una categoría");
			}
		}
	
		@FXML public void btnUpdate(){
			if (id != 0) {
				update.setId_sucursal(id);
				update.setDireccion(txtdireccion.getText());
				update.setCiudad(txtciudad.getText());
				update.setEstado(cmbestado.getValue());
				update.setTelefono(txttelefono.getText());
				update.setEstatus(btnEstatus.getText());
				Boolean result= ms.update(update);
				System.out.println(result);
				if (result==true) {
					lblMensaje.setText("Se han actualizado los datos correctamente");
					llenarTabla(true);
					btnCancel();
				}
			} else {
				lblMensaje.setText("No ha seleccionado una sucursal");
			}
		}
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbestado.setItems(estados);
		llenarTabla(true);
	}
}
