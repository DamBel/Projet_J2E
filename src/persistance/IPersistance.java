package persistance;

import java.sql.SQLException;
import java.util.List;

import modèle.Tool;
import modèle.User;

public interface IPersistance {
	
	public String getTools() throws SQLException;
	
	public User login(String email, String password) throws SQLException;

}
