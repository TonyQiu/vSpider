package com.tonyqiu.vspider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class DbHelper {
	private static Connection conn = null;  

	 /****数据库连接*** */  
    public static Connection getConnection() {  
    	if(conn == null) {
	        String driver = "com.mysql.jdbc.Driver";  
	        String url = "jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=utf-8";  
	  
	        DbUtils.loadDriver(driver);  
	  
	        try {  
	            conn = DriverManager.getConnection(url, "root", "devpassword123");  
	        } catch (SQLException ex) {  
	            ex.printStackTrace();  
	        }  
    	}
        return conn;  
    } 
    
	public static void saveColumns(List<List<ContentColumn>> list) throws SQLException{
		QueryRunner run = new QueryRunner();
		//save the url first
		String sql = "insert IGNORE into urls (job_id, url, success, create_time, update_time) values (1,?,1,now(),now())";
		List<Object[]> paramsList = new ArrayList<Object[]>();
		for(List<ContentColumn> subList : list) {
			for(ContentColumn column : subList) {
				paramsList.add(new Object[]{column.parentUrl});
				
			}
		}
		Object[][] params = new Object[paramsList.size()][1];
		for(int i=0; i<paramsList.size(); i++) {
			params[i] = paramsList.get(i);
		}
		run.batch(getConnection(), sql, params);
		
		//save the columns
		sql="INSERT into `columns` (job_id,url,title,value,create_by,create_time,update_by,update_time) values (job_id,?,?,?,NULL,now(),NULL,now())";
		paramsList = new ArrayList<Object[]>();
		for(List<ContentColumn> subList : list) {
			for(ContentColumn column : subList) {
				paramsList.add(new Object[]{column.parentUrl, column.name, column.value});
				
			}
		}
		
		params = new Object[paramsList.size()][3];
		for(int i=0; i<paramsList.size(); i++) {
			params[i] = paramsList.get(i);
		}
		
		run.batch(getConnection(), sql, params);
		
	}
	
	public static List<String> getHitoryUrl() throws SQLException {
		String sql="SELECT url FROM `urls` where success=1";
		QueryRunner run = new QueryRunner();
		ResultSetHandler<List<String>> h = new ResultSetHandler<List<String>>() {
			@Override
			public List<String> handle(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<String>();
				while(rs.next()) {
					list.add(rs.getString("url"));
				}
				return list;
			}
		};
		return run.query(getConnection(),sql, h);
	}
}
