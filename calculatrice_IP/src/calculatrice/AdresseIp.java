package calculatrice;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 24/09/2016
 * @author MASSELIN Thibaut
 * @version 1.3
 */
public class AdresseIp {
	
	/**
	 * Attribute
	 */
	//taille limité à 4 objects
	private List<Octet> lesOctets;

	
	/**
	 * Constructor
	 */
	public AdresseIp(){
		lesOctets= new ArrayList<>(4);
	}
	/**
	 * création de l'adresse IP via 4 int
	 * @param oct1 : octet n°1
	 * @param oct2 : octet n°2
	 * @param oct3 : octet n°3
	 * @param oct4 : octet n°4
	 */
	public void octetAdresseIp(int oct1,int oct2, int oct3, int oct4){

		Octet temp = new Octet(oct1);
		this.addOctet(temp);
		
		temp = new Octet(oct2);
		this.addOctet(temp);
		
		temp = new Octet(oct3);
		this.addOctet(temp);
		
		temp = new Octet(oct4);
		this.addOctet(temp);
		
	}	
	/**
	 * création de l'adresse IP via 1 String
	 * @param ip
	 */
	public void stringAdresseIp(String ip){
		Octet temp;
		String tabip[];
		try{
			for(int i=0;i<4;i++){
				tabip = ip.split("\\.");
				temp = new Octet(Integer.parseInt(tabip[i].trim()));			
				this.addOctet(temp);
				
			}
		}catch(java.lang.RuntimeException e){
			throw new IllegalStateException("Erreur : Adresse Ip invalide -> '"+ ip.toString() +"'");
		}
	}
	//Methods
	
	/**
	 * Vérifie si le mask est Conforme
	 */
	public void maskValid(){
		int i = 1;
		//parcout la list		
		for (Octet oct: this.getLesOctets()){
			oct.maskOctetValid();
			//On ne peut pas comparer le 4eme octet avec rien
			if(i!=4){
				oct.maskPositionValid(this.getLesOctets().get(i));
				i++;
			}
		}
	}
	
	/**
	 * Ajoute un octet à la list
	 * @param oct
	 */
	private void addOctet(Octet oct){
		lesOctets.add(oct);
	}
	
	//Getter Setter
	public List<Octet> getLesOctets() {
		return lesOctets;
	}
}

