package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import dao.etudiantDao;
import beans.Etudiant;

@WebServlet("/Etudiant")
public class etudiantController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private etudiantDao etudiantDao;

	public void init() {
		etudiantDao = new etudiantDao();
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
				newEtudiant(request, response);
				break;
			case "insert":
				insertEtudiant(request, response);
				break;
			case "delete":
				deleteEtudiant(request, response);
				break;
			case "edit":
				editEtudiant(request, response);
				break;
			case "update":
				updateEtudiant(request, response);
				break;
			default:
				listEtudiant(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listEtudiant(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Etudiant> listEtudiant = etudiantDao.selectAllEtudiants();
		request.setAttribute("listEtudiant", listEtudiant);
		RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant/list.jsp");
		dispatcher.forward(request, response);
	}

	private void newEtudiant(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant/new.jsp");
		dispatcher.forward(request, response);
	}

	private void editEtudiant(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Etudiant existingEtudiant = etudiantDao.selectEtudiant(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant/update.jsp");
		request.setAttribute("etudiant", existingEtudiant);
		dispatcher.forward(request, response);

	}

	private void insertEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		String cin = request.getParameter("cin");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String filiere = request.getParameter("filiere");
		
		Etudiant newEtudiant = new Etudiant(cin, nom, prenom, filiere, 0);
		etudiantDao.insertEtudiant(newEtudiant);
		response.sendRedirect("Etudiant");
	}

	private void updateEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String cin = request.getParameter("cin");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String filiere = request.getParameter("filiere");
		int emprunt = Integer.parseInt(request.getParameter("emprunt"));
		Etudiant updateEtudiant = new Etudiant(id, cin, nom, prenom, filiere, emprunt);
		
		etudiantDao.updateEtudiant(updateEtudiant);
		
		response.sendRedirect("Etudiant");
	}

	private void deleteEtudiant(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		etudiantDao.deleteEtudiant(id);
		response.sendRedirect("Etudiant");
	}
}
