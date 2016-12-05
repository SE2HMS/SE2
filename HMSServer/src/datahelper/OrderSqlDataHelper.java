package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.OrderPO;
import datahelperinterface.OrderDataHelper;

public class OrderSqlDataHelper implements OrderDataHelper{
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
	
	public ArrayList<OrderPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from orders";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<OrderPO> list=new ArrayList<OrderPO>();
	        while(rs.next()){
	        	OrderPO o=new OrderPO(rs.getInt("id")+"",rs.getInt("userid")+"",rs.getString("hotel"),
	        			rs.getString("username"),rs.getString("usercontact"),rs.getString("type"),rs.getString("intime"),
	        			rs.getString("outtime"),rs.getString("lasttime"),rs.getDouble("totel"));
	        	list.add(o);
	        }
	        pstmt.close();
	        conn.close();
	        return list;
	        
	    }catch(SQLException e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public int update(OrderPO o){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update orders set userid=" + Integer.parseInt(o.getUserID()) +
				", username='"+o.getUserName()+"', usercontact='"+o.getUserContact()+
				"',hotel='"+o.getHotel()+"',totel="+o.getTotel()+
				",type='"+o.getType()+"',intime='"+o.getInTime()+"',outtime='"+o.getOutTime()+
				"',lasttime='"+o.getLastTime()+"' where id='" + o.getID()+ "'";
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
	
	public int insert(OrderPO o){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into orders(userid,username,usercontact,hotel,totel,type,intime,outtime,lasttime) values("+
		Integer.parseInt(o.getUserID())+",'"+o.getUserName()+"','"+o.getUserContact()+"','"+o.getHotel()+"',"+o.getTotel()+
		",'"+o.getType()+"','"+o.getInTime()+"','"+o.getOutTime()+"','"+o.getLastTime()+"')";
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
		String sql="delete from orders where id ="+j;
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
