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

@WebServlet("/DisplayProject")
public class DisplayProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayProject() {
		super();
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

		Integer projectNo = Integer.valueOf(request.getParameter("projectNo"));

		User usernameCurrent = (User) request.getSession().getAttribute("user");
		Integer uid=null;
		if(usernameCurrent != null){
		 uid = usernameCurrent.getId();
		}else{ 
			 uid =null;}
		request.setAttribute("entries", getEntriesFromDB(projectNo, uid));
		request.getRequestDispatcher("/WEB-INF/DisplayProject.jsp").forward(
				request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	ProjectEntry getEntriesFromDB(Integer projectNo, Integer uid) throws ServletException {
		ProjectEntry entries = new ProjectEntry();
		List<Reward> entry = new ArrayList<Reward>();
		
		try {
			String url = "jdbc:mysql://localhost/funder";
			String username = "root";
			String password = "";


			Connection c = DriverManager.getConnection(url, username, password);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("select p.id, p.title, p.description, p.funding_target,p.funding_duration, u.username, p.start_date from projects p , users u where p.id='"
							+ projectNo + "' and  p.creator_id=u.id");

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				String postedBy = rs.getString("username");
				String description = rs.getString("description");
				Date date = rs.getDate("start_date");
				Integer fundingTarget = rs.getInt("funding_target");
				Integer fundingDuration = rs.getInt("funding_duration");

				Date currentDate = new Date();

				Integer daysToGo = ((fundingDuration - (int) (currentDate
						.getTime() - date.getTime()) / (1000 * 60 * 60 * 24)));

				// get rewards from database
				Statement stmt2 = c.createStatement();
				ResultSet rs2 = stmt2
						.executeQuery("select amount , description from rewards  where project_id='"
								+ projectNo + "'");
				while (rs2.next()) {
					// get reward details here and add to entries
					Integer rewardAmount = rs2.getInt("amount");
					String rewardDescription = rs2.getString("description");
					entry.add(new Reward(rewardAmount, rewardDescription));

				}
				// check if current logged in user is present in the
				// user_project table

				
				Statement stmtChecksponsor = c.createStatement();
				ResultSet rsChecksponsor = stmtChecksponsor
						.executeQuery("select user_id from user_project where user_id='"
								+ uid + "' and project_id='"+id+"'");// query if user is in table get current user id

				Integer uidp = null;
				while (rsChecksponsor.next()) {
					uidp = rsChecksponsor.getInt("user_id");
				}
				boolean sponsorLink = true;
				if ( uidp != null) {
					 sponsorLink = false;
				}

				entries = new ProjectEntry(id, title, postedBy, description,
						date, fundingTarget, fundingDuration, daysToGo, entry,
						sponsorLink);
			}

			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		return entries;
	}

}
