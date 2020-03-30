package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * INTERFACE SERVEUR RMI 
 * @author Hugo Mahé, Léo Mazé
 *
 */
public interface ServeurRMI  extends Remote {

	public String meth() throws RemoteException ;
	
	public boolean ajouterImage(String titre, byte[] TabBytes) throws RemoteException;

	public byte[] recupererImage(int idImage) throws RemoteException;
	
	public Integer trouverImage(String titre) throws RemoteException;
}