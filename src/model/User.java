package model;

import java.util.ArrayList;
import java.util.List;

public class User {


	int id;
	String username;
	String password;
	
	List<ProjectEntry> projects;


	public User() {
	}
	
	
	public User(String username) {
		this.username =username;
	}
	
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<ProjectEntry> getProjects() {
		return projects;
	}


	public void setProjects(List<ProjectEntry> projects) {
		this.projects = projects;
	}


	public void add(ProjectEntry project, Integer sponsoredProjectNo) {
		
		if(this.projects!=null){
			this.projects.add(sponsoredProjectNo - 1, project);			
		}else{
			
			List<ProjectEntry> projects = new ArrayList<ProjectEntry>();
			projects.add(project);
			this.setProjects(projects);
		}
	}

	
}
