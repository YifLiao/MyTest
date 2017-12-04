package com.test.bean;

import java.io.Serializable;
import java.util.Date;
/*CREATE TABLE user_inf (
ID INT(11) NOT NULL AUTO_INCREMENT,
loginname VARCHAR(20) NOT NULL,
PASSWORD VARCHAR(16) NOT NULL,
STATUS INT(11) NOT NULL DEFAULT '1',
createdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
username VARCHAR(20) DEFAULT NULL,
PRIMARY KEY (ID)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
*/
//用户
@SuppressWarnings("serial")
public class UserBean implements Serializable{
	private Integer id;        //用户ID
	private String loginname;  //登录名
	private String password;   //密码
	private Integer status;    //状态
	private Date createdate;   //创建时间
	private String username;   //用户名
	
	public UserBean() {
		super();
	}
	public UserBean(Integer id, String loginname, String password, Integer status, Date createdate, String username) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.status = status;
		this.createdate = createdate;
		this.username = username;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", loginname=" + loginname + ", password=" + password + ", status=" + status
				+ ", createdate=" + createdate + ", username=" + username + "]";
	}
	
}
