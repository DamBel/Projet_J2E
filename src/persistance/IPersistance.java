package persistance;

import java.sql.SQLException;
import java.util.List;

import mod�le.Tool;
import mod�le.User;

public interface IPersistance {
	
	public String getTools() throws SQLException;
	
	public User login(String email, String password) throws SQLException;

}
