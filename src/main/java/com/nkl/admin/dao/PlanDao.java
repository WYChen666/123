package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Plan;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class PlanDao extends BaseDao {

	public void addPlan(Plan plan){
		super.add(plan);
	}

	public void delPlan(Integer plan_id){
		super.del(Plan.class, plan_id);
	}

	public void delPlans(String[] plan_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <plan_ids.length; i++) {
			sBuilder.append(plan_ids[i]);
			if (i !=plan_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Plan WHERE plan_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updatePlan(Plan plan){
		Plan _plan = (Plan)super.get(Plan.class, plan.getPlan_id());
		if (plan.getPlan_year()!=0) {
			_plan.setPlan_year(plan.getPlan_year());
		}
		if (plan.getPlan_year_half()!=0) {
			_plan.setPlan_year_half(plan.getPlan_year_half());
		}
		if (plan.getClazz_id()!=0) {
			_plan.setClazz_id(plan.getClazz_id());
		}
		if (plan.getCourse_id()!=0) {
			_plan.setCourse_id(plan.getCourse_id());
		}
		if (plan.getUser_id()!=0) {
			_plan.setUser_id(plan.getUser_id());
		}
		super.update(_plan);
	}

	@SuppressWarnings("rawtypes")
	public Plan getPlan(Plan plan){
		Plan _plan=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Plan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = ? ");
			paramsList.add(plan.getPlan_id());
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + plan.getUser_id() +" ");
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
			_plan = (Plan)list.get(0);
			Clazz clazz = (Clazz)super.get(Clazz.class, _plan.getClazz_id());
			if (clazz!=null) {
				_plan.setClazz_name(clazz.getClazz_name());
			}
			Course course = (Course)super.get(Course.class, _plan.getCourse_id());
			if (course!=null) {
				_plan.setCourse_name(course.getCourse_name());
			}
			User user = (User)super.get(User.class, _plan.getUser_id());
			if (user!=null) {
				_plan.setReal_name(user.getReal_name());
			}
		}

		return _plan;
	}

	@SuppressWarnings("rawtypes")
	public List<Plan>  listPlans(Plan plan){
		List<Plan> plans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Plan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = ? ");
			paramsList.add(plan.getPlan_id());
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + plan.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(plan.getReal_name())) {
			sBuilder.append(" and user_id in (select user_id from User where real_name like '%" + plan.getReal_name() +"%') ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by plan_id asc ");

		List list = null;
		if (plan.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, plan.getStart(), plan.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			plans = new ArrayList<Plan>();
			for (Object object : list) {
				Plan _plan = (Plan)object;
				Clazz clazz = (Clazz)super.get(Clazz.class, _plan.getClazz_id());
				if (clazz!=null) {
					_plan.setClazz_name(clazz.getClazz_name());
				}
				Course course = (Course)super.get(Course.class, _plan.getCourse_id());
				if (course!=null) {
					_plan.setCourse_name(course.getCourse_name());
				}
				User user = (User)super.get(User.class, _plan.getUser_id());
				if (user!=null) {
					_plan.setReal_name(user.getReal_name());
				}
				plans.add(_plan);
			}
		}

		return plans;
	}

	public int  listPlansCount(Plan plan){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Plan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (plan.getPlan_id()!=0) {
			sBuilder.append(" and plan_id = ? ");
			paramsList.add(plan.getPlan_id());
		}
		if (plan.getPlan_year()!=0) {
			sBuilder.append(" and plan_year = " + plan.getPlan_year() +" ");
		}
		if (plan.getPlan_year_half()!=0) {
			sBuilder.append(" and plan_year_half = " + plan.getPlan_year_half() +" ");
		}
		if (plan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + plan.getClazz_id() +" ");
		}
		if (plan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + plan.getCourse_id() +" ");
		}
		if (plan.getUser_id()!=0) {
			sBuilder.append(" and user_id = " + plan.getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(plan.getReal_name())) {
			sBuilder.append(" and user_id in (select user_id from User where real_name like '%" + plan.getReal_name() +"%') ");
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

}
