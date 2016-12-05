package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.WebStrategyPO;
import datahelperinterface.WebStrategyDataHelper;

public class WebStrategySqlDataHelper implements WebStrategyDataHelper{
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
	
	public ArrayList<WebStrategyPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from webstrategy";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<WebStrategyPO> list=new ArrayList<WebStrategyPO>();
	        while(rs.next()){
	        	WebStrategyPO w=new WebStrategyPO(rs.getString("strategyname"),rs.getString("businesscircle"),
	        			rs.getString("date"),rs.getDouble("lev0"),rs.getDouble("lev1"),rs.getDouble("discount"),
	        			rs.getString("type"));
	        	list.add(w);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	public int update(WebStrategyPO w){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="update webstrategy set date='" + w.getDate() +
				"', lev0="+w.getLev0()+", lev1="+w.getLev1()+", discount="+w.getDiscount()+", type='"+w.getType()+
				"' where strategyname='" + w.getStrategyName() + "' and businesscircle='"+w.getBusinessCircle()+"'";
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
	
	public int insert(WebStrategyPO w){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into webstrategy (strategyname,businesscircle,date,lev0,lev1,discount,type) values("+
		"'"+w.getStrategyName()+"','"+w.getBusinessCircle()+"','"+w.getDate()+
		"',"+w.getLev0()+","+w.getLev1()+","+w.getDiscount()+",'"+w.getType()+"')";
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
	
	public int delete(WebStrategyPO w){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="delete from webstrategy where strategyname ='"+w.getStrategyName()+"' and businesscircle='"+w.getBusinessCircle()+"'";
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
