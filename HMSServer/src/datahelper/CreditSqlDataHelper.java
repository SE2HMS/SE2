package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import PO.CreditPO;
import datahelperinterface.CreditDataHelper;

public class CreditSqlDataHelper  implements CreditDataHelper{
	private static Connection getConn(){
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
	
	public ArrayList<CreditPO> getAll(){
		Connection conn = getConn();
	    String sql = "select * from credits";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<CreditPO> list=new ArrayList<CreditPO>();
	        while(rs.next()){
	        	CreditPO c=new CreditPO(String.valueOf(rs.getInt("id")),rs.getString("time"),String.valueOf(rs.getInt("userid")),
	        			rs.getDouble("totel"),rs.getDouble("chge"),rs.getString("behavior"));
	        	list.add(c);
	        }
	        conn.close();
	        pstmt.close();
	        return list;
	    }catch(SQLException e){
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	public int insert(CreditPO c){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into credits (userid,time,totel,chge,behavior) values ("+Integer.parseInt(c.getUserID())+
				",'"+c.getTime()+"',"+c.getTotel()+","+c.getChange()+",'"+c.getBehavior()+"')";
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
