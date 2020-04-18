package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class ScoreDao extends BaseDao {

	public void addScore(Score score){
		super.add(score);
	}
	
	public void importScore(Score score){
		String sql = "insert into score(user_id,course_id,score_year,score_year_half,score_value) "
				   + "select t1.user_id,t2.course_id,"+score.getScore_year()+","+score.getScore_year_half()+","+score.getScore_value()+" from "
				   + "(select user_id from `user` u where u.user_name='"+score.getUser_name()+"') t1, "
				   + "(select course_id from course c where c.course_name='"+score.getCourse_name()+"') t2";
		Object[] params = null;
		super.executeUpdateSql(sql, params);
	}

	public void delScore(Integer score_id){
		super.del(Score.class, score_id);
	}

	public void delScores(String[] score_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <score_ids.length; i++) {
			sBuilder.append(score_ids[i]);
			if (i !=score_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Score WHERE score_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateScore(Score score){
		Score _score = (Score)super.get(Score.class, score.getScore_id());
		if (score.getScore_value()!=0) {
			score.setScore_value(score.getScore_value());
		}
		if (score.getScore_year()!=0) {
			score.setScore_year(score.getScore_year());
		}
		if (score.getScore_year_half()!=0) {
			score.setScore_year_half(score.getScore_year_half());
		}
		super.update(_score);
	}

	@SuppressWarnings("rawtypes")
	public Score getScore(Score score){
		Score _score=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Score s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_score = (Score)list.get(0);
			User user = (User)super.get(User.class, _score.getUser_id());
			if (user!=null) {
				_score.setUser_name(user.getUser_name());
				_score.setUser_sex(user.getUser_sex());
				_score.setReal_name(user.getReal_name());
				
				Clazz clazz = (Clazz)super.get(Clazz.class, user.getClazz_id());
				if (clazz!=null) {
					user.setClazz_id(clazz.getClazz_id());
					user.setClazz_name(clazz.getClazz_name());
				}
			}
			Course course = (Course)super.get(Course.class, _score.getCourse_id());
			if (course!=null) {
				_score.setCourse_name(course.getCourse_name());
				_score.setCourse_type(course.getCourse_type());
			}
			
		}

		return _score;
	}

	@SuppressWarnings("rawtypes")
	public List<Score>  listScores(Score score){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Score s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where user_name like '%" + score.getUser_name() +"%') ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where real_name like '%" + score.getReal_name() +"%') ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and s.course_id in (select course_id from Course where course_type=" + score.getCourse_type() +") ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and s.user_id in (select user_id from User where clazz_id=" + score.getClazz_id() +") ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and ((s.course_id,s.score_year,s.score_year_half) in (select course_id,plan_year,plan_year_half from Plan where user_id=" + score.getTeacher_id() +") ");
			sBuilder.append("    or s.course_id in (select cc.course_id from Course cc where cc.teacher_id=" + score.getTeacher_id() +")) ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by score_value desc,score_id asc ");

		List list = null;
		if (score.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, score.getStart(), score.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				Score _score = (Score)object;
				User user = (User)super.get(User.class, _score.getUser_id());
				if (user!=null) {
					_score.setUser_name(user.getUser_name());
					_score.setUser_sex(user.getUser_sex());
					_score.setReal_name(user.getReal_name());
					
					Clazz clazz = (Clazz)super.get(Clazz.class, user.getClazz_id());
					if (clazz!=null) {
						user.setClazz_id(clazz.getClazz_id());
						user.setClazz_name(clazz.getClazz_name());
					}
				}
				Course course = (Course)super.get(Course.class, _score.getCourse_id());
				if (course!=null) {
					_score.setCourse_name(course.getCourse_name());
					_score.setCourse_type(course.getCourse_type());
				}
				scores.add(_score);
			}
		}

		return scores;
	}

	public int  listScoresCount(Score score){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Score s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getScore_id()!=0) {
			sBuilder.append(" and score_id = ? ");
			paramsList.add(score.getScore_id());
		}
		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where user_name like '%" + score.getUser_name() +"%') ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where real_name like '%" + score.getReal_name() +"%') ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and s.course_id in (select course_id from Course where course_type=" + score.getCourse_type() +") ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and s.user_id in (select user_id from User where clazz_id=" + score.getClazz_id() +") ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getScore_valueMin()!=0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() +" ");
		}
		if (score.getScore_valueMax()!=0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and ((s.course_id,s.score_year,s.score_year_half) in (select course_id,plan_year,plan_year_half from Plan where user_id=" + score.getTeacher_id() +") ");
			sBuilder.append("    or s.course_id in (select cc.course_id from Course cc where cc.teacher_id=" + score.getTeacher_id() +")) ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}

	@SuppressWarnings("rawtypes")
	public List<Score>  listScoresSum(Score score){
		List<Score> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT u.user_name,u.real_name,u.user_sex,c.clazz_id,c.clazz_name,s.score_year,s.score_year_half, ");
		sBuilder.append("       sum(s.score_value) sec_sum FROM score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and ((s.course_id,s.score_year,s.score_year_half) in (select course_id,plan_year,plan_year_half from plan where user_id=" + score.getTeacher_id() +") ");
			sBuilder.append("    or s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +")) ");
		}
		sBuilder.append(" group by u.user_name,u.user_sex,u.real_name,c.clazz_id,c.clazz_name,s.score_year,s.score_year_half ");
		sBuilder.append(" order by s.score_year,s.score_year_half,u.user_name) t");

		String[] scalars = {"user_name","real_name","user_sex","clazz_id","clazz_name","score_year","score_year_half","sec_sum"};
		Type[] types = {Hibernate.STRING,Hibernate.STRING,Hibernate.INTEGER,Hibernate.INTEGER,Hibernate.STRING,Hibernate.INTEGER,Hibernate.INTEGER,Hibernate.DOUBLE};

		List list = null;
		if (score.getStart()!=-1) {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), score.getClass(), null, scalars, types, score.getStart(), score.getLimit());
		}else {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), score.getClass(), null, scalars, types);
		}
		if (list != null && list.size() > 0) {
			scores = new ArrayList<Score>();
			for (Object object : list) {
				scores.add((Score)object);
			}
		}
		return scores;
	}

	@SuppressWarnings("rawtypes")
	public int  listScoresSumCount(Score score){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) s_count FROM score s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append("  join clazz c on c.clazz_id = u.clazz_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getUser_id()!=0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + score.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + score.getReal_name() +"%' ");
		}
		if (score.getCourse_id()!=0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() +" ");
		}
		if (score.getCourse_type()!=0) {
			sBuilder.append(" and co.course_type = " + score.getCourse_type() +" ");
		}
		if (score.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() +" ");
		}
		if (score.getScore_year()!=0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() +" ");
		}
		if (score.getScore_year_half()!=0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() +" ");
		}
		if (score.getTeacher_id()!=0) {
			sBuilder.append(" and ((s.course_id,s.score_year,s.score_year_half) in (select course_id,plan_year,plan_year_half from plan where user_id=" + score.getTeacher_id() +") ");
			sBuilder.append("    or s.course_id in (select cc.course_id from course cc where cc.teacher_id=" + score.getTeacher_id() +")) ");
		}
		sBuilder.append(" group by u.user_name,u.user_sex,u.real_name,c.clazz_id,c.clazz_name,s.score_year,s.score_year_half ");

		List list = super.executeQueryMapSql(sBuilder.toString(), null);
		if (list!=null) {
			Map map = (Map) list.get(0);
			sum = Integer.parseInt(map.get("s_count").toString());
		}
		return sum;
	}
}
