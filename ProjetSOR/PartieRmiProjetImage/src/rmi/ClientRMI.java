package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import schema.Image;

/**
 * CLIENT RMI 
 * @author Hugo Mahé, Léo Mazé
 *
 */
public class ClientRMI {
	public static byte[] tabImage=null;
	public static String titre = null;
	
	public ClientRMI(byte[] imageParam,String titre ) {
		this.tabImage=imageParam;
		this.titre=titre;
	}
	
	public static void main(String [] args) {
		System.out.println("Lancement client RMI");
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			String res = srmi.meth();
			boolean resultat = srmi.ajouterImage(titre , tabImage);
			
			System.out.println("res = "+res);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		
	}

}
