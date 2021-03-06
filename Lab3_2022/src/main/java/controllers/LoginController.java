package controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.tuple.Pair;

import managers.ManageUsers;
import models.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = new User();
		ManageUsers manager = new ManageUsers();
		Pair<Boolean, User> pair = null;

		try {

			BeanUtils.populate(user, request.getParameterMap());

			if (manager.isLoginComplete(user) && manager.canLogin(user).getLeft()) {
				pair = manager.canLogin(user);

				if (pair.getLeft()) {
					HttpSession session = request.getSession();
					session.setAttribute("user", pair.getRight());
					RequestDispatcher dispatcher = request.getRequestDispatcher("ViewOwnTimeline.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				request.setAttribute("user", user);
				request.setAttribute("failed", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("ViewLoginForm.jsp");
				dispatcher.forward(request, response);

			}
//			manager.finalize();
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
