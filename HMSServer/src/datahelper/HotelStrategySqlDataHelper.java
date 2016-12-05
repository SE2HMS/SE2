package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.HotelStrategyPO;
import datahelperinterface.HotelStrategyDataHelper;

public class HotelStrategySqlDataHelper implements HotelStrategyDataHelper{
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
	
	public ArrayList<HotelStrategyPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from hotelstrategy";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<HotelStrategyPO> list=new ArrayList<HotelStrategyPO>();
	        while(rs.next()){
	        	HotelStrategyPO p=new HotelStrategyPO(rs.getString("hotelname"),rs.getString("strategyname"),
	        			rs.getString("specialinfo"),rs.getDouble("discount"),rs.getString("type"));
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
	
	public int update(HotelStrategyPO h){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update hotelstrategy set specialinfo='" + h.getSpecialInof() +
				"', discount="+h.getDiscount()+", type='"+h.getType()+
				"' where hotelname='" + h.getHotelName() + "' and strategyname='"+h.getStrategyName()+"'";
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
	
	public int insert(HotelStrategyPO h){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into hotelstrategy (hotelname,strategyname,specialinfo,discount,type) values("+
		"'"+h.getHotelName()+"','"+h.getStrategyName()+"','"+h.getSpecialInof()+"',"+h.getDiscount()+
		",'"+h.getType()+"')";
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
	
	public int delete(HotelStrategyPO h){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="delete from hotelstrategy where hotelname ='"+h.getHotelName()+"' and strategyname='"+h.getStrategyName()+"'";
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
