package com.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.Pager;
import com.test.dao.BaseDao;
import com.test.dao.IDeptDao;

public class DeptDaoImpl extends BaseDao implements IDeptDao{

	//��ȡ�����б�
	@Override
	public List<DeptBean> queryDeptlist() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<DeptBean> deptlist = new ArrayList<>();
		String sql= "select * from dept_inf";
		try {
			System.out.println("��ʼ��������");
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DeptBean outDept = new DeptBean(
						rs.getInt("ID"),
						rs.getString("name"),
						rs.getString("remark")
						);
				deptlist.add(outDept);
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
		return deptlist;
	}
	//ɾ������
	@Override
	public int deleteDept(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "delete from dept_inf where ID=?";
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
				//�ر���Դ
				closeConnection(pstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	//��Ӳ���
	@Override
	public int insertDept(DeptBean dept) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "insert into dept_inf(name,remark) values(?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, dept.getName());
			pstmt.setString(2, dept.getRemark());
	
			pos = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//�ر���Դ
				closeConnection(pstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	//�༭����
	@Override
	public int updateDept(DeptBean deptUpdate, int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement prstmt = null;
		int pos = 0;
		String sql = "update dept_inf set name=?,remark=? where id=?";
		
		try {
			connection = getConnection();
			prstmt = connection.prepareStatement(sql);
			prstmt.setString(1, deptUpdate.getName());
			prstmt.setString(2, deptUpdate.getRemark());

			prstmt.setInt(3, id);
			pos = prstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//�ر���Դ
				closeConnection(prstmt,connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}
	//������ȡ����
	@Override
	public List<DeptBean> queryDept(DeptBean dept) {
		// TODO Auto-generated method stub
		List<DeptBean> deptlist = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer("select * from dept_inf t where 1=1 ");
		if(dept!=null) {
			if (dept.getName() != null && !dept.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + dept.getName() + "%'");
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
				DeptBean outDept = new DeptBean();
				outDept.setId(rs.getInt("ID"));
				outDept.setName(rs.getString("name"));
				outDept.setRemark(rs.getString("remark"));
				deptlist.add(outDept);
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
		return deptlist;
	}
	//��ҳ����
	@Override
	public Pager<DeptBean> findDept(DeptBean deptModel,int pageNum,int pageSize){
		Pager<DeptBean> result = null;
		List<DeptBean> deptlist = new ArrayList<>();
		int totalRecord = queryDeptCount(deptModel);
		int totalPage = totalRecord / pageSize;
		//��ȡ��ҳ��
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		StringBuffer sqlBuf = new StringBuffer("select * from dept_inf t where 1=1 ");
		if(deptModel!=null) {
			if (deptModel.getName() != null && !deptModel.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + deptModel.getName() + "%'");
			}
		}
		int fromIndex	= pageSize * (pageNum -1);
		sqlBuf.append(" limit "+fromIndex+","+pageSize);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				DeptBean outDept = new DeptBean();
				outDept.setId(rs.getInt("ID"));
				outDept.setName(rs.getString("name"));
				outDept.setRemark(rs.getString("remark"));
				deptlist.add(outDept);
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
		result = new Pager<DeptBean>(pageSize, pageNum, 
				totalRecord, totalPage, deptlist);
		return result;
	}
	//ͳ�Ƽ�¼��
	@Override
	public int queryDeptCount(DeptBean dept) {
		// �����ѯuser_inf��
		StringBuffer sqlBuf = new StringBuffer("select count(*) from dept_inf t where 1=1 ");
		if(dept!=null) {
			if (dept.getName() != null && !dept.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + dept.getName() + "%'");
			}
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
}
