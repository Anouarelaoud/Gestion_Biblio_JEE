package controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import beans.login;
import dao.loginDao;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private loginDao loginDao;

	public void init() {
		loginDao = new loginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null)
			session.removeAttribute("user");
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user");
			session.removeAttribute("password");
		}
		else
			authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		login loginBean = new login();
		loginBean.setUsername(username);
		loginBean.setPassword(password);

		try {
			if (loginDao.validate(loginBean)) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setAttribute("password", password);
				response.sendRedirect("Etudiant");
			} else {
				response.sendRedirect("login/login.jsp");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}

