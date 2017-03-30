package contrôleur;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/vue/index.html#")
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
			try {
				this.afficherAccueil(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
		
			switch(action){
			
			case "inscrire" : break;
			case "connecter" : this.login(request, response); break;
			case "tools" : try {
					this.sendTools(request, response);
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
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String) request.getAttribute("email");
		String password = (String) request.getAttribute("password");
		
	}
	
	private void sendTools(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List<Tool> tools = this.persistance.getTools();
		
		
		
	}
	
	private void inscrireUtilisateur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = (String) request.getAttribute("pseudo");
		
		//User user = new User(pseudo, firstName, lastName, imagePath, flag, email, password, gender, birthdate, banned, validation, admin)
		
	}
	
	private void afficherAccueil(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		List<Tool> tools = this.persistance.getTools();
		
		request.setAttribute("tools", tools);
		
		request.getRequestDispatcher("").forward(request, response);
		
		
	}
		

}
