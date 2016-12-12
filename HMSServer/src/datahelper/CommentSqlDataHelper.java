package datahelper;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.CommentPO;
import datahelperinterface.CommentDataHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentSqlDataHelper implements CommentDataHelper{
	
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
	
	public ArrayList<CommentPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from comment";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<CommentPO> list=new ArrayList<CommentPO>();
	        while(rs.next()){
	        	CommentPO c=new CommentPO(rs.getString("hotelname"),rs.getString("details"),rs.getDouble("commentlevel"));
	        	list.add(c);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public int insert(CommentPO c){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into comment (hotelname,details) values ("+"'"+c.getHotelName()+"','"+c.getDetials()+"')";
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
	
	public int delete(CommentPO c){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="delete from comment where details ='"+c.getDetials()+"'";
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
