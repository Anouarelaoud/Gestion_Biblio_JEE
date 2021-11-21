package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import dao.empruntDao;
import beans.Emprunt;
import beans.Etudiant;
import beans.Livre;

@WebServlet("/Emprunt")
public class empruntController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private empruntDao empruntDao;

	public void init() {
		empruntDao = new empruntDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession ses = request.getSession();
		if (ses.getAttribute("user") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
			dispatcher.forward(request, response);
		}
		String action;
		if (request.getParameter("action") != null)
			action = request.getParameter("action");
		else
			action = "list";
		try {
			switch (action) {
			case "new":
				newEmprunt(request, response);
				break;
			case "insert":
				insertEmprunt(request, response);
				break;
			case "delete":
				deleteEmprunt(request, response);
				break;
			case "edit":
				editEmprunt(request, response);
				break;
			case "update":
				updateEmprunt(request, response);
				break;
			default:
				listEmprunt(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listEmprunt(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Emprunt> listEmprunt = empruntDao.selectAllEmprunts();
		request.setAttribute("listEmprunt", listEmprunt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt/list.jsp");
		dispatcher.forward(request, response);
	}

	private void newEmprunt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Etudiant> etudiants = empruntDao.selectAllEtudiants();
		List<Livre> livres = empruntDao.selectAllLivres();
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("livres", livres);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt/new.jsp");
		dispatcher.forward(request, response);
	}

	private void editEmprunt(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Emprunt existingEmprunt = empruntDao.selectEmprunt(id);
		List<Etudiant> etudiants = empruntDao.selectAllEtudiants();
		List<Livre> livres = empruntDao.selectAllLivres();
		request.setAttribute("etudiants", etudiants);
		request.setAttribute("livres", livres);
		RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt/update.jsp");
		request.setAttribute("emprunt", existingEmprunt);
		dispatcher.forward(request, response);

	}

	private void insertEmprunt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
		int livreId = Integer.parseInt(request.getParameter("livreId"));
		Boolean checkemprunt = empruntDao.checkEmprunt(etudiantId, livreId);
		if (empruntDao.selectEtudiantById(etudiantId).getEmprunt() < 3 && empruntDao.selectLivreById(livreId).getStock() > 0 && checkemprunt == true) {
			Boolean remis;
			if (request.getParameter("remis") == "true")
				remis = true;
			else
				remis = false;
			Emprunt newEmprunt = new Emprunt(etudiantId, livreId, remis);
			empruntDao.insertEmprunt(newEmprunt);
			empruntDao.updateEtudiant(etudiantId, 1);
			empruntDao.updateLivre(livreId, 1);
			response.sendRedirect("Emprunt");
		}
		else {
			List<Etudiant> etudiants = empruntDao.selectAllEtudiants();
			List<Livre> livres = empruntDao.selectAllLivres();
			request.setAttribute("etudiants", etudiants);
			request.setAttribute("livres", livres);
			RequestDispatcher dispatcher = request.getRequestDispatcher("emprunt/new.jsp");
			dispatcher.forward(request, response);
		}
			
	}

	private void updateEmprunt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int etudiantId = Integer.parseInt(request.getParameter("etudiantId"));
		int livreId = Integer.parseInt(request.getParameter("livreId"));
		Boolean remis;
		if (request.getParameter("remis") == "true")
			remis = true;
		else
			remis = false;
		Emprunt updateEmprunt = new Emprunt(id, etudiantId, livreId, remis);
		empruntDao.updateEmprunt(updateEmprunt);
		response.sendRedirect("Emprunt");
	}

	private void deleteEmprunt(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int etudiantId = empruntDao.selectEmprunt(id).getEtudiantId();
		int livreId = empruntDao.selectEmprunt(id).getLivreId();
		empruntDao.deleteEmprunt(id);
		empruntDao.updateEtudiant(etudiantId, 2);
		empruntDao.updateLivre(livreId, 2);
		response.sendRedirect("Emprunt");
	}
}
