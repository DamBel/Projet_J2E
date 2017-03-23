package persistance;

import java.sql.Connection;

public class Persistance implements IPersistance {
	
	private static final String TYPE_BDD = "oracle:thin";
	private static final String HOST = "Lenovo-PC";
	private static final String PORT = "1521";
	private static final String SID = "XE";
	private static final String LOGIN = "SYSTEM";
	private static final String PASSWORD = "admin";
	
	private Connection connexion;
	private String url = "jdbc";
	
	public Persistance(){
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		
		this.connexion.close();
		
		super.finalize();
	}

}
