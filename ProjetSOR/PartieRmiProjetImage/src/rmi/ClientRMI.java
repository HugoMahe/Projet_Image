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

}
