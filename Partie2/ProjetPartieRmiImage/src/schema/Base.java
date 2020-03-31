package schema;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class Base {


Connection co = null;
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean ouvrir() {
		System.out.println("Ouverture de la base");
		

		try {
			Class.forName
				("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		boolean res = false;
		
		String url = null;
		String user = null;
		String password = null;
		
		ResourceBundle rb = 
			ResourceBundle.getBundle
				("config/config");
		url = rb.getString("url");
		user = rb.getString("user");
		password = rb.getString("password");
		
		System.out.println("url = "+url);
		System.out.println("user = "+user);
		
		try {
			co = DriverManager.getConnection(url, user, password);
			res = true;
		} catch (SQLException e) {
			System.out.println("erreur getConnection "+
					e.getMessage());
			e.printStackTrace();
		}

		return res;
	}
	public String recuperationImage() {
		return null;
	}
	
	public boolean ajoutImage(String titre, byte[] image){
		String requete=null;
		InputStream is = new ByteArrayInputStream(image);
		try {
			requete = "INSERT INTO `t_image` (`jpeg`, `titre`) VALUES (?,?);";
			PreparedStatement pst = co.prepareStatement(requete);
			// CONFIGURATION DES PARAMS
			pst.setBinaryStream(1, is);
			pst.setString(2, titre);
			pst.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean fermer() {
		System.out.println("Fermeture de la base ");
		try {co.close();}catch (Exception e) {}

		return true;
	}
	
	public static void main(String [] args) {
		Base base = new Base();
		base.ouvrir();	
		base.fermer();
	}
	
	public byte[] recuperationImage(int idImage) {
		byte[] image = null;
		try {
			PreparedStatement ps = co.prepareStatement("SELECT `jpeg` FROM t_image WHERE idImage=?");
			ps.setInt(1, idImage);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				image = rs.getBytes("jpeg");
				System.out.println(image);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public Integer trouverImage(String titre) {
		Integer idImage = null;
		try {
			PreparedStatement ps = co.prepareStatement("SELECT `idImage` FROM t_image WHERE titre=?");
			ps.setString(1, titre);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				idImage = rs.getInt("idImage");
				System.out.println(idImage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idImage;
	}
	
}
