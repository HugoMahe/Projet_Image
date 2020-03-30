package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import schema.Base;
import schema.Image;

/**
 * Implementation de l'interface ServeurRMI
 * @author Hugo Mahé, Léo Mazé
 *
 */
public class ServeurRMIImpl implements ServeurRMI {

	@Override
	public String meth() throws RemoteException {
		// TODO Auto-generated method stub
		return "Reponse du serveur RMI";
	}
	
	@Override
	public boolean ajouterImage(String titre, byte[] bytes) throws RemoteException {
		Base base = new Base();
		base.ouvrir();
		Image img = new Image(titre, bytes);
		base.ajoutImage(img);
		// TODO Auto-generated method stub
		base.fermer();
		return false;
	}
	
	@Override
	public byte[] recupererImage(int idImage) throws RemoteException {
		byte[] image = null;
		Base base = new Base();
		if (base.ouvrir()) {
			image = base.recuperationImage(idImage);
			base.fermer();
		}
		return image;
	}

	@Override
	public Integer trouverImage(String titre) throws RemoteException {
		Integer idImage = null;
		Base base = new Base();
		if (base.ouvrir()) {
			idImage = base.trouverImage(titre);
			base.fermer();
		}
		return idImage;
	}
	
	public static void main(String [] args) {
		
		int port = 10000;
		
		Registry registry = null;
		
		// création registry
		try {
			LocateRegistry.createRegistry(port);
			registry = LocateRegistry.getRegistry(port);
		} catch (RemoteException e) {
			System.out.println("Erreur RMI createRegistry "+e.getMessage());
		}
		
		ServeurRMIImpl srmii = new ServeurRMIImpl();
		ServeurRMI srmi = null;
		
		// création objet distant
		try {
			srmi = (ServeurRMI) UnicastRemoteObject.
					exportObject(srmii,0);
		} catch (RemoteException e) {
			System.out.println("Erreur RMI exportObject "+e.getMessage());
		}
		
		// enregistrement objet distant
		
		try {
			registry.rebind("serveurRMI", srmi);
		} catch (Exception e) {
			System.out.println("Erreur RMI rebind "+
					e.getMessage());			
		}
		
		System.out.println("Serveur RMI lancé");
		
	}
	

}
