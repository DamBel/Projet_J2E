package contrôleur;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistance.IPersistance;
import persistance.Persistance;

/**
 * Servlet implementation class index
 */
@WebServlet("/home")
public class index extends HttpServlet {
	
	private IPersistance persistance;	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public index() throws SQLException {
        super();
        
        this.persistance = new Persistance();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opération = request.getParameter("action");
		
		if (opération == null){
			this.afficherPage(request, response, opération);
		}
		else{
		
			switch(opération){
			/*
			case "signup_user" : break;
			case "signup_check" : break;
			case "tools" : break;
			case "login" : break;
			case "all_games" : break;
			case "new_users" : break;
			case "posts" : break;
			case "feed_posts" : break;
			case "new_feed_post" : break;
			case "recherche_user" : break;
			case "follow_user" : break;
			case "unfollow_user" : break;
			case "subscriptions" : break;
			*/
			default : this.afficherPage(request, response, opération); break;
			
			}
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void afficherPage(HttpServletRequest request, HttpServletResponse response, String opération) throws ServletException, IOException {
		
		
	}
		

}
