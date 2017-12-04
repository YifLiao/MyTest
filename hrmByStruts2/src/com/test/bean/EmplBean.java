package com.test.bean;

import java.io.Serializable;
import java.util.Date;

public class EmplBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;      //id
	private DeptBean dept;   //部门
	private JobBean job;     //职位
	private String name;     //姓名
	private String cardId;   //身份证
	private String address;  //地址
	private String postCode; //邮编
	private String tel;      //电话
	private String phone;    //手机
	private String qqNum;    //QQ  
	private String eMail;    //邮箱
	private Integer sex;     //性别 默认为1
	private String party;    //政治面貌
	private Date birthday;   //生日
	private String race;     //民族
	private String education;//学历 
	private String speciality;//专业
	private String hobby;     //爱好
	private String remark;    //备注
	private Date createDay;   //创建时间
	
	public EmplBean() {
		super();
	}

	public EmplBean(Integer id, DeptBean dept, JobBean job, String name, String cardId, String address, String postCode,
			String tel, String phone, String qqNum, String eMail, Integer sex, String party, Date birthday, String race,
			String education, String speciality, String hobby, String remark, Date createDay) {
		super();
		this.id = id;
		this.dept = dept;
		this.job = job;
		this.name = name;
		this.cardId = cardId;
		this.address = address;
		this.postCode = postCode;
		this.tel = tel;
		this.phone = phone;
		this.qqNum = qqNum;
		this.eMail = eMail;
		this.sex = sex;
		this.party = party;
		this.birthday = birthday;
		this.race = race;
		this.education = education;
		this.speciality = speciality;
		this.hobby = hobby;
		this.remark = remark;
		this.createDay = createDay;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DeptBean getDept() {
		return dept;
	}

	public void setDept(DeptBean dept) {
		this.dept = dept;
	}

	public JobBean getJob() {
		return job;
	}

	public void setJob(JobBean job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQqNum() {
		return qqNum;
	}

	public void setQqNum(String qqNum) {
		this.qqNum = qqNum;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDay() {
		return createDay;
	}

	public void setCreateDay(Date createDay) {
		this.createDay = createDay;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", job=" + job + ", name=" + name + ", cardId=" + cardId
				+ ", address=" + address + ", postCode=" + postCode + ", tel=" + tel + ", phone=" + phone + ", qqNum="
				+ qqNum + ", eMail=" + eMail + ", sex=" + sex + ", party=" + party + ", birthday=" + birthday
				+ ", race=" + race + ", education=" + education + ", speciality=" + speciality + ", hobby=" + hobby
				+ ", remark=" + remark + ", createDay=" + createDay + "]";
	}
}
