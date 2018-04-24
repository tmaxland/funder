package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;
		if (!username.equals("") && !password.equals(""))
			user = getUserFromDB(username, password);
		else {
			response.sendRedirect("Login");
		}
		if (user != null) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/Project");
		} else
			response.sendRedirect("Login");

	}

	User getUserFromDB(String uname, String pword) throws ServletException {
		User user = null;

		try {
			String url = "jdbc:mysql://localhost/funder";
			String username = "root";
			String password = "";



			Connection c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();

			ResultSet author = stmt
					.executeQuery("select * from users where username='"
							+ uname + "' and password='" + pword + "'");
			while (author.next()) {
				Integer aid = author.getInt("id");
				String _uname = author.getString("username");
				String _pword = author.getString("password");

				user = new User(aid, _uname, _pword);
			}

			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		return user;
	}

}
