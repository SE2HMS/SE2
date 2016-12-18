package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.RoomPO;
import datahelperinterface.RoomDataHelper;

public class RoomSqlDataHelper implements RoomDataHelper{
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
	
	public ArrayList<RoomPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from rooms";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<RoomPO> list=new ArrayList<RoomPO>();
	        while(rs.next()){
	        	int[] a={(int)rs.getByte("day0"),(int)rs.getByte("day1"),(int)rs.getByte("day2")};
	        	RoomPO r=new RoomPO(rs.getString("hotelname"),rs.getString("roomtype"),a,
	        			rs.getInt("totel"),rs.getInt("offline"),rs.getDouble("price"),rs.getString("name"));
	        	list.add(r);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	public int update(RoomPO r){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update rooms set day0=" + r.getNum()[0] +
				", day1="+r.getNum()[1]+", day2="+r.getNum()[2]+
				",totel="+r.getTotel()+",price="+r.getPrice()+ ",offline=" + r.getOfflineOrdered() +",name=" + r.getName()+
				"where hotelname='" + r.getHn() + "' and roomtype='"+r.getType()+"'";
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
	
	public int insert(RoomPO r){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into rooms(hotelname,roomtype,day0,day1,day2,totel,price,offline,name) values("+
		"'"+r.getHn()+"','"+r.getType()+"',"+r.getNum()[0]+","+r.getNum()[1]+
		","+r.getNum()[2]+","+r.getTotel()+","+r.getPrice() +r.getOfflineOrdered() + r.getName() +")";
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
	
	public int delete(RoomPO r){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="delete from rooms where hotelname ='"+r.getHn()+"' and roomtype='"+r.getType()+"'";
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
