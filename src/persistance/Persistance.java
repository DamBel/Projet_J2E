package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import modèle.JSONConverter;
import modèle.Tool;
import modèle.User;

public class Persistance implements IPersistance {
	
	
	//Coordonnées pour la base
	private final String BASE = "mysql";
	
	private final String URL_MYSQL = "jdbc:mysql://localhost:3306/taverne";
	private final String URL_ORACLE = "jdbc:oracle:thin:@//Lenovo-PC:1521/XE";
	private final String NOM_BDD = "taverne";
	private String LOGIN = "root";
	private final String PASSWORD = "root";
	
	private Connection connexion;
	
	//Statements
	private PreparedStatement initEncodage;
	private String newFeedPost, followUser, 
			unfollowUser, signUpUser, signUpCheck, getTools, lookForUser,
			getPosts, getFeedPosts, getSubscriptions, login, getAllGames, getNewUsers;
	
	/**
	 * Permet de se connecter à une base de données existante
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public Persistance() throws SQLException, ClassNotFoundException{
		
		switch(this.BASE){
		
		case "oracle" :
			
			this.connexion = DriverManager.getConnection(this.URL_ORACLE, this.LOGIN, this.PASSWORD);
			break;
		
		case "mysql" :
			Class.forName("com.mysql.jdbc.Driver");
			this.connexion = DriverManager.getConnection(this.URL_MYSQL, this.LOGIN, this.PASSWORD);
			break;
		
		
		default : throw new SQLException("Erreur lors de la création de la base");
	
		}
		
		
		this.prepareStatements();
		
		this.initEncodage.execute();
	}
	
	/**
	 * Connecter un utilisateur au site
	 * @throws SQLException 
	 */
	public User login(String email, String password) throws SQLException{
		
		/*
		PreparedStatement getAllUsers = this.connexion.prepareStatement(""
				+ "SELECT *"
				+ "FROM users"
				+ "WHERE email = ?");
		*/
		
		PreparedStatement getUser = this.connexion.prepareStatement(""
				+ "SELECT user_id, pseudo, imgPath, flag, email, gender, birthdate "
				+ "FROM users "
				+ "WHERE email = ?");
		
		getUser.setString(1, email);
		
		ResultSet userResult = getUser.executeQuery();
		
		if (userResult.next()){
			User user = new User(
					userResult.getString("pseudo"), 
					userResult.getString("firstName"), 
					userResult.getString("lastName"), 
					userResult.getString("imagePath"), 
					userResult.getString("flag"), 
					userResult.getString("email"), 
					userResult.getString("password"), 
					userResult.getString("gender"), 
					userResult.getDate("birthdate")
					);		
			
			if (true){}
			
		}
		
		return null;
		
	}
	
	
	public String getTools() throws SQLException{
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getTools);
		
		return json;
		
		/*String tmp = toolsResult.toString();
		
		while (toolsResult.next()){
			
			Tool tool = new Tool(toolsResult.getString("name"), 
					toolsResult.getString("imgPath"), 
					toolsResult.getString("url"));
			
			tools.add(tool);
			
		}
		
		return tools;*/
		
	}
	
	/**
	 * Prépare les statements qui seront utilisés plus tard par le site
	 * @throws SQLException
	 */
	private void prepareStatements() throws SQLException{
		
		this.initEncodage = this.connexion.prepareStatement("SET NAMES utf8mb4");
		
		this.newFeedPost = "INSERT INTO `post`(`user_id`, `adventure_id`, `publication_time`, `type`, `content`, `link`) VALUES (:user_id,NULL,:publication_time,0,:post_content,null)";
		
		this.followUser = "INSERT INTO `follow_user`(`follower_id`, `followed_id`) VALUES (:follower_id,:followed_id)";
		
		this.unfollowUser = "DELETE FROM `follow_user` WHERE follower_id = :follower_id AND followed_id = :followed_id";
		
		this.signUpUser = "INSERT INTO `users`(`pseudo`, `first_name`, `last_name`, `imgPath`, `flag`, `email`, `password`, `gender`, `birthdate`, `banned`, `validation`, `admin`) VALUES (:pseudo,'','','images/unknown.png',:flag,:email,:password,:gender,:birthdate,'0','1','0')";
		
		this.signUpCheck = "SELECT * FROM users WHERE email = :email OR pseudo = :pseudo;";
		
		this.getTools = "SELECT * FROM tools;";
		
		this.lookForUser = "SELECT user_id, pseudo, imgPath, flag FROM `users` WHERE pseudo LIKE :pseudo_search AND user_id != :user_id LIMIT 20;";
		
		this.getPosts = "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p INNER JOIN users u ON p.user_id = u.user_id ORDER BY p.publication_time DESC LIMIT 10;";
		
		this.getFeedPosts = "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p "
				+"INNER JOIN users u ON (p.user_id = u.user_id) "
				+"INNER JOIN follow_user f ON (p.user_id = f.followed_id) "
				+"WHERE f.follower_id = :follower_id "
				+"UNION "
				+"SELECT u2.pseudo as pseudo, p2.content as content, u2.imgPath as imgPath, p2.publication_time as publication_time, u2.flag as flag FROM post p2" 
				+"INNER JOIN users u2 ON (p2.user_id = u2.user_id)" 
				+"WHERE u2.user_id = :follower_id" 
				+"ORDER BY publication_time" 
				+"DESC LIMIT 10;";
		
		this.getSubscriptions = "SELECT u.user_id as user_id, u.pseudo as pseudo, u.imgPath as imgPath, u.flag as flag FROM follow_user f INNER JOIN users u ON f.follower_id = :follower_id AND f.followed_id = u.user_id";
		
		this.getAllGames = "SELECT * FROM `games` ORDER BY `game_id` ASC;";
		
		this.getNewUsers = "SELECT pseudo, imgPath, flag FROM `users` WHERE 1 ORDER BY `user_id` DESC LIMIT 5;";
		
		
		
		
		
		/*this.followUser = this.connexion.prepareStatement(""
				+ "INSERT INTO `follow_user`(`follower_id`, `followed_id`) "
				+ "VALUES (?,?)");
		
		this.unfollowUser = this.connexion.prepareStatement(""
				+ "DELETE FROM `follow_user` "
				+ "WHERE follower_id = ? AND followed_id = ?");
		
		this.signUpUser = this.connexion.prepareStatement(""
				+ "INSERT INTO `users`(`pseudo`, `first_name`, `last_name`, `imgPath`, `flag`, `email`, `password`, `gender`, `birthdate`, `banned`, `validation`, `admin`) "
				+ "VALUES (?,'','','images/unknown.png',?,?,?,?,?,'0','1','0')");
		
		this.signUpCheck = this.connexion.prepareStatement(""
				+ "SELECT * "
				+ "FROM users "
				+ "WHERE email = ? "
				+ "OR pseudo = ?;");
		
		this.getTools = this.connexion.prepareStatement("SELECT * FROM tools");
		
		this.lookForUser = this.connexion.prepareStatement(""
				+ "SELECT user_id, pseudo, imgPath, flag FROM `users` "
				+ "WHERE pseudo LIKE ? "
				+ "AND user_id != ? "
				+ "LIMIT 20");
		
		this.getPosts = this.connexion.prepareStatement(""
				+ "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag "
				+ "FROM post p "
				+ "INNER JOIN users u ON p.user_id = u.user_id "
				+ "ORDER BY p.publication_time "
				+ "DESC LIMIT 10;");
		
		this.getFeedPosts = this.connexion.prepareStatement(""
				+ "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag "
				+ "FROM post p "
				+ "INNER JOIN users u ON (p.user_id = u.user_id) "
				+ "INNER JOIN follow_user f ON (p.user_id = f.followed_id) "
				+ "WHERE f.follower_id = ? "
				+ "UNION "
				+ "SELECT u2.pseudo as pseudo, p2.content as content, u2.imgPath as imgPath, p2.publication_time as publication_time, u2.flag as flag "
				+ "FROM post p2 "
				+ "INNER JOIN users u2 ON (p2.user_id = u2.user_id) "
				+ "WHERE u2.user_id = ? "
				+ "ORDER BY publication_time "
				+ "DESC LIMIT 10;");
		
		this.getSubscriptions = this.connexion.prepareStatement(""
				+ "SELECT u.user_id as user_id, u.pseudo as pseudo, u.imgPath as imgPath, u.flag as flag "
				+ "FROM follow_user f "
				+ "INNER JOIN users u ON f.follower_id = ? "
				+ "AND f.followed_id = u.user_id");
		
		this.getAllGames = this.connexion.prepareStatement(""
				+ "SELECT * "
				+ "FROM `games` "
				+ "ORDER BY `game_id` "
				+ "ASC;");
		
		this.getNewUsers = this.connexion.prepareStatement(""
				+ "SELECT pseudo, imgPath, flag "
				+ "FROM `users` "
				+ "WHERE 1 "
				+ "ORDER BY `user_id` "
				+ "DESC LIMIT 5;");
		*/
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		this.connexion.close();
		
		super.finalize();
	}

}
