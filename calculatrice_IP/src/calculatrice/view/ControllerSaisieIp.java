package calculatrice.view;

import calculatrice.AdresseIp;
import calculatrice.MainApp;
import calculatrice.Operation;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @date 24/09/2016
 * @author MASSELIN Thibaut
 * @version 1.3
 */

public class ControllerSaisieIp {
	//Attribute
	private MainApp mainApp;
	
	@FXML
	private RadioButton octBtn;
	@FXML
	private RadioButton ipBtn;
	
	//Ip1
	@FXML
    private TextField txIp1Oct1;
    @FXML
    private TextField txIp1Oct2;
    @FXML
    private TextField txIp1Oct3;
    @FXML
    private TextField txIp1Oct4;
    
    @FXML
	private Pane octPane;
    @FXML
    private TextField ip1String;
    
    //Ip2
    @FXML
    private TextField txIp2Oct1;
    @FXML
    private TextField txIp2Oct2;
    @FXML
    private TextField txIp2Oct3;
    @FXML
    private TextField txIp2Oct4;
    
    @FXML
	private Pane ipPane;
    @FXML
    private TextField ip2String;
    
    //Masque de sous réseau
    @FXML
    private TextField txMaskOct1;
    @FXML
    private TextField txMaskOct2;
    @FXML
    private TextField txMaskOct3;
    @FXML
    private TextField txMaskOct4;
    
    @FXML
    private TextField ipMasqueString;
    
    
    //Method présent sur l'intreface
    /**
     * Echange de vue
     */
    @FXML
	private void selectBtView(){
    	octPane.setVisible(octBtn.isSelected());
    	ipPane.setVisible(ipBtn.isSelected());
    }
    
    /**
     * Bouton fermeture de l'application
     */
    @FXML
	private void handleFermer() {
    	mainApp.getPrimaryStage().close();
    }

    /**
     * Bouton valider
     * @require isValuesValide : Si les champs ne sont pas vide
     */
	@FXML
	private void handleValider() {
    	
    	if(isValuesValide()){
    		
	    	String errorMessage = "";
	    	AdresseIp ip1 = new AdresseIp(), 
	    			  ip2 = new AdresseIp(), 
	    			  ipMask = new AdresseIp();
	    	try{
	    		// cas par octet
		    	if(octBtn.isSelected()){			    	
		    		ip1.octetAdresseIp(Integer.parseInt(txIp1Oct1.getText().trim()),
		    						   Integer.parseInt(txIp1Oct2.getText().trim()),
		    						   Integer.parseInt(txIp1Oct3.getText().trim()),
		    						   Integer.parseInt(txIp1Oct4.getText().trim()));	
	
		    		ip2.octetAdresseIp(Integer.parseInt(txIp2Oct1.getText().trim()),
		    						   Integer.parseInt(txIp2Oct2.getText().trim()),
		    						   Integer.parseInt(txIp2Oct3.getText().trim()),
		    						   Integer.parseInt(txIp2Oct4.getText().trim()));	
	
		    		ipMask.octetAdresseIp(Integer.parseInt(txMaskOct1.getText().trim()),
		    							  Integer.parseInt(txMaskOct2.getText().trim()),
		    							  Integer.parseInt(txMaskOct3.getText().trim()),
		    							  Integer.parseInt(txMaskOct4.getText().trim()));
		    		ipMask.maskValid();
		    	}
		    	// cas par adresse de 4 octet
		    	else{
		    		saisieError(ip1,ip1String);
		    		saisieError(ip2,ip2String);
		    		saisieError(ipMask,ipMasqueString);
		    		ipMask.maskValid();
		    	}
	    	}catch(java.lang.NumberFormatException | IllegalStateException e){
				//récupération et concaténation des erreurs à la volé
				errorMessage += e.getMessage()+"\n";
			}
	    	resultOperation(errorMessage,ip1,ip2,ipMask);
	    	suppressionObjetCourant();
    	}
    }
	
	//Method
	/**
	 * Color en rouge la zone de saisie invalide
	 * @param ip : adresse IP
	 * @param zoneSaisie
	 */
	private void saisieError(AdresseIp ip,TextField zoneSaisie){
		try {
			ip.stringAdresseIp(zoneSaisie.getText().trim());
			zoneSaisie.setStyle("-fx-border-color: none");
		}catch(IllegalStateException e){
			zoneSaisie.setStyle("-fx-border-color: red");
			throw new IllegalStateException(e.getMessage());
		}
	}
	
	/** 
	 * Exécution du 'Et Logique' et comparison
	 * @param errorMessage
	 * @param ip1 : adresse IP numéro 1
	 * @param ip2 : adresse IP numéro 2
	 * @param mask : masque du réseau
	 */
	private void resultOperation(String errorMessage,AdresseIp ip1,AdresseIp ip2,AdresseIp mask){
		
		if (errorMessage.length() == 0) { 
			//aucun message d'erreur
			Operation op = new Operation();
    		double r1 = op.etLogique(ip1, mask);
    		double r2 = op.etLogique(ip2, mask); 
    		
    		Alert alert = new Alert(AlertType.ERROR);
	    	String msg = "Ils ne sont pas sur le même réseau";  
	    	// r1 == r2
    		if(op.comparReseauIp(r1, r2)){
            	msg = "Ils sont sur le même réseau";	            	
            	alert = new Alert(AlertType.CONFIRMATION);
            }
            alert.setTitle("Résultat Calculatrice Binaire");
            alert.setHeaderText("Résultat de l'Opération :");
            alert.setContentText(msg);          
            alert.showAndWait();
            
    	}else{
    		//errorMessage contient au moins une erreur
    		Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText(errorMessage);          
            alert.showAndWait();
            errorMessage="";
        }
	}
	
	/**
     * Color en rouge les champs vide
     * @param imput
     * @return boolean
     */
    private boolean textSaisieValide(TextField imput){
    	boolean flag = true;
    	if(imput.getText() == null || imput.getText().trim().length() == 0 ){
    		imput.setStyle("-fx-border-color: red");
			flag = false;
		}else{
			imput.setStyle("-fx-border-color: none");
		}
    	return flag;
    }
    
    /**
     * Test si les champs sont bien remplit
     * @return boolean
     */
	private boolean isValuesValide(){
		boolean errorMessage = true;
		//Pour les champs String
		if(ipBtn.isSelected()){
			errorMessage = textSaisieValide(ip1String);
			errorMessage = textSaisieValide(ip2String);
			errorMessage = textSaisieValide(ipMasqueString);
		}
		//Pour les champs int
		if(octBtn.isSelected()){
			//Pour @IP1
			errorMessage = textSaisieValide(txIp1Oct1);
			errorMessage = textSaisieValide(txIp1Oct2);
			errorMessage = textSaisieValide(txIp1Oct3);
			errorMessage = textSaisieValide(txIp1Oct4);
			
			//Pour @ IP2
			errorMessage = textSaisieValide(txIp2Oct1);
			errorMessage = textSaisieValide(txIp2Oct2);
			errorMessage = textSaisieValide(txIp2Oct3);
			errorMessage = textSaisieValide(txIp2Oct4);
			
			//Pour masque
			errorMessage = textSaisieValide(txMaskOct1);
			errorMessage = textSaisieValide(txMaskOct2);
			errorMessage = textSaisieValide(txMaskOct3);
			errorMessage = textSaisieValide(txMaskOct4);
		}
		//Quand il y a une erreur
		if (!errorMessage) {
        	Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText("Merci de remplir les champs"); 
            alert.showAndWait();  
        } 
		return errorMessage;
	}
	
	/**
	 *supprime les objets existants dans la mémoire
	 */
	private void suppressionObjetCourant(){
		System.gc();
	}
	
    
    /**
     * Setter du main
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
    	octBtn.setSelected(true);
    	selectBtView();
    	this.mainApp = mainApp;
    }
}