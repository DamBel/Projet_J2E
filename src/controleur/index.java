package controleur;

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
import persistance.PersistanceTaverne;

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
        this.persistance = new PersistanceTaverne();
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
		
			/*
			 * Contrôleur principal, redirige vers les fonctions appropriées en fonction du contenu de la variable 'action'
			 * qui se chargeront des transactions avec la base de données, avant d'envoyer le résultat sous format
			 * JSON à angular pour l'affichage dynamique
			 */
			switch(action){
			
			case "new_users" : this.sendNewUsers(request, response); break;
				
			case "login" : this.login(request, response); break;
			
			case "tools" : this.sendTools(request, response); break;
				
			case "youtubers" : this.sendYoutubers(request, response); break;
				
			case "signup_user" : this.signUpUser(request, response); break;
				
			case "signup_check" : this.signUpCheck(request, response); break;
				
			case "feed_posts" : this.showFeedPosts(request, response); break;
				
			case "new_feed_post" : this.newFeedPost(request, response); break;
				
			case "recherche_user" : this.searchUser(request, response); break;
				
			case "subscriptions" : this.getSubscriptions(request, response); break;
				
			case "follow_user" : this.followUser(request, response); break;
				
			case "unfollow_user" : this.unfollowUser(request, response); break;
				
			default : this.afficherAccueil(request, response); break;
			
			}
		}
		
	}
	
	/**
	 * Gère la connexion des utilisateurs au site
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String json = this.persistance.login(email, password);
		
		response.getOutputStream().print(json);
		
	}
	
	/**
	 * Renvoie le contenu de la table 'tools' de la base de données
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException
	 */
	private void sendTools(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String json = this.persistance.getTools();
		
		response.getOutputStream().print(json);
		
	}
	
	/**
	 * Vérifie lors de l'inscription d'un nouvel utilisateur si l'email a déjà été utilisé
	 * en recherchant dans la base de données
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void signUpCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String email = request.getParameter("email");
		
		String json = this.persistance.signUpCheck(email);
				
		response.getOutputStream().print(json);
		
	}
	
	/**
	 * Inscrit un nouvel utilisateur au site
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void signUpUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthdate = request.getParameter("birthdate");
		String gender = request.getParameter("gender");
		String flag = request.getParameter("flag");
		
		String json = this.persistance.signUpUser(pseudo, flag, email, password, gender, birthdate);
		
		response.getOutputStream().print(json);
		
	}
	
	/**
	 * Charge tous les posts relatifs aux abonnements de l'utilisateur
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showFeedPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String follower_id = request.getParameter("follower_id");
		
		String json = this.persistance.getFeedPosts(follower_id);
		
		response.getOutputStream().print(json);
		
	}
	
	/**
	 * Méthode appelée lors de la création d'un nouveau post. Le contenu de ce dernier est inséré
	 * dans la base puis renvoie ensuite le résultat de la requête à angular
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newFeedPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String user_id = request.getParameter("user_id");
		String content = request.getParameter("content");
		String publication_time = request.getParameter("time");
		
		String json = this.persistance.newFeedPost(user_id, publication_time, content);
		
		response.getOutputStream().print(json);
		
	}	
	
	/**
	 * Utilisée pour afficher les abonnements de l'utilisateur actuel
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getSubscriptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String user_id = request.getParameter("user_id");
		
		//Si l'utilisateur a au moins un abonnement
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
	
	/**
	 * Utilisée pour ajouter un abonnement à un utilisateur
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void followUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String follower = request.getParameter("follower_id");
		String followed = request.getParameter("followed_id");
		
		String json = this.persistance.followUser(follower, followed);
		
		response.getOutputStream().print(json);
		
	}	
	
	/**
	 * Utilisée pour enlever des abonnements à un utilisateur
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void unfollowUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String follower = request.getParameter("follower_id");
		String followed = request.getParameter("followed_id");
		
		String json = this.persistance.unfollowUser(follower, followed);
		
		response.getOutputStream().print(json);
		
	}	
	
	/**
	 * Utilisée pour rechercher un utilisateur par son pseudo
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String pseudo = request.getParameter("pseudo_search");
		
		String json = this.persistance.lookForUsers(pseudo);
		
		response.getOutputStream().print(json);
		
	}	
	
	
	/**
	 * Redirige vers la page d'accueil si le paramètre "action" est vide ou nul
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void afficherAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.sendRedirect("./vue/index.html#/");
		
	}
	
	/**
	 * Affiche les cinq derniers utilisateurs inscrits au site
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendNewUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String json = this.persistance.getNewUsers();
		
		response.getOutputStream().print(json);
		
	}
	
	
	/**
	 * Affiche les youtubers pour la page "Vidéastes"
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void sendYoutubers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String json = this.persistance.getYoutubers();
		
		System.out.println(json.toString());
		
		response.getOutputStream().print(json);
		
	}
		

}
