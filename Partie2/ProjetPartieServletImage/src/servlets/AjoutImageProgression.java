package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import rmi.ClientRMI;

/**
 * Servlet implementation class AjoutImageProgression
 */
@WebServlet("/AjoutImageProgression")
public class AjoutImageProgression extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int progression=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutImageProgression() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LANCEMENT DO GET");
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("0%");
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("LANCEMENT DO POST");
		String clientOrigin = request.getHeader("origin");
		String titre = request.getParameter("titre");
		System.out.println(titre);
		Part filePart = request.getPart("fichier");
		String nomFichier = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream contenuFichier = filePart.getInputStream();
		if(contenuFichier!=null) {
			ByteArrayOutputStream os= new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int taille;
			while((taille=contenuFichier.read(buffer))!=-1) {
				os.write(buffer,0,taille);
			}
			ClientRMI client = new ClientRMI(os.toByteArray(), titre);
			boolean  res = client.ajouterImage();
			System.out.println("AJOUT DE L'IMAGE");
			if(res) {
				System.out.println("AJOUT REUSSI");
				response.getWriter().append("<h1>" + "ajoutReussi" + "</h1>");
			}else {
				System.out.println("ERREUR DANS l'AJOUT");
				response.getWriter().append("<h1>" + "Erreur dans l'ajout de l'image" + "</h1>");
			}
		}else {
			System.out.println("Le fichier envoyé semble null");
		}
		//doGet(request, response);
		 
	}

}
