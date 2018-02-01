package application;
/**
 * @author Thibaut Masselin
 * @date 05/01/2017
 * @version 1.1
 */
public interface Monnaie {	
	
	public void convertir(Monnaie monnaie);
	
	public double getMonnaie();

	public void setMonnaie(double nombreDonne);

	public String toString();
}
