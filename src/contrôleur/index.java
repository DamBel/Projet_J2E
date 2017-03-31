package contrôleur;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import persistance.IPersistance;
import persistance.Persistance;

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
        this.persistance = new Persistance();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null){
			response.sendRedirect("./vue/index.html#/");
		}
		else{
		
			switch(action){
			
			case "new_users" : try {
					this.sendNewUsers(request, response);
				} catch (SQLException e2) {
					e2.printStackTrace();
				} break;
				
			case "login" : try {
					this.login(request, response);
				} catch (SQLException e2) {
					e2.printStackTrace();
				} break;
			
			case "tools" : try {
					this.sendTools(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "youtubers" : try {
					this.sendYoutubers(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "signup_user" : try {
					this.signUpUser(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "signup_check" : try {
					this.signUpCheck(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "feed_posts" : try {
					this.showFeedPosts(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "new_feed_post" : try {
					this.newFeedPost(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "recherche_user" : try {
					this.searchUser(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "subscriptions" : try {
					this.subscribe(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "follow_user" : try {
					this.followUser(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			case "unfollow_user" : try {
					this.unfollowUser(request, response);
				} catch (SQLException e1) {
					e1.printStackTrace();
				} break;
				
			default : try {
					this.afficherAccueil(request, response);
				} catch (SQLException e) {
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
		
		String email = request.getParameter("email");
		
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
		
		String json = this.persistance.newFeedPost(user_id, publication_time, content);
		
		response.getOutputStream().print(json);
		
	}	
	
	private void subscribe(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String user_id = request.getParameter("user_id");
		
		if (user_id != null){
			String json = this.persistance.getSubscriptions(user_id);
			
			if (json.contains(user_id)){
				response.getOutputStream().print("");	
			} else{
				response.getOutputStream().print(json);
			}
			
		} else {
			response.getOutputStream().print("");
		}
		
	}		
	
	
	private void followUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String follower = request.getParameter("follower_id");
		String followed = request.getParameter("followed_id");
		
		String json = this.persistance.followUser(follower, followed);
		
		response.getOutputStream().print(json);
		
	}	
	
	
	private void unfollowUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String follower = request.getParameter("follower_id");
		String followed = request.getParameter("followed_id");
		
		String json = this.persistance.unfollowUser(follower, followed);
		
		response.getOutputStream().print(json);
		
	}	
	
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		String pseudo = request.getParameter("pseudo_search");
		
		String json = this.persistance.lookForUsers(pseudo);
		
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
