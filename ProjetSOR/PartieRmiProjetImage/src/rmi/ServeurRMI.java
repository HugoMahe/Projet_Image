package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * INTERFACE SERVEUR RMI 
 * @author Hugo Mah�, L�o Maz�
 *
 */
public interface ServeurRMI  extends Remote {

	public String meth() throws RemoteException ;
}