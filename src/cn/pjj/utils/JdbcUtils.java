package cn.pjj.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtils {
	private static DataSource dataSource;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static {
		try {
			Properties prop = new Properties();
			InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream(
					"db.properties");
			prop.load(in);
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e){
			e.printStackTrace();
//			throw new RuntimeException(e);
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static ThreadLocal<Connection> getThreadLocal() {
		return tl;
	}

	public static Connection startTransaction() {
		Connection conn = null;
		try {
			conn = tl.get();
			if (conn == null) {
				conn = dataSource.getConnection();
				tl.set(conn);
			}
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return conn;
	}

	public static void commitTransaction() {
		Connection conn = null;
		try {
			conn = tl.get();
			if(conn!=null){
				conn.commit();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeConnection() {
		Connection conn = null;
		try {
			conn = tl.get();
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			tl.remove();
		}
	}
}
