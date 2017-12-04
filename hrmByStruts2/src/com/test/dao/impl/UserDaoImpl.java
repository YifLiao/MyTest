package com.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.dao.BaseDao;
import com.test.dao.IUserDao;

public class UserDaoImpl extends BaseDao implements IUserDao{

	//用户登录
	@Override
	public List<UserBean> queryUserByLogin(UserBean user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<UserBean> userList = new ArrayList<>();
		String sql= "select * from user_inf where loginname=? and PASSWORD=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, user.getLoginname());
			pstmt.setString(2, user.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserBean outUser = new UserBean(
						rs.getInt("ID"),
						rs.getString("loginname"),
						rs.getString("PASSWORD"),
						rs.getInt("STATUS"),
						rs.getTimestamp("createdate"),
						rs.getString("username")
						);
				userList.add(outUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection(pstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}
	//获取用户列表
	public List<UserBean> queryUserlist(){
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<UserBean> userlist = new ArrayList<>();
		String sql= "select * from user_inf";
		try {
			System.out.println("开始建立连接");
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserBean outUser = new UserBean(
						rs.getInt("ID"),
						rs.getString("loginname"),
						rs.getString("PASSWORD"),
						rs.getInt("STATUS"),
						rs.getTimestamp("createdate"),
						rs.getString("username")
						);
				userlist.add(outUser);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection(pstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userlist;
		
	}
	//删除用户  超级用户有效
	@Override
	public int deleteUser(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "delete from user_inf where ID=?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, id);
			pos = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源
				closeConnection(pstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	//添加用户
	@Override
	public int insertUser(UserBean user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "insert into user_inf(loginname,PASSWORD,STATUS,createdate,username) values(?,?,?,?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getLoginname());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getStatus());
			pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
			pstmt.setString(5, user.getUsername());
			
			pos = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源
				closeConnection(pstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	//编辑用户
	@Override
	public int updateUser(UserBean userUpdate, int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement prstmt = null;
		int pos = 0;
		String sql = "update user_inf set loginname=?,PASSWORD=?,STATUS=?,username=? where id=?";
		
		try {
			connection = getConnection();
			prstmt = connection.prepareStatement(sql);
			prstmt.setString(1, userUpdate.getLoginname());
			prstmt.setString(2, userUpdate.getPassword());
			prstmt.setInt(3, userUpdate.getStatus());
			prstmt.setString(4, userUpdate.getUsername());
			prstmt.setInt(5, id);
			pos = prstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源
				closeConnection(prstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	
	//查找用户
	public List<UserBean> queryUser(UserBean user){
		List<UserBean> userlist = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer("select * from user_inf t where 1=1 ");
		if(user!=null) {
			if (user.getLoginname() != null && !user.getLoginname().equals("")) {
				sqlBuf.append(" and t.loginname like '" + user.getLoginname() + "%'");
			}
			if (user.getUsername() != null && !user.getUsername().equals("")) {
				sqlBuf.append(" and t.username like '" + user.getUsername() + "%'");
			}
			if (user.getStatus() != null) {
				sqlBuf.append(" and t.STATUS =" + user.getStatus());
			}
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				UserBean outUser = new UserBean();
				outUser.setId(rs.getInt("ID"));
				outUser.setLoginname(rs.getString("loginname"));
				outUser.setPassword(rs.getString("PASSWORD"));
				outUser.setStatus(rs.getInt("STATUS"));
				outUser.setCreatedate(rs.getTimestamp("createdate"));
				outUser.setUsername(rs.getString("username"));
				userlist.add(outUser);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				closeConnection(null,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userlist;
	}
	//分页查找用户
	@Override
	public Pager<UserBean> findUser(UserBean userModel,int pageNum,int pageSize){
		Pager<UserBean> result = null ;
		List<UserBean> userlist = new ArrayList<>();
		//获取总记录条数
		int totalRecord = queryUserCount(userModel);
		//获取总页数
		int totalPage = totalRecord / pageSize;
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		StringBuffer sqlBuf = new StringBuffer("select * from user_inf t where 1=1 ");
		if(userModel!=null) {
			if (userModel.getLoginname() != null && !userModel.getLoginname().equals("")) {
				sqlBuf.append(" and t.loginname like '" + userModel.getLoginname() + "%'");
			}
			if (userModel.getUsername() != null && !userModel.getUsername().equals("")) {
				sqlBuf.append(" and t.username like '" + userModel.getUsername() + "%'");
			}
			if (userModel.getStatus() != null) {
				sqlBuf.append(" and t.STATUS =" + userModel.getStatus());
			}
		}
		int fromIndex	= pageSize * (pageNum -1);
		//int toIndex  = pageSize * pageNum > this.totalRecord ? this.totalRecord : pageSize * pageNum;
		sqlBuf.append(" limit "+fromIndex+","+pageSize);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				UserBean outUser = new UserBean();
				outUser.setId(rs.getInt("ID"));
				outUser.setLoginname(rs.getString("loginname"));
				outUser.setPassword(rs.getString("PASSWORD"));
				outUser.setStatus(rs.getInt("STATUS"));
				outUser.setCreatedate(rs.getTimestamp("createdate"));
				outUser.setUsername(rs.getString("username"));
				userlist.add(outUser);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				closeConnection(null,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		result = new Pager<UserBean>(pageSize, pageNum, 
					totalRecord, totalPage, userlist);
		return result;
	}
	//统计user表的数据数
	@Override
	public int queryUserCount(UserBean user) {
		// TODO Auto-generated method stub
		// 定义查询user_inf表
		StringBuffer sqlBuf = new StringBuffer("select count(*) from user_inf t where 1=1");
		if (user.getLoginname() != null && !user.getLoginname().equals("")) {
			sqlBuf.append(" and t.loginname like '" + user.getLoginname() + "%'");
		}

		if (user.getUsername() != null && !user.getUsername().equals("")) {
			sqlBuf.append(" and t.username like '" + user.getUsername() + "%'");
		}

		if (user.getStatus() != null) {
			sqlBuf.append(" and t.STATUS =" + user.getStatus());
		}

		Connection conn = null;
		java.sql.Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				int count = rs.getInt(1);
				return count;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}
				closeConnection(null, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return 0;
	}
/*	@Override
	public int registerUser(UserBean user) {
		// TODO Auto-generated method stub
		return 0;
	}*/
	@Override
	public List<UserBean> checkLoginname(String loginname) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<UserBean> userList = new ArrayList<>();
		String sql= "select * from user_inf where loginname=? ";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, loginname);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				UserBean outUser = new UserBean();
				outUser.setId(rs.getInt("ID"));
				outUser.setLoginname(rs.getString("loginname"));
				outUser.setPassword(rs.getString("PASSWORD"));
				outUser.setStatus(rs.getInt("STATUS"));
				outUser.setCreatedate(rs.getTimestamp("createdate"));
				outUser.setUsername(rs.getString("username"));
				userList.add(outUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection(pstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userList;
	}
	
}
