package com.test.domain;

import java.util.Date;

public class User {
	private String id;
	private String name;
	private String nickName;
	private String pass;
	private String address;
	private Date createDate;
	
	public User() {
		super();
	}

	public User(String id, String name, String nickName, String pass, String address, Date createDate) {
		super();
		this.id = id;
		this.name = name;
		this.nickName = nickName;
		this.pass = pass;
		this.address = address;
		this.createDate = createDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickName=" + nickName + ", pass=" + pass + ", address="
				+ address + ", createDate=" + createDate + "]";
	}
	
}
