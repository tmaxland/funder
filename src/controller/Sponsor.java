package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProjectEntry;
import model.Reward;
import model.User;

@WebServlet("/Sponsor")
public class Sponsor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Sponsor() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			response.sendRedirect("Login");

		} else {

			Integer projectNo = Integer.parseInt(request
					.getParameter("projectNo"));
			request.setAttribute("projectNo", projectNo);

			request.setAttribute("entries", getEntriesFromDB(projectNo));
			// redirect to jsp
			request.getRequestDispatcher("/WEB-INF/Sponsor.jsp").forward(
					request, response);
		}
		// get the argument of the projectNo

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer projectNo = Integer.parseInt(request.getParameter("projectNo"));
		
		 Integer amount= Integer.parseInt(request.getParameter("amount"));

		try
	    {
			String url = "jdbc:mysql://localhost/funder";
			String username = "root";
			String password = "";

	        Connection c = DriverManager.getConnection( url, username, password );
	       
	        //get user id
	        User usernameCurrent = (User) request.getSession().getAttribute("user");
//	        System.out.print(usernameCurrent.getUsername());
	        
	        Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "select u.id from users u where u.username='"+ usernameCurrent.getUsername() +"'" );
	        Integer id = null;
	        while( rs.next() )
	        {
	             id = rs.getInt("id" );
	        } 
	        
			//sponsor_id , amount, reward_id , project_id
	        String sql = "insert into user_project (user_id,  project_id, pledge_amount  ) values (?, ?, ?)";
	        PreparedStatement pstmt = c.prepareStatement( sql );
	        pstmt.setInt( 1, usernameCurrent.getId() );
	        pstmt.setInt( 2, projectNo );
	        pstmt.setInt( 3, amount );
	        
	        pstmt.executeUpdate();
	        
	        
	        
	        c.close();
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
		
		response.sendRedirect ("Project");

	}
		
		


	ProjectEntry getEntriesFromDB(Integer projectNo) throws ServletException {
		ProjectEntry entries = new ProjectEntry();
		List<Reward> entry = new ArrayList<Reward>();

		try {
			String url = "jdbc:mysql://localhost/funder";
			String username = "root";
			String password = "";


			Connection c = DriverManager.getConnection(url, username, password);

			Statement stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("select p.id, p.title from projects p where p.id='"
							+ projectNo + "'");

			while (rs.next()) {
				
				
				Integer id = rs.getInt("id");
				String title = rs.getString("title");

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

				entries = new ProjectEntry ( id,title, entry);
			}

			c.close();
		} catch (SQLException e) {
			throw new ServletException(e);
		}
		return entries;
	}

}
