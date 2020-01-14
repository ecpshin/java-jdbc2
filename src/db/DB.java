package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				Properties propers = loadProperties();
				String url = propers.getProperty("dburl");
				conn = DriverManager.getConnection(url, propers);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	private static Properties loadProperties() {
		
		try (FileInputStream fis = new FileInputStream("db.properties")){
			
			Properties proper = new Properties();
			proper.load(fis);
			return proper;
			
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		} 
	}
	
	public static void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeStatement(Statement statement) {
		if(statement !=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
