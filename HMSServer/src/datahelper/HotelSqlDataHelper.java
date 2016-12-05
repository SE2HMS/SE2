package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.HotelPO;
import datahelperinterface.HotelDataHelper;

public class HotelSqlDataHelper implements HotelDataHelper{
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
	
	public ArrayList<HotelPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from hotel";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<HotelPO> list=new ArrayList<HotelPO>();
	        while(rs.next()){
	        	HotelPO h=new HotelPO(rs.getString("name"),rs.getString("businesscircle"),
	        			rs.getString("introduce"),rs.getString("address"),rs.getDouble("stars"));
	        	list.add(h);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	public int insert(HotelPO h){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into hotel (name,businesscircle,introduce,address,stars) values("+
		"'"+h.getName()+"','"+h.getBC()+"','"+h.getINTRO()+"','"+h.getAddress()+"',"+h.getStars()+")";
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
	
	public int update(HotelPO h){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update hotel set businesscircle='" + h.getBC() +
				"', introduce='"+h.getINTRO()+"', address='"+h.getAddress()+
				"',stars="+h.getStars()+"where name='" + h.getName() + "'";
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
