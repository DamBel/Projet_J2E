package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.ConfigurationException;

public class Persistance implements IPersistance {
	
	
	//Coordonnées pour la base
	private final String BASE = "oracle";
	private final String HOST = "localhost";
	private final String PORT = "3306";
	private final String LOGIN = "root";
	private final String PASSWORD = "root";
	private final String NOM_BDD = "taverne";
	
	private Connection connexion;
	
	
	//Statements
	private final String initEncodage = "SET NAMES utf8mb4";
	
	
	public Persistance() throws SQLException{
		
		switch(this.BASE){
		
		case "oracle" :
			
			this.connexion = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + this.HOST + ":" + this.PORT + ":XE", 
					this.LOGIN, 
					this.PASSWORD);
			break;
		
		case "mysql" :
			this.connexion = DriverManager.getConnection(
					"jdbc:mysql://" + this.HOST + ":" + this.PORT + "/" + this.NOM_BDD,
					this.LOGIN,
					this.PASSWORD);
			//trucs
			break;
		
		
		default : throw new SQLException("Erreur lors de la création de la base");
		
		}
		
		this.connexion.prepareStatement(this.initEncodage).execute();
	}
	
	
	
	@Override
	protected void finalize() throws Throwable {
		
		this.connexion.close();
		
		super.finalize();
	}

}
