package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutil {

	//靜態方法 開啟連線
	public static Connection getConnection() {
		Connection connection=null;
		FileInputStream fileInputStream=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			fileInputStream = new FileInputStream("src/jdbc.properties");
			properties.load(fileInputStream);
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			String url = properties.getProperty("url");
			connection = DriverManager.getConnection(url,user,password);
			boolean status=!connection.isClosed();
			System.out.println("連線成功:"+status);
			} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	//關閉資源
	public static void closeResource (Connection connection,Statement statement,ResultSet resultSet) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement!=null) {
				statement.close();
			}
			if(resultSet !=null) {
				resultSet.close();
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	public static void closeResource(Connection connection, Statement statement) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement!=null) {
				statement.close();
			}
			
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public static void closeResource(Connection connection) {
		try {
			if(connection!=null) {
				connection.close();
			}
			
			
		}catch (SQLException e) {
				e.printStackTrace();
			}
	}


}
