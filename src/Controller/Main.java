package Controller;
	
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {	
	//Declaramos nombres y links para las interfaces
	public static String menu="Menú";
	public static String urlMenu="../Views/fxml/menu.fxml";
	public static String sucursales="Sucursales";
	public static String urlSucursales="../Views/fxml/sucursales.fxml";
	public static String proveedores="Proveedores";
	public static String urlProveedores="../Views/fxml/proveedores.fxml";
	public static String clientes="Clientes";
	public static String urlclientes="../Views/fxml/clientes.fxml";
	@Override
	public void start(Stage primaryStage) {
		//Enviamos los datos para cargar las ventanas
		ScreenController mainContainer= ScreenController.getInstancia();
		mainContainer.setPrimaryStage(primaryStage);
		mainContainer.loadScreen(Main.menu, Main.urlMenu);
		mainContainer.loadScreen(Main.sucursales, Main.urlSucursales);
		mainContainer.loadScreen(Main.proveedores, Main.urlProveedores);
		mainContainer.loadScreen(Main.clientes, Main.urlclientes);
		mainContainer.showScreen(Main.menu);
		Group root= new Group();
		//root.setAutoSizeChildren(true);
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("../Views/css/app.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
