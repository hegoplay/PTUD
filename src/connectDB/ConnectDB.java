package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
	private static final String url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=QLyCHAM;";
	private static String USER_NAME = "sa";
    private static String PASSWORD = "123456";
	public static Connection getConection() {
		Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, USER_NAME, PASSWORD);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
	}
	public static void closeConnection(Connection c) {
		try {
			c.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
