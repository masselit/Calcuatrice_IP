package calculatrice;
/**
 * @date 24/09/2016
 * @author MASSELIN Thibaut
 * @version 1.3
 */
public class Octet {
	
	/**
	 * Attribute
	 */
	private int octet;
	
	/**
	 * Constructor
	 */
	public Octet(int oct){
		this.setOctet(oct);
	}
	
	//Method
	/**
	 * test si le byte est comprit entre 0 est 255
	 * tester dans les setters
	 * @param oct
	 * @return boolean
	 */
	private boolean octetValid(int oct){
		boolean temp = false;
		if((oct<=255) && (oct>=0)){
			temp = true;
		}
		return temp;
	}
	
	/**
	 * test si l'octet est possible dans un mask
	 * @param octMask
	 */
	public void maskOctetValid(){
		for(int i =0;i<=8;i++){
			if(this.getOctet() == (256-Math.pow(2, i))){
				break;//arrêt la boucle
			}
			if(i==8){
				//error -------------------------------------
				System.out.println("Error1");
				throw new IllegalStateException("Erreur : octet invalide dans le masque -> "+this.getOctet());
			}
		}
	}
	
	/**
	 * test si la position des octets sont possibles dans un mask
	 * @param oct
	 */
	public void maskPositionValid(Octet oct){
		//octet de poid fort   plus petit    octet de poid faible
		if(this.getOctet() < oct.getOctet()){			
			//error -------------------------------------
			throw new IllegalStateException("Erreur : Masque invalide -> "+oct.getOctet());
			
		}
		//l'octet n'est pas 0 ou 255 le suivant doit être égale à 0
		if(this.getOctet()!= 255 && this.getOctet() != 0){
			if(oct.getOctet() != 0)	{
				//error -------------------------------------
				throw new IllegalStateException("Erreur : Masque invalide -> "+oct.getOctet());
				
			}
			//position valid
		}
	}
	
	//Getter Setter
	public int getOctet() {
		return octet;
	}
	public void setOctet(int oct) {
		if(octetValid(oct)){
			this.octet = oct;
		}else{
			//error -------------------------------------
			throw new IllegalStateException("Erreur : Octet invalide -> "+oct);
		}
	}
}
