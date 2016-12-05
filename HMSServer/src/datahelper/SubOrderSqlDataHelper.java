package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import datahelperinterface.SubOrderDataHelper;

public class SubOrderSqlDataHelper implements SubOrderDataHelper{
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
	
	public ArrayList<SubOrder> getAll(){
		Connection conn = getConn();
	    String sql = "select * from sub_order";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<SubOrder> list=new ArrayList<SubOrder>();
	        while(rs.next()){
	        	SubOrder s=new SubOrder(rs.getInt("orderid")+"",rs.getString("roomtype"),
	        			rs.getInt("num"),rs.getDouble("price"),rs.getDouble("subtotel"));
	        	list.add(s);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public int insert(SubOrder s){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into sub_order (orderid,roomtype,num,price,subtotel) values ("+Integer.parseInt(s.getOrderID())
			+",'"+s.getRoomType()+"',"+s.getNum()+","+s.getPrice()+","+s.getSubTotel()+")";
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
