package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * CLIENT RMI 
 * @author Hugo Mahé, Léo Mazé
 *
 */
public class ClientRMI {
	public byte[] tabImage=null;
	public String titre = null;
	
	public ClientRMI(byte[] imageParam,String titre ) {
		this.tabImage=imageParam;
		this.titre=titre;
	}
	
	public byte[] getTabImage() {
		return tabImage;
	}

	public void setTabImage(byte[] tabImage) {
		this.tabImage = tabImage;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public ClientRMI( ) {
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			String res = srmi.meth();
			
			System.out.println("res = "+res);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param idImage
	 * @return
	 */
	public byte[] recupererImage(int idImage) {

		int port = 10000;
		byte[] image = null;

		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");

			String res = srmi.meth();

			System.out.println("res = "+res);
			image = srmi.recupererImage(idImage);
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}

		return image;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean ajouterImage() {
		int port = 10000;
		
		try {
			Registry registry = LocateRegistry.
					getRegistry(port);
			
			ServeurRMI srmi = (ServeurRMI) 
					registry.lookup("serveurRMI");
			
			boolean resultat = srmi.ajouterImage(this.titre , this.tabImage);
			
			System.out.println(resultat);
			return resultat;
		}
		catch (Exception e) {
			System.out.println("Erreur Client RMI "+e.getMessage());
		}
		return false;
		
	}

	/**
	 * 
	 * @param titre
	 * @return
	 */
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
