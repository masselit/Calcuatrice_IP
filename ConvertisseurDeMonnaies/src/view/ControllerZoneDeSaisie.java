package view;
import javafx.application.Platform;
/**
 * @author Thibaut Masselin
 * @date 05/01/2017
 * @version 1.3
 */
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import application.Dollar;
import application.Euro;
import application.MainApp;
import application.Monnaie;
import application.TauxDeChange;

public class ControllerZoneDeSaisie {
	
	private MainApp mainApp;
	private String msgErr = "";
	private String infoSurLeTAux;
	private Monnaie mEuro = new Euro();
	private Monnaie mDollar = new Dollar();
	private Thread t;
	
	@FXML
    private TextField saisieEuro;
	
	@FXML
    private TextField saisieDollar;
	
	@FXML
    private Label infoTauxDeChange;
	
	
	
	//Methods
	@FXML
	public void startNotif(){
		t = new Thread(new TauxDeChange(this));
		t.start();
	}
	
	public void setInfoTaux(String str){
		this.infoSurLeTAux = str;
		Platform.runLater(() ->affiche());
	}
	
	private void affiche() {
		infoTauxDeChange.setText(infoSurLeTAux);
	}

	@FXML
	private void handleDollar() {
		if(isValuesValide(saisieDollar)){			
			mDollar.setMonnaie(doubleFormat(saisieDollar.getText().trim()));
			mEuro.convertir(mDollar);
			saisieEuro.setText(mEuro.toString());
		}
	}
	
	@FXML
	private void handleEuro() {
		if(isValuesValide(saisieEuro)){			
			mEuro.setMonnaie(doubleFormat(saisieEuro.getText().trim()));
			mDollar.convertir(mEuro);
			saisieDollar.setText(mDollar.toString());
		}
	}
	
	
	
	private boolean isValuesValide(TextField imput){
		boolean errorMessage = true;
		errorMessage = textSaisieValide(imput);
		if (!errorMessage) {
        	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(msgErr); 
            alert.showAndWait();  
            msgErr ="";
        } 
		return errorMessage;
	}
	
	/**
	 * Vérifie que la valeur données est bien un positif
	 * @param imput
	 * @return boolean
	 */
	private boolean textSaisieValide(TextField imput){
    	boolean flag = true;
    	if(doubleFormat(imput.getText()) < 0){
    		if(msgErr.equals(""))
    			msgErr += "nombre négatif";
    		imput.setStyle("-fx-border-color: red");
			flag = false;
		}else{
			saisieEuro.setStyle("-fx-border-color: none");
			saisieDollar.setStyle("-fx-border-color: none");
		}
    	return flag;
    }
	
	private double doubleFormat(String str){
		str = str.replaceAll(",", ".");
		double temp = -1;
		try{
			temp = Double.parseDouble(str);
		}catch(NumberFormatException e){
			msgErr += "Merci de rentrer un nombre\n";
		}
		return temp;
	}
	
	public void setMainApp(MainApp mainApp2) {
		this.mainApp = mainApp2;	
	}	
}
