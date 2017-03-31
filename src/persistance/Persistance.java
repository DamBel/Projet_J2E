package persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modèle.JSONConverter;

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
	private String newFeedPost, followUser, getYoutubers,
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
	

	public String signUpUser(String pseudo, String flag, String email, String password, String gender, String birthdate){
		
		String query = new String(this.signUpUser);
		
		System.out.println("initialquery=" + query);
		
		query = query.replaceAll(":pseudo", pseudo);
		query = query.replaceAll(":flag", flag);
		query = query.replaceAll(":email", email);
		query = query.replaceAll(":password", password);
		query = query.replaceAll("ggender", gender);
		query = query.replaceAll(":birthdate", birthdate);
		
		System.out.println("query=" + query);
		
		String json = JSONConverter.insertResultSetToJson(this.connexion, query);
		
		return json;
		
	}
	
	public String signUpCheck(String email){
		
		String query = new String(this.signUpCheck);
		
		query = query.replaceAll(":email", email);
		
		String json = JSONConverter.resultSetToJson(this.connexion, query);
		
		return json;
		
	}
	
	
	public String login(String email, String password){
		
		String query = new String(this.login);
		
		System.out.println("login=" + login);
		
		query = query.replaceAll(":email", email);
		query = query.replaceAll(":password", password);
		
		System.out.println("query=" + query);
		
		String json = JSONConverter.resultSetToJson(this.connexion, query);
		
		return json;
		
	}
	
	
	public String getTools(){
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getTools);
		
		return json;
		
	}
	
	
	public String getNewUsers(){
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getNewUsers);
		
		return json;
		
	}
	
	
	public String getYoutubers(){
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getYoutubers);
		
		return json;
		
	}
	
	@Override
	public String getAllGames() {
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getAllGames);
		
		return json;
	}

	@Override
	public String getPosts() {
		
		String json = JSONConverter.resultSetToJson(this.connexion, this.getPosts);
		
		return json;
		
	}

	@Override
	public String getFeedPosts(String follower_id) {
		
		String query = new String(this.getFeedPosts);
		
		query = query.replaceAll(":follower_id", follower_id);
		
		String json = JSONConverter.resultSetToJson(this.connexion, query);
		
		return json;
		
	}

	@Override
	public String getSubscriptions() {
		return null;
	}
	
	
	public String newFeedPost(String user_id, String publication_time, String post_content) {
		
		String query = new String(this.newFeedPost);
		
		System.out.println("initquery=" + query);
		
		query = query.replaceAll(":user_id", user_id);
		query = query.replaceAll(":time", publication_time);
		query = query.replaceAll(":content", post_content);
		
		String json = JSONConverter.insertResultSetToJson(this.connexion, query);
		
		return json;
		
	}
	
	
	
	/**
	 * Prépare les statements qui seront utilisés plus tard par le site
	 * @throws SQLException
	 */
	private void prepareStatements() throws SQLException{
		
		this.initEncodage = this.connexion.prepareStatement("SET NAMES utf8mb4");
		
		this.login = "SELECT user_id, pseudo, imgPath, flag, email, gender, password, birthdate FROM users WHERE email = ':email' AND password = ':password'";
		
		this.newFeedPost = "INSERT INTO `post`(`user_id`, `adventure_id`, `publication_time`, `type`, `content`, `link`) VALUES (':user_id',NULL,':time',0,':content',null)";
		
		this.followUser = "INSERT INTO `follow_user`(`follower_id`, `followed_id`) VALUES (':follower_id',':followed_id')";
		
		this.unfollowUser = "DELETE FROM `follow_user` WHERE follower_id = ':follower_id' AND followed_id = ':followed_id'";
		
		this.signUpUser = "INSERT INTO `users`(`pseudo`, `first_name`, `last_name`, `imgPath`, `flag`, `email`, `password`, `gender`, `birthdate`, `banned`, `validation`, `admin`) VALUES (':pseudo','undefined','undefined','images/unknown.png',':flag',':email',':password',':gender',':birthdate',0,1,0)";
		
		this.signUpCheck = "SELECT * FROM users WHERE email = ':email'";
		
		this.getTools = "SELECT * FROM tools;";
		
		this.lookForUser = "SELECT user_id, pseudo, imgPath, flag FROM `users` WHERE pseudo LIKE ':pseudo_search' AND user_id != ':user_id' LIMIT 20;";
		
		this.getPosts = "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p INNER JOIN users u ON p.user_id = u.user_id ORDER BY p.publication_time DESC LIMIT 10;";
		
		this.getFeedPosts = "SELECT u.pseudo as pseudo, p.content as content, u.imgPath as imgPath, p.publication_time as publication_time, u.flag as flag FROM post p "
				+"INNER JOIN users u ON (p.user_id = u.user_id) "
				+"INNER JOIN follow_user f ON (p.user_id = f.followed_id) "
				+"WHERE f.follower_id = ':follower_id' "
				+"UNION "
				+"SELECT u2.pseudo as pseudo, p2.content as content, u2.imgPath as imgPath, p2.publication_time as publication_time, u2.flag as flag FROM post p2" 
				+"INNER JOIN users u2 ON (p2.user_id = u2.user_id)" 
				+"WHERE u2.user_id = ':follower_id'" 
				+"ORDER BY publication_time" 
				+"DESC LIMIT 10;";
		
		this.getSubscriptions = "SELECT u.user_id as user_id, u.pseudo as pseudo, u.imgPath as imgPath, u.flag as flag FROM follow_user f INNER JOIN users u ON f.follower_id = ':follower_id' AND f.followed_id = u.user_id";
		
		this.getAllGames = "SELECT * FROM `games` ORDER BY `game_id` ASC;";
		
		this.getNewUsers = "SELECT pseudo, imgPath, flag FROM `users` WHERE 1 ORDER BY `user_id` DESC LIMIT 5;";
		
		this.getYoutubers = "SELECT * FROM youtubers;";
		
		
		
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
