package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rmi.ClientRMI;

/**
 * Servlet implementation class AffichageImage
 */
@WebServlet("/ServletChercher")
public class ServletChercher extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletChercher() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DoGet chercher image");
		
		String titre = request.getParameter("titre");
		
		ClientRMI client = new ClientRMI();
		
		Integer idImage = client.trouverImage(titre);

		response.getWriter().append("<html>");
		response.getWriter().append("<head>");
		response.getWriter().append("</head>");
		response.getWriter().append("<body>");
		response.getWriter().append("<h1>" + titre + "</h1>");
		if (idImage == null) {
			response.getWriter().append("<p>Pas d'image à ce titre</p>");
		}
		else {
			response.getWriter().append("<img src=\"ServletImage?id=" + idImage + "\">");
		}
		response.getWriter().append("</body>");
		response.getWriter().append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
