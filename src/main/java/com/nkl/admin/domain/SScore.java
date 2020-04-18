package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

@SuppressWarnings("serial")
public class SScore extends BaseDomain {
	private int sscore_id; //
	private int user_id; //
	private int course_id; //
	private String score_value1; //
	private String score_value2; //
	private String score_value3; //
	private String score_value4; //
	private String score_value5; //
	private int score_year; //
	private int score_year_half; // 1-上半年 2-下半年

	private int flag;
	private String piid;
	private String bf1;
	private String bf2;

	private String user_name; //
	private int user_sex; //
	private String real_name; //
	private String course_name; //
	private int course_type; //
	private int clazz_id; //
	private String clazz_name;

	private double score_valueMin;
	private double score_valueMax;
	private int teacher_id; //

	private String ids;
	private String random;

	public String getScore_year_halfDesc() {
		switch (score_year_half) {
		case 1:
			return "上半年";
		case 2:
			return "下半年";
		default:
			return "";
		}
	}

	public String getCourse_typeDesc() {
		switch (course_type) {
		case 1:
			return "必修课";
		case 2:
			return "选修课";
		default:
			return "";
		}
	}

	public String getUser_sexDesc() {
		switch (user_sex) {
		case 1:
			return "男";
		case 2:
			return "女";
		default:
			return "男";
		}
	}

	public void setSscore_id(int sscore_id) {
		this.sscore_id = sscore_id;
	}

	public int getSscore_id() {
		return sscore_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setScore_year(int score_year) {
		this.score_year = score_year;
	}

	public int getScore_year() {
		return score_year;
	}

	public void setScore_year_half(int score_year_half) {
		this.score_year_half = score_year_half;
	}

	public int getScore_year_half() {
		return score_year_half;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public int getClazz_id() {
		return clazz_id;
	}

	public void setClazz_id(int clazz_id) {
		this.clazz_id = clazz_id;
	}

	public String getClazz_name() {
		return clazz_name;
	}

	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}

	public double getScore_valueMin() {
		return score_valueMin;
	}

	public void setScore_valueMin(double score_valueMin) {
		this.score_valueMin = score_valueMin;
	}

	public double getScore_valueMax() {
		return score_valueMax;
	}

	public void setScore_valueMax(double score_valueMax) {
		this.score_valueMax = score_valueMax;
	}

	public int getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getCourse_type() {
		return course_type;
	}

	public void setCourse_type(int course_type) {
		this.course_type = course_type;
	}

	public String getScore_value1() {
		return score_value1;
	}

	public String getScore_value2() {
		return score_value2;
	}

	public String getScore_value3() {
		return score_value3;
	}

	public String getScore_value4() {
		return score_value4;
	}

	public String getScore_value5() {
		return score_value5;
	}

	public void setScore_value1(String score_value1) {
		this.score_value1 = score_value1;
	}

	public void setScore_value2(String score_value2) {
		this.score_value2 = score_value2;
	}

	public void setScore_value3(String score_value3) {
		this.score_value3 = score_value3;
	}

	public void setScore_value4(String score_value4) {
		this.score_value4 = score_value4;
	}

	public void setScore_value5(String score_value5) {
		this.score_value5 = score_value5;
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

}
