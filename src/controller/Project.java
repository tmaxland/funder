package controller;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProjectEntry;
import model.Reward;
import model.User;

@WebServlet("/Project")
public class Project extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Project() {
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO initialize initial values here
		super.init(config);
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO redirect display to jsp

		request.setAttribute("entries", getEntriesFromDB());
		request.getRequestDispatcher("/WEB-INF/Project.jsp").forward(request,
				response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	List<ProjectEntry> getEntriesFromDB() throws ServletException {
		List<ProjectEntry> entries = new ArrayList<ProjectEntry>();

		try {
			String url = "jdbc:mysql://localhost/funder";
			String username = "root";
			String password = "";



			Connection c = DriverManager.getConnection(url, username, password);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("select p.id, p.title, p.description, p.funding_target,p.funding_duration, u.username, p.start_date from projects p , users u where  p.creator_id=u.id");

			while (rs.next()) {
				// calculate days to go % funded an

				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				String postedBy = rs.getString("username");
				Date date = rs.getDate("start_date");
				Integer fundingTarget = rs.getInt("funding_target");
				Integer fundingDuration = rs.getInt("funding_duration");

				Date currentDate = new Date();

				// int daysToGo = (Integer) null;
				Integer daysToGo = ((fundingDuration - (int) (currentDate
						.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)));
				// query to find amount funded for the current project we only
				// need
				// project id for this

				Statement stmtAmountFunded = c.createStatement();
				ResultSet rsAmountFunded = stmtAmountFunded
						.executeQuery("select sum(pledge_amount) from user_project where project_id='"
								+ id + "'");
				Integer amountFunded = null;
				while (rsAmountFunded.next()) {
					amountFunded = rsAmountFunded.getInt("sum(pledge_amount)");
					
				}

				// calculate percentage funded

				float percentage = 0;
				if (amountFunded != 0) {

					percentage = (float) (amountFunded / fundingTarget
							.doubleValue());
					percentage = percentage * 100;
					if (percentage != 0) {
						percentage = (float) (Math.round(percentage * 100.0) / 100.0);

					}
				}

						// entry.setPercentage(percentage);

						entries.add(new ProjectEntry(id, title, postedBy, date,
								daysToGo, amountFunded, percentage));

					}

					c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		return entries;
	}
}
