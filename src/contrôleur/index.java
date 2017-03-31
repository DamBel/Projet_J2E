package contrôleur;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import modèle.Tool;
import modèle.User;
import persistance.IPersistance;
import persistance.Persistance;

import java.util.Scanner;

/**
 * Servlet implementation class index
 */
@WebServlet("/control")
public class index extends HttpServlet {
	
	private IPersistance persistance;	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @throws InvalidInputException 
     * @see HttpServlet#HttpServlet()
     */
    public index() throws SQLException, ClassNotFoundException, InvalidInputException {
        super();
        //Scanner scanner = new Scanner(System.in);
        //String s = scanner.nextLine();
        this.persistance = new Persistance();
        System.out.print("construct");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.print("init");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		System.out.println("opération = " + action);
		
		if (action == null){
			response.sendRedirect("./vue/index.html#/");
		}
		else{
		
			switch(action){
			
			case "new_users" : try {
					this.sendNewUsers(request, response);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} break;
				
			case "login" : try {
					this.login(request, response);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} break;
			
			case "tools" : try {
					this.sendTools(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			case "youtubers" : try {
					this.sendYoutubers(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			case "signup_user" : try {
					this.signUpUser(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			case "signup_check" : try {
					this.signUpCheck(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			case "feed_posts" : try {
					this.showFeedPosts(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			case "new_feed_post" : try {
					this.newFeedPost(request, response);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} break;
				
			default : try {
					this.afficherAccueil(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} break;
			
			}
		}
		
	}
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String json = this.persistance.login(email, password);
		
		response.getOutputStream().print(json);
		
	}
	
	
	private void sendTools(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String json = this.persistance.getTools();
		
		response.getOutputStream().print(json);
		
	}
	
	private void signUpCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		
		System.out.println("email=" + email);
		
		String json = this.persistance.signUpCheck(email);
				
		response.getOutputStream().print(json);
		
	}
	
	private void signUpUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String flag = request.getParameter("flag");
		
		String json = this.persistance.signUpUser(pseudo, flag, email, password, gender, birthdate);
		
		response.getOutputStream().print(json);
		
	}
	
	private void showFeedPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String follower_id = request.getParameter("follower_id");
		
		String json = this.persistance.getFeedPosts(follower_id);
		
		response.getOutputStream().print(json);
		
	}
	
	private void newFeedPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");
		String publication_time = request.getParameter("time");
		
		System.out.println(publication_time);
		System.out.println(content);
		
		String json = this.persistance.newFeedPost(user_id, publication_time, content);
		
		response.getOutputStream().print(json);
		
	}	
	
	
	
	private void afficherAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		response.sendRedirect("./vue/index.html#/");
		
	}
	
	private void sendNewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String json = this.persistance.getNewUsers();
		
		response.getOutputStream().print(json);
		
	}
	
	private void sendYoutubers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String json = this.persistance.getYoutubers();
		
		response.getOutputStream().print(json);
		
	}
		

}
