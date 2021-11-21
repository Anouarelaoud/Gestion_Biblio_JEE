package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import dao.livreDao;
import beans.Livre;

@WebServlet("/Livre")
public class livreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private livreDao livreDao;

	public void init() {
		livreDao = new livreDao();
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
				newLivre(request, response);
				break;
			case "insert":
				insertLivre(request, response);
				break;
			case "delete":
				deleteLivre(request, response);
				break;
			case "edit":
				editLivre(request, response);
				break;
			case "update":
				updateLivre(request, response);
				break;
			default:
				listLivre(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listLivre(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Livre> listLivre = livreDao.selectAllLivres();
		request.setAttribute("listLivre", listLivre);
		RequestDispatcher dispatcher = request.getRequestDispatcher("livre/list.jsp");
		dispatcher.forward(request, response);
	}

	private void newLivre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("livre/new.jsp");
		dispatcher.forward(request, response);
	}

	private void editLivre(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Livre existingLivre = livreDao.selectLivre(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("livre/update.jsp");
		request.setAttribute("livre", existingLivre);
		dispatcher.forward(request, response);

	}

	private void insertLivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		System.out.println("test");
		int num = Integer.parseInt(request.getParameter("num"));
		String titre = request.getParameter("titre");
		int stock = Integer.parseInt(request.getParameter("stock"));
		Livre newLivre = new Livre(num, titre, LocalDate.now(), stock);
		livreDao.insertLivre(newLivre);
		response.sendRedirect("Livre");
	}

	private void updateLivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Livre livre = livreDao.selectLivre(id);
		int num = Integer.parseInt(request.getParameter("num"));
		String titre = request.getParameter("titre");
		LocalDate date = livre.getDate();
		int stock = Integer.parseInt(request.getParameter("stock"));
		Livre updateLivre = new Livre(id, num, titre, date, stock);
		
		livreDao.updateLivre(updateLivre);
		
		response.sendRedirect("Livre");
	}

	private void deleteLivre(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		livreDao.deleteLivre(id);
		response.sendRedirect("Livre");
	}
}
