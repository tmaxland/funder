package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProjectEntry;
import model.Reward;
import model.User;

@WebServlet("/AddReward")
public class AddReward extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddReward() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// User user = (User) request.getSession().getAttribute("user");
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect("Login");

		} else {

			Integer projectNo = Integer.parseInt(request
					.getParameter("projectNo"));
			request.setAttribute("projectNo", projectNo);

			// redirect to jsp
			request.getRequestDispatcher("/WEB-INF/AddReward.jsp").forward(
					request, response);
		}
		// get the argument of the projectNo

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// process the reward inputs from the form
		Integer projectNo = Integer.parseInt(request.getParameter("projectNo"));
		Integer rewardAmount = null;
		if ((request.getParameter("amount") != "")
				|| (request.getParameter("description") != "")) {

			if (request.getParameter("amount") != "") {

				rewardAmount = Integer.parseInt(request.getParameter("amount"));
			}

			String rewardDescription = request.getParameter("description");
			try {
				String url = "jdbc:mysql://localhost/funder";
				String username = "root";
				String password = "";


				Connection c = DriverManager.getConnection(url, username,
						password);

				String sql = "insert into rewards ( amount , description, project_id) values (?, ?, ?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, rewardAmount);
				pstmt.setString(2, rewardDescription);
				pstmt.setInt(3, projectNo);

				pstmt.executeUpdate();

				c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		if (request.getParameter("add") != null) {
			response.sendRedirect("AddReward?projectNo=" + projectNo);
		} else if (request.getParameter("finish") != null) {
			response.sendRedirect("Project");
		}
	}
}
