package calculatrice;

import java.io.IOException;
import calculatrice.view.ControllerSaisieIp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @date 24/09/2016
 * @author MASSELIN Thibaut
 * @version 1.3
 */

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Calculatrice Binaire");
		initRootLayout();

		showSaisieIpView();
	}
	public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void showSaisieIpView() {
       try { 
	    	// charger ViewSaisieIp.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/ViewSaisieIp.fxml"));
	        
	        AnchorPane SaisieView = (AnchorPane) loader.load();
            rootLayout.setCenter(SaisieView);
            
            ControllerSaisieIp controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Getter
	 public Stage getPrimaryStage() {
        return primaryStage;
     }
	 /**
	  * Début de l'app
	  * @param args
	  */
	 public static void main(String[] args) {
		launch(args);
	}
}
