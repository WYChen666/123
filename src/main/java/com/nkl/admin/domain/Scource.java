package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Scource extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2314005237608205836L;
	private int scource_id; //
	private int scource_year; //
	private int scource_year_half; // 1-上半年 2-下半年
	private int course_id; //
	private int user_id; //

	private int flag;
	private String piid;
	private String bf1;
	private String bf2;

	private String course_name; //
	private String user_name; //
	private String real_name; //
	private String clazz_name; //
	private String teacher_name; //
	private int user_count; //

	private String ids;
	private String random;

	public String getScource_year_halfDesc() {
		switch (scource_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}

	public void setScource_id(int scource_id) {
		this.scource_id = scource_id;
	}

	public int getScource_id() {
		return scource_id;
	}

	public void setScource_year(int scource_year) {
		this.scource_year = scource_year;
	}

	public int getScource_year() {
		return scource_year;
	}

	public void setScource_year_half(int scource_year_half) {
		this.scource_year_half = scource_year_half;
	}

	public int getScource_year_half() {
		return scource_year_half;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getTeacher_name() {
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}

	public int getUser_count() {
		return user_count;
	}

	public void setUser_count(int user_count) {
		this.user_count = user_count;
	}

	public String getUser_name() {
		return user_name;
	}

	public String getClazz_name() {
		return clazz_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getPiid() {
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	public String getBf1() {
		return bf1;
	}

	public void setBf1(String bf1) {
		this.bf1 = bf1;
	}

	public String getBf2() {
		return bf2;
	}

	public void setBf2(String bf2) {
		this.bf2 = bf2;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
