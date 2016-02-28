package Controller;

import java.util.HashMap;

import com.sun.glass.ui.Screen;
import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.javafx.scene.control.SizeLimitedList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class ScreenController extends BorderPane {
	//Esta linea crea como un interprete o diccionario
		private HashMap<String, Node>Screens=new HashMap<>();
		private static ScreenController instancia;
		private Stage primaryStage;
		//Contructor de la clase
		private ScreenController(){
			super();
		}
		
		public static ScreenController getInstancia(){
			if(instancia==null){
				instancia= new ScreenController();
			}
			return instancia;
		}
		
		//Agregamos la ventana al diccionario
		//Recibiendo por parametros el nombre de la ventana y su ubicación o llamodo de
		//forma el nodo
		public void addWindows(String windowsname,Node screenFXML){
			Screens.put(windowsname, screenFXML);
		}
		
		//Optenomos la direccion del nodo
		public Node getWindows(String windowsoption){
			return Screens.get(windowsoption);
		}
		
		//Cargamos la ventana al espacio en blanco
		public Boolean loadScreen(String windowsname, String root){
			try {
				FXMLLoader myLoader=new FXMLLoader(getClass().getResource(root));
				Parent windowLoad=(Parent)myLoader.load();
//				primaryStage.setMaxWidth(windowLoad.minWidth(getPrefWidth()));
//				primaryStage.setWidth(windowLoad.minWidth(getPrefWidth()));
//				primaryStage.setHeight(windowLoad.minHeight(USE_COMPUTED_SIZE));
				//primaryStage.maxWidthProperty(windowLoad.maxWidth(USE_COMPUTED_SIZE));
				addWindows(windowsname, windowLoad);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("option=>"+windowsname+"<==>");
				return false;
			}
		}
		
		//Descargamos la ventana cargada para cargar otra nueva

		public boolean download(String windowsoption){
			if(Screens.remove(windowsoption)==null){
				System.out.println("No exites la ventana");
				return false;
			}
			else{
				return true;
			}
		}
		//Aqui activamos la vista de la interfaz
		public boolean showScreen(final String windowsoption)
		{
			//System.out.println(Screens.get(windowsoption)+ "===");
			if(Screens.get(windowsoption)!=null)
			{
				final DoubleProperty opacity = opacityProperty();
				if(!getChildren().isEmpty())
				{
					System.out.println("ingreso");
				Timeline time=new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>(){
							
							public void handle(ActionEvent event)
							{
								getChildren().remove(0);
								getChildren().add(0,Screens.get(windowsoption));
								Timeline input= new Timeline(
										new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
										new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
								input.play();
							}	
						}, new KeyValue(opacity, 0.0)));
						time.play();
				}else{
					setOpacity(0.0);
					getChildren().add(Screens.get(windowsoption));
					Timeline input=new Timeline(
							new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
							new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
					input.play();
				}
				return true;
			}else{
				System.out.println("No se puede cargar el archivo");
				return false;
			}
		}

		public void setPrimaryStage(Stage primaryStage) {
			this.primaryStage=primaryStage;
			
		}
	}