package rmi;


import schema.Base;
import schema.Image;

public class TestAccesBase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Image img = new Image("Testeur", null);
		Base base = new Base();
		base.ouvrir();
		base.ajoutImage(img);
		base.fermer();

	}

}
