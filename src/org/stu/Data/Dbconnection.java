package org.stu.Data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.Driver;

public class Dbconnection {
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PWD;
	private static Connection conn;
	
	static {
		Properties prop = new Properties();
		ClassLoader loader = Dbconnection.class.getClassLoader();
		InputStream is = loader.getResourceAsStream("org/stu/Data/jdbc.properties");
		
		try {
			prop.load(is);
			DRIVER = prop.getProperty("driver");
			URL = prop.getProperty("url");
			USERNAME = prop.getProperty("username");
			PWD = prop.getProperty("pwd");
			new Driver();
			conn = DriverManager.getConnection(URL, USERNAME, PWD);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	
	}

