package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.ControllerZoneDeSaisie;

public class MainApp extends Application {
	//declaration des variables pour la fenêtre
		private Stage primaryStage;
		private BorderPane rootLayout;
		
		//création de la fenêtre
		@Override
		public void start(Stage primaryStage) {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Convertisseur De Monnaies");
			initRootLayout();

			showSaisieIpView();
		}
		
		//vue arrière plan
		public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
	            
	            rootLayout = (BorderPane) loader.load();
	            Scene scene = new Scene(rootLayout);
	            
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		//vue premier plan
		public void showSaisieIpView() {
	       try { 
		    	// charger ViewSaisieIp.
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(MainApp.class.getResource("../view/ZoneDeSaisieTP03.fxml"));
		        
		        AnchorPane SaisieView = (AnchorPane) loader.load();
	            rootLayout.setCenter(SaisieView);
	            
	            ControllerZoneDeSaisie controller = loader.getController();
	            controller.setMainApp(this);
	            controller.startNotif();
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
		//Getter
		 public Stage getPrimaryStage() {
	        return primaryStage;
	     }
		 /**
		  * Lancement de l'application
		  * @param args
		  */
		 public static void main(String[] args) {
			launch(args);
		}
}
