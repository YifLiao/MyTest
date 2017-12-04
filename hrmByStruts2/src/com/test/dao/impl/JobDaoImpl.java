package com.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.dao.BaseDao;
import com.test.dao.IJobDao;

public class JobDaoImpl extends BaseDao implements IJobDao{
	//获取职位列表
	@Override
	public List<JobBean> queryJoblist() {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<JobBean> joblist = new ArrayList<>();
		String sql= "select * from job_inf";
		try {
			//System.out.println("开始建立连接");
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JobBean outJob = new JobBean(
						rs.getInt("ID"),
						rs.getString("name"),
						rs.getString("remark")
						);
				joblist.add(outJob);
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
		return joblist;
	}
	//删除职位
	@Override
	public int deleteJob(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "delete from job_inf where ID=?";
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
	//添加职位
	@Override
	public int insertJob(JobBean job) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "insert into job_inf(name,remark) values(?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, job.getName());
			pstmt.setString(2, job.getRemark());
	
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
	//修改职位信息
	@Override
	public int updateJob(JobBean jobUpdate, int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement prstmt = null;
		int pos = 0;
		String sql = "update job_inf set name=?,remark=? where id=?";
		
		try {
			connection = getConnection();
			prstmt = connection.prepareStatement(sql);
			prstmt.setString(1, jobUpdate.getName());
			prstmt.setString(2, jobUpdate.getRemark());

			prstmt.setInt(3, id);
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
	//搜索职位
	@Override
	public List<JobBean> queryJob(JobBean job) {
		// TODO Auto-generated method stub
		List<JobBean> joblist = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer("select * from job_inf t where 1=1 ");
		if(job!=null) {
			if (job.getName() != null && !job.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + job.getName() + "%'");
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
				JobBean outJob = new JobBean();
				outJob.setId(rs.getInt("ID"));
				outJob.setName(rs.getString("name"));
				outJob.setRemark(rs.getString("remark"));
				joblist.add(outJob);
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
		return joblist;
	}
	//
	public Pager<JobBean> findJob(JobBean jobModel,int pageNum,int pageSize){
		Pager<JobBean> result = null;
		List<JobBean> joblist = new ArrayList<>();
		int totalRecord = queryJobCount(jobModel);
		int totalPage = totalRecord / pageSize;
		//获取总页数
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		StringBuffer sqlBuf = new StringBuffer("select * from job_inf t where 1=1 ");
		if(jobModel!=null) {
			if (jobModel.getName() != null && !jobModel.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + jobModel.getName() + "%'");
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
				JobBean outJob = new JobBean();
				outJob.setId(rs.getInt("ID"));
				outJob.setName(rs.getString("name"));
				outJob.setRemark(rs.getString("remark"));
				joblist.add(outJob);
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
		result = new Pager<JobBean>(pageSize, pageNum, 
				totalRecord, totalPage, joblist);
		return result;
	}
	//
	public int queryJobCount(JobBean job) {
		StringBuffer sqlBuf = new StringBuffer("select count(*) from job_inf t where 1=1 ");
		if(job!=null) {
			if (job.getName() != null && !job.getName().equals("")) {
				sqlBuf.append(" and t.name like '" + job.getName() + "%'");
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
				closeConnection(null,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
}
