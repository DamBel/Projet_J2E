package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
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
	private final String initEncodage = "SET NAMES utf8mb4";
	
	
	public Persistance() throws SQLException{
		
		switch(this.BASE){
		
		case "oracle" :
			
			this.connexion = DriverManager.getConnection(this.URL_ORACLE, this.LOGIN, this.PASSWORD);
			break;
		
		case "mysql" :
			this.connexion = DriverManager.getConnection(this.URL_MYSQL, this.LOGIN, this.PASSWORD);
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
