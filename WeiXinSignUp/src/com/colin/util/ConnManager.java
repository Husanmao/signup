package com.colin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnManager {
	// 使用log4j记录日志
	private static Logger logger = Logger.getLogger(ConnManager.class);
	// 连接驱动
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// 连接路径
	private static final String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_weixinsignuptest";
	// 用户名
	private static final String USERNAME = "2yk1yx0k3x";
	// 密码
	private static final String PASSWORD = "1i3kkmxk025mjwm3j5hmzw0kx5wz4hl4ll53ihhl";
	
	// 连接路径
	/*private static final String URL = "jdbc:mysql://localhost:3306/signup";
	// 用户名
	private static final String USERNAME = "root";
	// 密码
	private static final String PASSWORD = "602302300";*/
	
	//静态代码块
	static {
		try {
			// 加载驱动
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 获取数据库连接
	 */
	public static Connection getConnection() {
		Connection conn = null;
		logger.debug("开始连接数据库");
		try{
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("数据库连接失败！");
		}
		logger.debug("数据库连接成功");
		return conn;
	}

	/*
	 * 关闭数据库连接，注意关闭的顺序
	 */
	public static void close(ResultSet rs1,ResultSet rs2, PreparedStatement ps, Connection conn) {
		if(rs1!=null){
			try{
				rs1.close();
				rs1=null;
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("关闭ResultSet失败");
			}
		}
		if(rs2!=null){
			try{
				rs2.close();
				rs2=null;
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