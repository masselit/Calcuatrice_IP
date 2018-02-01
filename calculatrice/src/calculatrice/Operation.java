package calculatrice;
/**
 * @date 24/09/2016
 * @author MASSELIN Thibaut
 * @version 1.3
 */
public class Operation {
	/**
	 * Constructor
	 */
	public Operation(){}
	
	/**
	 * Exécute un "et logique" entre l'@ip et le mask
	 * @param maskIp
	 * @return boolean
	 */
	public double etLogique(AdresseIp ip,AdresseIp maskIp){
		double temp = 0;
		//On compart le mask par rapport à l'ip
		for(int i = 0;i<4;i++)
			temp += concat(i,(ip.getLesOctets().get(i).getOctet() & maskIp.getLesOctets().get(i).getOctet()));
		return temp;
	}
	
	/**
	 * La concaténation d'une @ ip
	 * @param i est la position de l'octet
	 * @param octet
	 * @return double
	 */
	private double concat(int i,int oct){
		return Math.pow(2,(24-(8*i)))* oct;
	}
	
	/**
	 * Permet la comparaison entre les deux @ Ip Résaeu
	 * @param ipR1
	 * @param ipR2
	 * @return boolean
	 */
	public boolean comparReseauIp(double ipR1,double ipR2){
		boolean flag = false;
		if(ipR1==ipR2){
			flag = true;
		}
		return flag;
	}
}
