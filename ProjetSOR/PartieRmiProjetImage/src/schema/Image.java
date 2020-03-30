package schema;

public class Image {
	private String titre;
	private byte[] fichier;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public byte[] getMonImage() {
		return fichier;
	}
	public void setMonImage(byte[] monImage) {
		this.fichier = monImage;
	}
	
	public Image(String titre, byte[] monImg) {
		this.titre=titre;
		this.fichier=monImg;
	}
}
