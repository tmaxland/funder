package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUp() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// String comment=null;
		// request.setAttribute("comment", comment);
		request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rpassword = request.getParameter("rpassword");
		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");

		if (username == "") {
			String comment = "Username cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;

		}
		if (password == "") {
			String comment = "Password cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;
		}
		if (rpassword == "") {
			String comment = "ReType Password field cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;
		}
		if (fName == "") {
			String comment = "First Name cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;
		}
		if (lName == "") {
			String comment = "Last Name cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;
		}
		if (email == "") {
			String comment = "Email cannot be empty";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;
		}
		if (username != "") {
			if (username.length() < 4) {
				String comment = "UserName has to be more than 4 Characters.";
				request.setAttribute("comment", comment);
				request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
						request, response);
				return;

			}
		}

		if (password != "") {
			if (password.length() < 4) {
				String comment = "Password has to be more than 4 Characters.";
				request.setAttribute("comment", comment);
				request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
						request, response);
				return;

			}
		}

		if (!password.equals(rpassword)) {
			String comment = "Passwords do not match";
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/WEB-INF/SignUp.jsp").forward(
					request, response);
			return;

		}

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs320stu17";
			String username1 = "cs320stu17";
			String password1 = "R!#5JHXC";


			Connection c = DriverManager.getConnection(url, username1,
					password1);
			Statement stmt = c.createStatement();

			ResultSet author = stmt
					.executeQuery("select username,email from users");
			while (author.next()) {

				String _uname = author.getString("username");

				String _email = author.getString("email");

				if (username.equals(_uname)) {
					String comment = "Username already exists, choose other";
					request.setAttribute("comment", comment);
					request.getRequestDispatcher("/WEB-INF/SignUp.jsp")
							.forward(request, response);
					return;

				}
				if (email.equals(_email)) {
					String comment = "Email already exists, choose other";
					request.setAttribute("comment", comment);
					request.getRequestDispatcher("/WEB-INF/SignUp.jsp")
							.forward(request, response);
					return;

				}
			}

			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		try {
			String url = "jdbc:mysql://localhost/funder";
			String username2 = "root";
			String password2 = "";



			Connection c = DriverManager.getConnection(url, username2,
					password2);
			String sql1 = "insert into users (username,password,first_name,last_name,email) values (?, ?, ?, ?,?)";
			PreparedStatement pstmt = c.prepareStatement(sql1);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, fName);
			pstmt.setString(4, lName);
			pstmt.setString(5, email);

			pstmt.executeUpdate();

			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}

		response.sendRedirect("Login");
	}

}
