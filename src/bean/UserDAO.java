package bean;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

import hibernate.Conversation;

public class UserDAO {
	private Connection conn = null;

	public Connection openDBConnection() {
		String url = "jdbc:sqlite:e:/user.db";
		try {
			Class.forName("org.sqlite.JDBC");
			conn = (Connection) DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public boolean closeDBConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	public int insertUser(UserBean user) {
		int i = 0;
		String sql = "insert into users(username,password) values(?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			i = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int updateUser(UserBean user) {
		int i = 0;
		String sql = "update users set username='" + user.getUsername() + "' where password='" + user.getPassword() + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public UserBean queryUser(String userName) {
		Conversation conv = new Conversation();
		conv.openDBConnection();
		UserBean userBean = conv.queryUser("username", userName);
		conv.closeDBConnection();
		return userBean;
		

//		String sql = "select * from users where username='" + userName + "'";
//		PreparedStatement pstmt;
//		try {
//			pstmt = (PreparedStatement) conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {				
//				UserBean user = new UserBean();
//				user.setName(rs.getString("username"));
//				user.setPassword(rs.getString("password"));
//				return user;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
	}

	public int deleteUser(String name) {
		int i = 0;
		String sql = "delete from users where username='" + name + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("resutl: " + i);
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static void main(String args[]) {

	}

}
