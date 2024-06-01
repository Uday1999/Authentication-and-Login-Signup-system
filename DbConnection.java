package com.servlet.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

	public static Connection getConnection() throws SQLException, IOException {

		Properties props = new Properties();

		FileInputStream fis = new FileInputStream(
				"D:\\PracticeWorkSpace\\LogInProject\\src\\main\\webapp\\jdbcdb.properties");

		//loading the properties file in the props object
		props.load(fis);

		//loading Driver 
		try { 
			Class.forName(props.getProperty("DB_DRIVER"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		//Establishing connection and return the connection object 
		Connection con = DriverManager.getConnection(props.getProperty("DB_URL"), props.getProperty("DB_USERNAME"),
				props.getProperty("DB_PASSWORD"));

		return con;
	}
}
