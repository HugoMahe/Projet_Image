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
}