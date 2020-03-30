package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * CLIENT RMI 
 * @author Hugo Mahé, Léo Mazé
 *
 */
public class ClientRMI {
	public static void main(String [] args) {
		ClientRMI client = new ClientRMI();

		client.trouverImage("pipou");
		client.recupererImage(1);
	}

	public byte[] recupererImage(int idImage) {
		
		int port = 10000;
		byte[] image = null;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			image = srmi.recupererImage(idImage);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		return image;
	}

	public Integer trouverImage(String titre) {
		
		int port = 10000;
		Integer idImage = null;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			idImage = srmi.trouverImage(titre);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		return idImage;
	}
}
