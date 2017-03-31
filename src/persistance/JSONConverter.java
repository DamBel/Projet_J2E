package persistance;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;

/*
 * Classe se chargeant d'exécuter et de convertir le résultat des requêtes SQL
 * de la persistance en JSON
 * 
 * ---
 * TODO : Voir si l'utilisation de "PreparedStatements" est possible
 * afin de remplacer les strings (pas assez sécurisés)
 */
public class JSONConverter {
	
	public static String resultSetToJson(Connection connection, String query) {
        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Impossible de joindre la base de données", se);
        } 
        return new Gson().toJson(listOfMaps);
    }
	
	public static String insertOrDeleteResultSetToJson(Connection connection, String query) {

        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.insert(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Impossible de joindre la base de données", se);
        } 
        return new Gson().toJson(listOfMaps);		
		
	}
	
}
