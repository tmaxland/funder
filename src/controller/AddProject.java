package controller;

import java.io.IOException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

@WebServlet("/AddProject")
public class AddProject extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
    public AddProject() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user = (User) request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect("Login");
			
		}
		else{
			
			request.getRequestDispatcher("/WEB-INF/AddProject.jsp").forward(request, response);	
		}
		
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO process the input data
	
		
	String title = request.getParameter("title");
	String description = request.getParameter("description");
	Integer funding_target = Integer.parseInt(request.getParameter("funding"));
	String date = request.getParameter("date");
	Integer funding_duration = Integer.parseInt(request.getParameter("duration"));
	Integer projectNo = null;
	try
    {
		String url = "jdbc:mysql://localhost/funder";
		String username = "root";
		String password = "";


        Connection c = DriverManager.getConnection( url, username, password );
       
        //get user id
        User usernameCurrent = (User) request.getSession().getAttribute("user");

        
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery( "select u.id from users u where u.username='"+ usernameCurrent.getUsername() +"'" );
        Integer id = null;
        while( rs.next() )
        {
             id = rs.getInt("id" );
        } 
        
        String sql = "insert into projects (title, description, creator_id, funding_target, start_date, funding_duration) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = c.prepareStatement( sql );
        pstmt.setString( 1, title );
        pstmt.setString( 2, description );
		pstmt.setInt( 3, id );
        pstmt.setInt( 4, funding_target );
        pstmt.setString( 5,  date );
        pstmt.setInt( 6, funding_duration );
        
        pstmt.executeUpdate();
        
        Statement stmt2 = c.createStatement();
        ResultSet rs2 = stmt2.executeQuery( "select last_insert_id() from projects;" );
        
        Integer last_id = null;
        while( rs2.next() )
        {
             last_id = rs2.getInt("last_insert_id()");
        }
        
        projectNo = last_id ;
        
        c.close();
    }
    catch( SQLException e )
    {
        throw new ServletException( e );
    }
	
	response.sendRedirect ("AddReward?projectNo="+projectNo);}
}

	
	
	
	

