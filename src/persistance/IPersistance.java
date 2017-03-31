package persistance;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import modèle.Tool;
import modèle.User;

public interface IPersistance {
	
	public String getTools();
	
	public String login(String email, String password);
	
	public String signUpUser(String pseudo, String flag, String email, String password, String gender, String birthdate) throws SQLException;
	
	public String signUpCheck(String email);
	
	public String getNewUsers();

	public String getYoutubers();
	
	public String getAllGames();
	
	public String getPosts();
	
	public String getFeedPosts(String follower_id);
	
	public String newFeedPost(String user_id, String publication_time, String post_content);
	
	public String getSubscriptions(String follower_id);
	
	public String lookForUsers(String pseudo);
	
	public String followUser(String follower_id, String followed_id);

	public String unfollowUser(String follower_id, String followed_id);
	
	
}
