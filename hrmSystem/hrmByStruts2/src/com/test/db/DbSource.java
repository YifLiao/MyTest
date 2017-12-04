package com.test.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.alibaba.druid.pool.DruidDataSource;

public class DbSource {
	private static DruidDataSource ds = null;
	
	//��ȡ�����Ķ���    
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
	//��ȡ����Դ
	public static DruidDataSource getDataSource() {
		return ds;
	}
}
