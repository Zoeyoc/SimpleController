package hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.UserBean;

import java.lang.Class;
import java.lang.reflect.Method;

public class Conversation {
	private Connection connection = null;// 数据库链接
	private PreparedStatement ps =null; //sql语句
	private ResultSet rs = null; //返回结果集
	private OR_Mapping or_mapping;
	
	public Connection openDBConnection(){
		Configuration config = new Configuration();
		or_mapping = config.getOr_mapping();
		try {
			Property tempProperty = null;
			String driver_class = null;
			String url_path = null;
			String db_username = null;
			String db_userpassword = null;
			for(int i=0; i < or_mapping.getJdbc().getPropertyList().size(); i++) {
				tempProperty = or_mapping.getJdbc().getPropertyList().get(i);
				if (tempProperty.getName().equals("driver_class")){
					driver_class = tempProperty.getValue();
				}
				if (tempProperty.getName().equals("url_path")){
					url_path = tempProperty.getValue();
				}
				if (tempProperty.getName().equals("db_username")){
					db_username = tempProperty.getValue();
				}
				if (tempProperty.getName().equals("db_userpassword")){
					db_userpassword = tempProperty.getValue();
				}
			}
			Class.forName(driver_class);
			if(db_username==null&&db_userpassword==null){
				connection = DriverManager.getConnection(url_path);
			}else
			connection = DriverManager.getConnection(url_path,db_username,db_userpassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean closeDBConnection(){
		boolean b = false;
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(connection!=null){
				connection.close();
				connection=null;
			}
			b = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public UserBean queryUser(String columnName,String value) {
		UserBean userBean = null;
		String className = UserBean.class.getSimpleName();

		OrClass tempClass = null;
		OrClass rightClass = null;
		for(int i=0; i<or_mapping.getClassList().size(); i++) {
			tempClass = or_mapping.getClassList().get(i);
			if(tempClass.getName().equals(className)){
				rightClass = tempClass;
			}
		}
		try {
			connection = this.openDBConnection();
			String sql = "select * from "+ rightClass.getTable() + " where " + columnName + " = '" + value + "'";
			System.out.println(sql);
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()){
				//存在该用户
				String result = ""; 
				Class c = Class.forName(UserBean.class.getName());
				Object o = c.newInstance();
				for (Property property:rightClass.getPropertyList()) {
					String name = property.getName();
					Method m = c.getMethod("set"+name.substring(0, 1).toUpperCase()+name.substring(1),Class.forName("java.lang."+property.getType()));					
					System.out.println(name+rs.getObject(property.getColumn()).getClass().getName());
					m.invoke(o,rs.getObject(property.getColumn()));
				}
				return (UserBean) o;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeDBConnection();
		}
		return userBean;
	}
	
}
