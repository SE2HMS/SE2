package datahelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import datahelperinterface.CompaniesDataHelper;

public class CompaniesSqlDataHelper implements CompaniesDataHelper{
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

	public ArrayList<Company> getAll(){
		Connection conn = getConn();
	    String sql = "select * from companies";
	    PreparedStatement pstmt;
	    try{
	    	pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        ArrayList<Company> list=new ArrayList<Company>();
	        while(rs.next()){
	        	Company c=new Company(rs.getString("hotelname"),rs.getString("companies"));
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
	
	public int insert(Company c){
		Connection conn=getConn();
		int i=0;
		PreparedStatement pstmt;
		String sql="insert into companies (hotelname,companies) values ("+"'"+c.getHotelName()+"','"+c.getCompanies()+"')";
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
