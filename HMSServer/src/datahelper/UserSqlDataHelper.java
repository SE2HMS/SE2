package datahelper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.UserPO;
import datahelperinterface.UserDataHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserSqlDataHelper implements UserDataHelper{
	
	private static Connection getConn() {
	    String driver = "com.mysql.jdbc.Driver";
	    String url = "jdbc:mysql://localhost:3306/SE2";
	    String username = "root";
	    String password = "wasd132435";
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,加载对应驱动
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	public ArrayList<UserPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from users";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<UserPO> list=new ArrayList<UserPO>();
	        while(rs.next()){
	        	UserPO p=new UserPO(rs.getInt("id")+"",rs.getString("password"),
	        			rs.getString("contact"),rs.getString("name"),rs.getString("specialInfo"),
	        			rs.getInt("creditTol"),(int)rs.getByte("vipLev"),rs.getInt("islogin"),rs.getString("type"));
	        	list.add(p);
	        }
	        pstmt.close();
	        conn.close();
	        return list;
	        
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public int update(UserPO user){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update users set password='" + user.getPassword() +
				"', contact='"+user.getContactInfo()+"', name='"+user.getName()+
				"',specialInfo='"+user.getSpecialInfo()+"',creditTol="+user.getCreditTol()+
				",vipLev="+user.getVipLev()+",islogin="+user.getIsLogin()+" where id=" + Integer.parseInt(user.getID()) ;
		try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	public int insert(UserPO user){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into users(name,contact,creditTol,vipLev,islogin,password,type,specialInfo) values("+
		"'"+user.getName()+"','"+user.getContactInfo()+"',"+user.getCreditTol()+","+user.getVipLev()+","+user.getIsLogin()+
		",'"+user.getPassword()+"','"+user.getType()+"','"+user.getSpecialInfo()+"')";
		 try {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        i = pstmt.executeUpdate();
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return i;
	}
	
	public int delete(String id){
		Connection conn=getConn();
		int i=0,j=Integer.parseInt(id);
		PreparedStatement pstmt;
		String sql="delete from users where id ="+j;
		try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
}
