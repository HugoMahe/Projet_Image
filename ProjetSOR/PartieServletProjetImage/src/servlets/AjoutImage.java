package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Image;
import rmi.ClientRMI;
/**
 * Servlet implementation class AjoutImage
 */
@WebServlet("/AjoutImage")
@MultipartConfig
public class AjoutImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AjoutImage() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LANCEMENT DO GET");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LANCEMENT DO POST");
		String titre = request.getParameter("titre");
		//Image image = new ObjectMapper().readValue(request.getReader(), Image.class);
		//String titre = image.getTitre();
		System.out.println("TITRE RECUP :" + titre);
		Part filePart = request.getPart("fichier");
		String nomFichier = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		System.out.println(nomFichier);
		InputStream contenuFichier = filePart.getInputStream();
		//LECTURE DES DONNEES DU FICHIER
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while((nRead = contenuFichier.read(data, 0 , data.length))!= -1) {
			buffer.write(data,0,nRead);
		}
		buffer.flush();
		byte[] byteArray = buffer.toByteArray();
		contenuFichier.close();
		Image img = new Image(titre, byteArray);
		ClientRMI client = new ClientRMI(byteArray, titre);
		client.main(null);
		doGet(request, response);
	}

}
