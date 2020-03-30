package bean;

import java.io.File;

/**
 * Objet qui reprend l'ensemble des attributs d'une image
 * @author Hugo Mahé, Léo Mazé
 *
 */
public class Image {
	private String titreImage;
	private byte[] monImage;
	
	public String getTitreImage() {
		return titreImage;
	}
	public void setTitreImage(String titreImage) {
		this.titreImage = titreImage;
	}
	public byte[] getMonImage() {
		return monImage;
	}
	public void setMonImage(byte[] monImage) {
		this.monImage = monImage;
	}

}
