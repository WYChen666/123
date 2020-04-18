package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class EvaluateDao extends BaseDao {

	public void addEvaluate(Evaluate evaluate){
		super.add(evaluate);
	}

	public void delEvaluate(Integer evaluate_id){
		super.del(Evaluate.class, evaluate_id);
	}

	public void delEvaluates(String[] evaluate_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <evaluate_ids.length; i++) {
			sBuilder.append(evaluate_ids[i]);
			if (i !=evaluate_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Evaluate WHERE evaluate_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateEvaluate(Evaluate evaluate){
		Evaluate _evaluate = (Evaluate)super.get(Evaluate.class, evaluate.getEvaluate_id());
		if(evaluate.getQuality_score()!=0){
			_evaluate.setQuality_score(evaluate.getQuality_score());
		}
		if(evaluate.getPost_score()!=0){
			_evaluate.setPost_score(evaluate.getPost_score());
		}
		if(evaluate.getSkill_score()!=0){
			_evaluate.setSkill_score(evaluate.getSkill_score());
		}
		if(evaluate.getCoach_score()!=0){
			_evaluate.setCoach_score(evaluate.getCoach_score());
		}
		super.update(_evaluate);
	}

	@SuppressWarnings("rawtypes")
	public Evaluate getEvaluate(Evaluate evaluate){
		Evaluate _evaluate=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Evaluate WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = ? ");
			paramsList.add(evaluate.getEvaluate_id());
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and student_id = " + evaluate.getStudent_id() +" ");
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
			_evaluate = (Evaluate)list.get(0);
			User user = (User)super.get(User.class, _evaluate.getTeacher_id());
			if (user!=null) {
				_evaluate.setTeacher_name(user.getReal_name());
			}
			User user2 = (User)super.get(User.class, _evaluate.getStudent_id());
			if (user2!=null) {
				_evaluate.setStudent_name(user2.getReal_name());
			}
		}

		return _evaluate;
	}

	@SuppressWarnings("rawtypes")
	public List<Evaluate>  listEvaluates(Evaluate evaluate){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Evaluate WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = ? ");
			paramsList.add(evaluate.getEvaluate_id());
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and student_id = " + evaluate.getStudent_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and teacher_id in (select user_id from User where real_name like '%" + evaluate.getTeacher_name() +"%') ");
		}
		if (!StringUtil.isEmptyString(evaluate.getStudent_name())) {
			sBuilder.append(" and student_id in (select user_id from User where real_name like '%" + evaluate.getStudent_name() +"%') ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by evaluate_id asc ");

		List list = null;
		if (evaluate.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, evaluate.getStart(), evaluate.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				Evaluate _evaluate = (Evaluate)object;
				User user = (User)super.get(User.class, _evaluate.getTeacher_id());
				if (user!=null) {
					_evaluate.setTeacher_name(user.getReal_name());
				}
				User user2 = (User)super.get(User.class, _evaluate.getStudent_id());
				if (user2!=null) {
					_evaluate.setStudent_name(user2.getReal_name());
				}
				evaluates.add(_evaluate);
			}
		}

		return evaluates;
	}

	public int  listEvaluatesCount(Evaluate evaluate){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Evaluate WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = ? ");
			paramsList.add(evaluate.getEvaluate_id());
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and student_id = " + evaluate.getStudent_id() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and teacher_id in (select user_id from User where real_name like '%" + evaluate.getTeacher_name() +"%') ");
		}
		if (!StringUtil.isEmptyString(evaluate.getStudent_name())) {
			sBuilder.append(" and student_id in (select user_id from User where real_name like '%" + evaluate.getStudent_name() +"%') ");
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
	public List<Evaluate>  listEvaluatesSum(Evaluate evaluate){
		List<Evaluate> evaluates = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT * FROM (");
		sBuilder.append("SELECT p.evaluate_year,p.evaluate_year_half,u1.real_name teacher_name, ");
		sBuilder.append("       avg(quality_score) quality_score_avg, ");
		sBuilder.append("       avg(post_score) post_score_avg, ");
		sBuilder.append("       avg(skill_score) skill_score_avg, ");
		sBuilder.append("       avg(coach_score) coach_score_avg, ");
		sBuilder.append("       avg(quality_score + post_score + skill_score + coach_score) sum_avg ");
		sBuilder.append("  FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		sBuilder.append(" group by p.evaluate_year,p.evaluate_year_half,u1.real_name ");
		sBuilder.append(" order by p.evaluate_year,p.evaluate_year_half,u1.real_name ) t");

		String[] scalars = {"evaluate_year","evaluate_year_half","teacher_name","quality_score_avg","post_score_avg","skill_score_avg","coach_score_avg","sum_avg"};
		Type[] types = {Hibernate.INTEGER,Hibernate.INTEGER,Hibernate.STRING,Hibernate.DOUBLE,Hibernate.DOUBLE,Hibernate.DOUBLE,Hibernate.DOUBLE,Hibernate.DOUBLE};

		List list = null;
		if (evaluate.getStart()!=-1) {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), evaluate.getClass(), null, scalars, types, evaluate.getStart(), evaluate.getLimit());
		}else {
			list = super.executeQueryJavaBeanSql(sBuilder.toString(), evaluate.getClass(), null, scalars, types);
		}
		if (list != null && list.size() > 0) {
			evaluates = new ArrayList<Evaluate>();
			for (Object object : list) {
				evaluates.add((Evaluate)object);
			}
		}
		return evaluates;
	}
	
	@SuppressWarnings("rawtypes")
	public int  listEvaluatesSumCount(Evaluate evaluate){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) s_count FROM evaluate p ");
		sBuilder.append("  join user u1 on p.teacher_id=u1.user_id WHERE 1=1 ");
		if (evaluate.getEvaluate_id()!=0) {
			sBuilder.append(" and evaluate_id = " + evaluate.getEvaluate_id() +" ");
		}
		if (evaluate.getEvaluate_year()!=0) {
			sBuilder.append(" and evaluate_year = " + evaluate.getEvaluate_year() +" ");
		}
		if (evaluate.getEvaluate_year_half()!=0) {
			sBuilder.append(" and evaluate_year_half = " + evaluate.getEvaluate_year_half() +" ");
		}
		if (!StringUtil.isEmptyString(evaluate.getTeacher_name())) {
			sBuilder.append(" and u1.real_name like '%" + evaluate.getTeacher_name() +"%' ");
		}
		if (evaluate.getTeacher_id()!=0) {
			sBuilder.append(" and p.teacher_id = " + evaluate.getTeacher_id() +" ");
		}
		if (evaluate.getStudent_id()!=0) {
			sBuilder.append(" and p.student_id = " + evaluate.getStudent_id() +" ");
		}
		sBuilder.append(" group by p.evaluate_year,p.evaluate_year_half,u1.real_name ");

		List list = super.executeQueryMapSql(sBuilder.toString(), null);
		if (list!=null) {
			Map map = (Map) list.get(0);
			sum = Integer.parseInt(map.get("s_count").toString());
		}
		return sum;
	}

}
