package com.test.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.test.bean.DeptBean;
import com.test.bean.EmplBean;
import com.test.bean.JobBean;
import com.test.bean.Pager;
import com.test.dao.BaseDao;
import com.test.dao.IEmplDao;

public class EmplDaoImpl extends BaseDao implements IEmplDao{

	@Override
	public List<EmplBean> queryAllEmp() {
		// TODO Auto-generated method stub
		List<EmplBean> empllist = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer(" select t.* from( select e.*,j.NAME as job_name from "
				+ " (select e.*,d.NAME as dept_name from employee_inf e " + "  left join dept_inf d on e.DEPT_ID=d.ID"
				+ " ) e left join job_inf j on e.JOB_ID=j.ID ) t where 1=1");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				EmplBean outEmp = new EmplBean();
				copyDataBean(rs,outEmp);
				empllist.add(outEmp);
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
		return empllist;
	}
	@Override
	public List<EmplBean> queryEmpllist(EmplBean empl) {
		// TODO Auto-generated method stub
		System.out.println("queryEmpllist");
		List<EmplBean> empllist = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer(" select t.* from( select e.*,j.NAME as job_name from "
				+ " (select e.*,d.NAME as dept_name from employee_inf e " + "  left join dept_inf d on e.DEPT_ID=d.ID"
				+ " ) e left join job_inf j on e.JOB_ID=j.ID ) t where 1=1");
		if (empl.getJob() != null && empl.getJob().getId() != null) {
			sqlBuf.append(" and JOB_ID = " + empl.getJob().getId());
		}

		if (empl.getDept() != null && empl.getDept().getId() != null) {
			sqlBuf.append(" and DEPT_ID = " + empl.getDept().getId());
		}

		if (empl.getName() != null && !empl.getName().equals("")) {
			sqlBuf.append(" and NAME like '" + empl.getName() + "%'");
		}

		if (empl.getCardId() != null && !empl.getCardId().equals("")) {
			sqlBuf.append(" and CARD_ID like '" + empl.getCardId() + "%'");
		}

		if (empl.getPhone() != null && !empl.getPhone().equals("")) {
			sqlBuf.append(" and PHONE like '" + empl.getPhone() + "%'");
		}

		if (empl.getSex() != null) {
			sqlBuf.append(" and  SEX = " + empl.getSex());
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				EmplBean outEmp = new EmplBean();
				copyDataBean(rs,outEmp);
				empllist.add(outEmp);
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
		return empllist;
	}
	//删除员工
	@Override
	public int deleteEmpl(int id) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement prstmt = null;
		int pos = 0;
		String sql = "delete from employee_inf where ID=?";
		try {
			connection = getConnection();
			prstmt = connection.prepareStatement(sql);
			prstmt.setInt(1, id);
			
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
	@Override
	public int insertEmpl(EmplBean empl) {
		int pos = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "insert into employee_inf (NAME,CARD_ID,ADDRESS,POST_CODE,TEL,PHONE,QQ_NUM,EMAIL,SEX,PARTY,BIRTHDAY,RACE,EDUCATION,SPECIALITY,HOBBY,REMARK,CREATE_DATE,DEPT_ID,JOB_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, empl.getName());
			pstmt.setString(2, empl.getCardId());
			pstmt.setString(3, empl.getAddress());
			pstmt.setString(4, empl.getPostCode());
			pstmt.setString(5, empl.getTel());
			pstmt.setString(6, empl.getPhone());
			pstmt.setString(7, empl.getQqNum());
			pstmt.setString(8, empl.geteMail());
			pstmt.setInt(9, empl.getSex());
			pstmt.setString(10, empl.getParty());
			if(empl.getBirthday()!=null) {
				pstmt.setDate(11, new Date(empl.getBirthday().getTime()));
			}else
				pstmt.setDate(11, null);
			pstmt.setString(12, empl.getRace());
			pstmt.setString(13, empl.getEducation());
			pstmt.setString(14, empl.getSpeciality());
			pstmt.setString(15, empl.getHobby());
			pstmt.setString(16, empl.getRemark());
			pstmt.setTimestamp(17, new Timestamp(new java.util.Date().getTime()));
			System.out.println("Dept:"+empl.getDept().getId());
			pstmt.setInt(18, empl.getDept().getId());
			pstmt.setInt(19, empl.getJob().getId());
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
	@Override
	public int updateEmpl(EmplBean emplUpdate, int id) {
		// TODO Auto-generated method stub
		int pos = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "update employee_inf set NAME=?,CARD_ID=?,ADDRESS=?,POST_CODE=?,TEL=?,PHONE=?,QQ_NUM=?,EMAIL=?,SEX=?,PARTY=?,BIRTHDAY=?,RACE=?,EDUCATION=?,SPECIALITY=?,HOBBY=?,REMARK=?,DEPT_ID=?,JOB_ID=? where id =?";
		System.out.println("Empl id is"+id);
		System.out.println("emplUpdate is "+emplUpdate);
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, emplUpdate.getName());
			pstmt.setString(2, emplUpdate.getCardId());
			pstmt.setString(3, emplUpdate.getAddress());
			pstmt.setString(4, emplUpdate.getPostCode());
			pstmt.setString(5, emplUpdate.getTel());
			pstmt.setString(6, emplUpdate.getPhone());
			pstmt.setString(7, emplUpdate.getQqNum());
			pstmt.setString(8, emplUpdate.geteMail());
			pstmt.setInt(9, emplUpdate.getSex());
			pstmt.setString(10, emplUpdate.getParty());
			if(emplUpdate.getBirthday()!=null) {
				pstmt.setDate(11, new Date(emplUpdate.getBirthday().getTime()));
			}else
				pstmt.setDate(11, null);
			pstmt.setString(12, emplUpdate.getRace());
			pstmt.setString(13, emplUpdate.getEducation());
			pstmt.setString(14, emplUpdate.getSpeciality());
			pstmt.setString(15, emplUpdate.getHobby());
			pstmt.setString(16, emplUpdate.getRemark());
			//pstmt.setTimestamp(17, new Timestamp(new java.util.Date().getTime()));
			//System.out.println("Dept:"+emplUpdate.getDept().getId());
			pstmt.setInt(17, emplUpdate.getDept().getId());
			pstmt.setInt(18, emplUpdate.getJob().getId());
			
			pstmt.setInt(19, id);
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
	@Override
	public int isCardIdExist(String cardId) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		int id = -1;
		String sql = "select ID from employee_inf where CARD_ID =?";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cardId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				id = rs.getInt(1);
			}
			
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
		return id;
	}
	
	
	@Override
	public Pager<EmplBean> findEmpl(EmplBean emplModel, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Pager<EmplBean> result = null;
		List<EmplBean> empllist = new ArrayList<>();
		int totalRecord = queryEmplCount(emplModel);
		int totalPage = totalRecord / pageSize;
		//获取总页数
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		StringBuffer sqlBuf = new StringBuffer(" select t.* from( select e.*,j.NAME as job_name from "
				+ " (select e.*,d.NAME as dept_name from employee_inf e " + "  left join dept_inf d on e.DEPT_ID=d.ID"
				+ " ) e left join job_inf j on e.JOB_ID=j.ID ) t where 1=1");
		if (emplModel.getJob() != null && emplModel.getJob().getId() != null) {
			sqlBuf.append(" and JOB_ID = " + emplModel.getJob().getId());
		}

		if (emplModel.getDept() != null && emplModel.getDept().getId() != null) {
			sqlBuf.append(" and DEPT_ID = " + emplModel.getDept().getId());
		}

		if (emplModel.getName() != null && !emplModel.getName().equals("")) {
			sqlBuf.append(" and NAME like '" + emplModel.getName() + "%'");
		}

		if (emplModel.getCardId() != null && !emplModel.getCardId().equals("")) {
			sqlBuf.append(" and CARD_ID like '" + emplModel.getCardId() + "%'");
		}

		if (emplModel.getPhone() != null && !emplModel.getPhone().equals("")) {
			sqlBuf.append(" and PHONE like '" + emplModel.getPhone() + "%'");
		}

		if (emplModel.getSex() != null) {
			sqlBuf.append(" and  SEX = " + emplModel.getSex());
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
				EmplBean outEmp = new EmplBean();
				copyDataBean(rs,outEmp);
				empllist.add(outEmp);
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
		result = new Pager<EmplBean>(pageSize, pageNum, 
				totalRecord, totalPage, empllist);
		return result;
	}
	@Override
	public int queryEmplCount(EmplBean empl) {
		// TODO Auto-generated method stub
		System.out.println("queryEmpllist");
		StringBuffer sqlBuf = new StringBuffer(" select count(*) from( select e.*,j.NAME as job_name from "
				+ " (select e.*,d.NAME as dept_name from employee_inf e " + "  left join dept_inf d on e.DEPT_ID=d.ID"
				+ " ) e left join job_inf j on e.JOB_ID=j.ID ) t where 1=1");
		if (empl.getJob() != null && empl.getJob().getId() != null) {
			sqlBuf.append(" and JOB_ID = " + empl.getJob().getId());
		}

		if (empl.getDept() != null && empl.getDept().getId() != null) {
			sqlBuf.append(" and DEPT_ID = " + empl.getDept().getId());
		}

		if (empl.getName() != null && !empl.getName().equals("")) {
			sqlBuf.append(" and NAME like '" + empl.getName() + "%'");
		}

		if (empl.getCardId() != null && !empl.getCardId().equals("")) {
			sqlBuf.append(" and CARD_ID like '" + empl.getCardId() + "%'");
		}

		if (empl.getPhone() != null && !empl.getPhone().equals("")) {
			sqlBuf.append(" and PHONE like '" + empl.getPhone() + "%'");
		}

		if (empl.getSex() != null) {
			sqlBuf.append(" and  SEX = " + empl.getSex());
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlBuf.toString());

			while (rs.next()) {
				int count = 0 ;
				count = rs.getInt(1);
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
	public void copyDataBean(ResultSet rs, EmplBean outEmployee) throws SQLException {

		outEmployee.setId(rs.getInt("ID"));

		DeptBean dept = new DeptBean();
		dept.setId(rs.getInt("DEPT_ID"));
		dept.setName(rs.getString("dept_name"));
		outEmployee.setDept(dept);

		JobBean job = new JobBean();
		job.setId(rs.getInt("JOB_ID"));
		job.setName(rs.getString("job_name"));
		outEmployee.setJob(job);

		outEmployee.setName(rs.getString("NAME"));
		outEmployee.setCardId(rs.getString("CARD_ID"));
		outEmployee.setAddress(rs.getString("ADDRESS"));
		outEmployee.setPostCode(rs.getString("POST_CODE"));
		outEmployee.setTel(rs.getString("TEL"));
		outEmployee.setPhone(rs.getString("PHONE"));
		outEmployee.setQqNum(rs.getString("QQ_NUM"));

		outEmployee.seteMail(rs.getString("EMAIL"));
		outEmployee.setSex(rs.getInt("SEX"));
		outEmployee.setParty(rs.getString("PARTY"));
		outEmployee.setBirthday(rs.getDate("BIRTHDAY"));
		outEmployee.setRace(rs.getString("RACE"));
		outEmployee.setEducation(rs.getString("EDUCATION"));

		outEmployee.setSpeciality(rs.getString("SPECIALITY"));
		outEmployee.setHobby(rs.getString("HOBBY"));
		outEmployee.setRemark(rs.getString("REMARK"));
		outEmployee.setCreateDay(rs.getTimestamp("CREATE_DATE"));

	}
	
}
