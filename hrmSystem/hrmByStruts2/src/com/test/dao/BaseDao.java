package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.test.db.DbSource;

public class BaseDao {
	Connection connection = null;
	
	//��ȡ���ݵ�����
	public Connection getConnection() throws SQLException {
		DruidDataSource ds = DbSource.getDataSource();
		connection =ds. getConnection();
		return connection;
	}
	//�ر���Դ
	public void closeConnection(PreparedStatement pstmt,Connection connection) throws SQLException {
		if(pstmt!=null) {
			pstmt.close();
		}
		if(connection!=null) {
			connection.close();
		}
		
	}
}
