package com.test.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.test.bean.DocumentBean;
import com.test.bean.Pager;
import com.test.bean.UserBean;
import com.test.dao.BaseDao;
import com.test.dao.IDocumDao;

public class DocumDaoImpl extends BaseDao implements IDocumDao{
	
	//�ϴ��ļ�
	@Override
	public int insertDocument(DocumentBean docum) {
		// TODO Auto-generated method stub
		//��ȡ����
		Connection connection = null;
		PreparedStatement pstmt = null;
		int pos = 0;
		String sql = "insert into document_inf(TITLE,filename,REMARK,CREATE_DATE,bytes) values(?,?,?,?,?)";
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, docum.getTitle());
			pstmt.setString(2, docum.getFileName());
			pstmt.setString(3, docum.getRemark());
			pstmt.setTimestamp(4, new Timestamp(new Date().getTime()));
			pstmt.setBytes(5, docum.getBytes());
//			pstmt.setInt(5, docum.getUser().getId());
			
			pos = pstmt.executeUpdate();
			if(pos>0) {
				System.out.println("�ϴ��ɹ�");
			}else
				System.out.println("�ϴ�ʧ��");
			
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
		
		return pos;
	}
	
	//ɾ���ļ�
	@Override
	public int deleteDocument(int id) {
		// TODO Auto-generated method stub
		int pos =0 ;
		//��ȡ����
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sql = "delete from document_inf where ID=?";
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
				closeConnection(pstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return pos;
	}

	//��ѯ�ļ�
	@Override
	public List<DocumentBean> queryDocument(DocumentBean docum) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<DocumentBean> documLists = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer("select * from document_inf t where 1=1 ");
		if(docum!=null) {
			if (docum.getTitle() != null && !docum.getTitle().equals("")) {
				sqlBuf.append(" and t.TITLE like %'" + docum.getTitle() + "%'");
			}
		}
	
		try {
			System.out.println(sqlBuf.toString());
			connection = getConnection();
			pstmt = connection.prepareStatement(sqlBuf.toString());
			//pstmt.setString(1, docum.getTitle());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				DocumentBean outDocum = new DocumentBean();
				UserBean user = new UserBean();
				user.setId(rs.getInt("USER_ID"));
				outDocum.setId(rs.getInt("ID"));
				outDocum.setTitle(rs.getString("TITLE"));
				outDocum.setFileName(rs.getString("filename"));
				outDocum.setRemark(rs.getString("REMARK"));
				outDocum.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				outDocum.setUser(user);
				outDocum.setBytes(rs.getBytes("bytes"));
				documLists.add(outDocum);
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
		return documLists;
	}
	//�༭�ļ�
	@Override
	public int updateDocum(DocumentBean documUpdate,int id) {
		int pos = 0;
		Connection connection = null;
		PreparedStatement prstmt = null;
		String sql = "update document_inf set TITLE=?,REMARK=? where id=?";
		
		try {
			connection = getConnection();
			prstmt = connection.prepareStatement(sql);
			prstmt.setString(1, documUpdate.getTitle());
			prstmt.setString(2, documUpdate.getRemark());
			
			prstmt.setInt(3, id);
			pos = prstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection(prstmt, connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pos;
	}

	@Override
	public Pager<DocumentBean> findDocum(DocumentBean documModel, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Pager<DocumentBean> result = null;
		int totalRecord = queryDocumCount(documModel);
		int totalPage = totalRecord / pageSize;
		//��ȡ��ҳ��
		if(totalRecord % pageSize !=0){
			totalPage++;
		}
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		List<DocumentBean> documLists = new ArrayList<>();
		StringBuffer sqlBuf = new StringBuffer("select * from document_inf t where 1=1 ");
		if(documModel!=null) {
			if (documModel.getTitle() != null && !documModel.getTitle().equals("")) {
				sqlBuf.append(" and t.TITLE like %'" + documModel.getTitle() + "%'");
			}
		}
		int fromIndex	= pageSize * (pageNum -1);
		sqlBuf.append(" limit "+fromIndex+","+pageSize);
		try {
			System.out.println(sqlBuf.toString());
			connection = getConnection();
			pstmt = connection.prepareStatement(sqlBuf.toString());
			//pstmt.setString(1, docum.getTitle());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				DocumentBean outDocum = new DocumentBean();
				UserBean user = new UserBean();
				user.setId(rs.getInt("USER_ID"));
				outDocum.setId(rs.getInt("ID"));
				outDocum.setTitle(rs.getString("TITLE"));
				outDocum.setFileName(rs.getString("filename"));
				outDocum.setRemark(rs.getString("REMARK"));
				outDocum.setCreateDate(rs.getTimestamp("CREATE_DATE"));
				outDocum.setUser(user);
				outDocum.setBytes(rs.getBytes("bytes"));
				documLists.add(outDocum);
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
		result = new Pager<DocumentBean>(pageSize, pageNum, 
				totalRecord, totalPage, documLists);
		return result;
	}

	@Override
	public int queryDocumCount(DocumentBean docum) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement pstmt = null;
		StringBuffer sqlBuf = new StringBuffer("select count(*) from document_inf t where 1=1 ");
		if(docum!=null) {
			if (docum.getTitle() != null && !docum.getTitle().equals("")) {
				sqlBuf.append(" and t.TITLE like %'" + docum.getTitle() + "%'");
			}
		}
	
		try {
			System.out.println(sqlBuf.toString());
			connection = getConnection();
			pstmt = connection.prepareStatement(sqlBuf.toString());
			//pstmt.setString(1, docum.getTitle());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int count = rs.getInt(1);
				return count;
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
		return 0;
	}
	
	
	//�����ļ�
//	@Override
/*	public DocumentBean getFileBeanById(DocumentBean docum) {
		// TODO Auto-generated method stub
		ResultSet rs=null;
		// ��ȡ���ݿ�Connection����
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		DocumentBean outFileBean=new DocumentBean();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select id,filename,CREATE_DATE,bytes from document_inf where id=?");

			pstmt.setInt(1, docum.getId());
			rs=pstmt.executeQuery();
			
			while(rs.next())
			{
				Integer id=rs.getInt(1);
				String filename=rs.getString(2);
				Date savedTime=rs.getTimestamp(3);
				byte bytes[]=rs.getBytes(4);
				
				outFileBean.setId(id);
				outFileBean.setFileName(filename);
				outFileBean.setCreateDate(savedTime);
				outFileBean.setBytes(bytes);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeConnection(pstmt, conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return outFileBean;
	}
*/
}
