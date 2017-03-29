package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	private PreparedStatement initEncodage, newFeedPost, followUser, 
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
	 * Prépare les statements qui seront utilisés plus tard par le site
	 * @throws SQLException
	 */
	private void prepareStatements() throws SQLException{
		
		this.initEncodage = this.connexion.prepareStatement("SET NAMES utf8mb4");
		
		this.newFeedPost = this.connexion.prepareStatement(""
				+ "INSERT INTO `post`(`user_id`, `adventure_id`, `publication_time`, `type`, `content`, `link`) "
				+ "VALUES (?,NULL,?,0,?,null)");
		
		this.followUser = this.connexion.prepareStatement(""
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
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		this.connexion.close();
		
		super.finalize();
	}

}
