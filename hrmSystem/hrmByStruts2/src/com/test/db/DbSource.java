package com.test.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.alibaba.druid.pool.DruidDataSource;

public class DbSource {
	private static DruidDataSource ds = null;
	
	//获取上下文对象    
	static {
		Context context = null;
		try {
			context = new InitialContext();
			ds = (DruidDataSource) context.lookup("java:comp/env/jdbc/MysqlDataSource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//获取数据源
	public static DruidDataSource getDataSource() {
		return ds;
	}
}
