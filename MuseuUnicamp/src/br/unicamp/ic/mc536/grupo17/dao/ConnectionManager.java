package br.unicamp.ic.mc536.grupo17.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
		
		public  static Connection getConnection(){
			Connection connection = null;
			try {
			    Class.forName("com.mysql.jdbc.Driver"); 
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/museu3","root", "root");
			} 
			catch (SQLException  e) {
				System.out.println("Fail to connect with the DB");
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				System.out.println("Class not found: com.mysql.jdbc.Driver");
				e.printStackTrace();
			}
			return connection;
		}
}
