package modèle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.google.gson.Gson;

public class JSONConverter {
	
	public static String resultSetToJson(Connection connection, String query) {
        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.query(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        } 
        return new Gson().toJson(listOfMaps);
    }
	
	public static String insertResultSetToJson(Connection connection, String query) {

        List<Map<String, Object>> listOfMaps = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            listOfMaps = queryRunner.insert(connection, query, new MapListHandler());
        } catch (SQLException se) {
            throw new RuntimeException("Couldn't query the database.", se);
        } 
        return new Gson().toJson(listOfMaps);		
		
	}
	
}
