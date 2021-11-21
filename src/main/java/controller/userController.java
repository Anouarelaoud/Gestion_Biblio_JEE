package controller;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import dao.userDao;
import beans.user;

@WebServlet("/signUp")
public class userController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDao userDao;

	public void init() {
		userDao = new userDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//	System.out.println("test");
	signUp(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("signUp/signUp.jsp");
	}

	private void signUp(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		user usr = new user();
		usr.setNom(nom);
		usr.setPrenom(prenom);
		usr.setUsername(username);
		usr.setPassword(password);

		try {
//			System.out.println("test");
			int result = userDao.signUpUsr(usr);
//			System.out.println("test");
			if(result == 1) {
				request.setAttribute("NOTIFICATION", "User Registered Successfully!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("login/login.jsp");
		dispatcher.forward(request, response);
	}
}
