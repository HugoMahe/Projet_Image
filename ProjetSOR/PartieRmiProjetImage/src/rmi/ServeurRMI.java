package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import schema.Image;

/**
 * INTERFACE SERVEUR RMI 
 * @author Hugo Mah�, L�o Maz�
 *
 */
public interface ServeurRMI  extends Remote {

	public String meth() throws RemoteException ;
	
	public boolean ajouterImage(String titre, byte[] TabBytes) throws RemoteException;
}