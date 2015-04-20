package com.colin.util;


import java.sql.*;

import org.apache.log4j.Logger;

public class ConnManager {
	// 使用log4j记录日志
	private static Logger logger = Logger.getLogger(ConnManager.class);
	private static final String driverClass = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/signup";
	private static final String username = "root";
	private static final String password = "602302300";
	
	
	//静态代码块
	static {
		try {
			// 加载驱动
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public  static Connection getConnection()
	  {
		
	  	Connection conn=null ;
			try
			{
				conn=DriverManager.getConnection(url,username,password);
				
			}catch(Exception e)
			{
				System.out.println("数据库连接失败!");
				e.printStackTrace();
			}
			return conn;
	  }
	  public static void closeConnection(Connection conn)
	  {
	  	try
	  	{
	  		if(conn!=null&&!(conn.isClosed()))
	  		{
	  			conn.close();
	  		}
	  	}catch(SQLException e)
			{
				e.printStackTrace();
			}
	  }
	  public static void closeResultSet(ResultSet rs)
	  {
	  	if(rs!=null)
	  	{
	  		try
	  		{
	  		    rs.close();
	  		}catch(SQLException e)
	  		{
	  			e.printStackTrace();
	  		}
	  		rs=null;
	  	}
	  }
	  public static void closeStatement(Statement statement)
	  {
	  	if(statement!=null)
	  	{
	  		try{
	  			statement.close();
	  		}catch(SQLException e){
	  			e.printStackTrace();
	  		}
	  	}
	  statement=null;
	  }
	  /*
		 * 关闭数据库连接，注意关闭的顺序
		 */
		public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
			if(rs!=null){
				try{
					rs.close();
					rs=null;
				}catch(SQLException e){
					e.printStackTrace();
					logger.error("关闭ResultSet失败");
				}
			}
			if(ps!=null){
				try{
					ps.close();
					ps=null;
				}catch(SQLException e){
					e.printStackTrace();
					logger.error("关闭PreparedStatement失败");
				}
			}
			if(conn!=null){
				try{
					conn.close();
					conn=null;
				}catch(SQLException e){
					e.printStackTrace();
					logger.error("关闭Connection失败");
				}
			}
		}
}
