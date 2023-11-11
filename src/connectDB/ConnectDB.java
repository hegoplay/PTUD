package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.NhaCC;

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
//            System.out.println("connect successfully!");
        } catch (Exception ex) {
//            System.out.println("connect failure!");
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
	public static List<String> getQuocGiaList() {
        List<String> quocGiaList = new ArrayList<>();

        try (Connection con = getConection()) {
            String query = "SELECT Iso FROM Countries";
            try (PreparedStatement statement = con.prepareStatement(query);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    quocGiaList.add(rs.getString("Iso"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quocGiaList;
    }
//	public static String getMaQuocGiaByTen(String tenQuocGia) {
//        String maQuocGia = null;
//
//        try {
//            Connection con = getConection();
//            String sql = "SELECT Iso FROM Countries WHERE Name = ?";
//            PreparedStatement statement = con.prepareStatement(sql);
//            statement.setString(1, tenQuocGia);
//            ResultSet rs = statement.executeQuery();
//
//            if (rs.next()) {
//                maQuocGia = rs.getString("Iso");
//            }
//
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            // Xử lý exception tùy ý, có thể thông báo lỗi hoặc trả về giá trị mặc định
//        }
//
//        return maQuocGia;
//    }
}
