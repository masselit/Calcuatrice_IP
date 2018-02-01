package application;
/**
 * @author Thibaut Masselin
 * @date 05/01/2017
 * @version 1.3
 */
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.ControllerZoneDeSaisie;

public class TauxDeChange implements Runnable{
	//Attribute
	private static double tauxDeChangeEuro;
	private final static double TAUX_PAR_DEFAUT = 0.942886;
	private ControllerZoneDeSaisie controllerZDS;
	//Constructor
	public TauxDeChange(ControllerZoneDeSaisie controllerZDS){
		this.controllerZDS = controllerZDS;
	}
	
	//Method
	
	@Override
	public void run() {
		controllerZDS.setInfoTaux(enEuro());		
	}
	
	/**
	 * étape 1 : Je vais rechercher sur internet le site web grâce à URL
	 * étape 2 : Je récupère tout le code source de la page web
	 * étape 3 : j'utilise un robot (Pattern) qui me permet de 
	 * 			 faire une recherche dans le code source de la page web
	 * étape 4 : je fait deux recherches { la 1re pour me placer "uccResultAmount'>" 
	 * 			 et la deuxèmes pour récupérer ce qui m'intéresse }
	 */
	public String enEuro(){
		String infotdce = "", a="";	
		try{					
			URL url = new URL("http://www.xe.com/fr/currencyconverter/convert/?Amount=1&From=USD&To=EUR");			  
			URLConnection con=url.openConnection();
			InputStream input = con.getInputStream();			
			while(input.available()>0)
				a += (char)input.read();		
			Pattern p = Pattern.compile("(uccResultAmount'>)(........)");//(uccResultAmount'>)->m.group(1)  (........)->m.group(2)
			Matcher m = p.matcher(a);
			if(m.find()){
				System.out.println("Taux de change : 1 $ = " + m.group(2) +"€");
				this.setTauxDeChangeEuro(doubleFormat(m.group(2)));
			}
			infotdce = "Taux de change actualisé";
		}catch(MalformedURLException e){
			this.setTauxDeChangeEuro(TAUX_PAR_DEFAUT);
			infotdce = "Taux de change non actuelle";
		}catch(IOException e1){
			this.setTauxDeChangeEuro(TAUX_PAR_DEFAUT);
			infotdce = "Taux de change non actuelle";
		}
		return infotdce;
	}
	/**
	 * étape 5 : je formate le string (le "," -> ".") et je le transforme en double
	 * @param str
	 */
	private double doubleFormat(String str){
		str = str.replaceAll(",", ".");
		return Double.parseDouble(str);
	}
	//Getter - Setter
	public static double getTauxDeChangeEuro() {
		return tauxDeChangeEuro;
	}

	private void setTauxDeChangeEuro(double tauxDeChangeEuro) {
		this.tauxDeChangeEuro = tauxDeChangeEuro;
	}
	
}
