package persistance;

import java.sql.SQLException;
import java.util.List;

import modèle.Tool;
import modèle.User;

public interface IPersistance {
	
	public String getTools() throws SQLException;
	
	public String login(String email, String password) throws SQLException;
	
	public String getNewUsers() throws SQLException;

	public String getYoutubers() throws SQLException;
	
	public String getAllGames() throws SQLException;
	
	public String getPosts() throws SQLException;
	
	public String getFeedPosts(String follower_id) throws SQLException;
	
	public String getSubscriptions() throws SQLException;
	
	
}
