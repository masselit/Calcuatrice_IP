package application;
/**
 * @author Thibaut Masselin
 * @date 05/01/2017
 * @version 1.2
 */
public class Euro implements Monnaie {
	//Attributes
	private double nombreDonne;
	
	//Constructor
	public Euro(){
		this.nombreDonne = 0;
	}
	
	//Methods
	@Override
	public void convertir(Monnaie monnaie) {
		this.nombreDonne = (monnaie.getMonnaie() * TauxDeChange.getTauxDeChangeEuro())/1;
	}

	//Getter - Setter
	@Override
	public double getMonnaie() {
		return this.nombreDonne;
	}

	@Override
	public void setMonnaie(double nombreDonne) {
		this.nombreDonne = nombreDonne;
	}
	
	@Override
	public String toString(){
		return String.valueOf(nombreDonne);
	}
}
