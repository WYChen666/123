package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Cplan;
import com.nkl.common.dao.BaseDao;

@Repository
public class CplanDao extends BaseDao {

	public void addCplan(Cplan cplan){
		super.add(cplan);
	}

	public void delCplan(Integer cplan_id){
		super.del(Cplan.class, cplan_id);
	}

	public void delCplans(String[] cplan_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <cplan_ids.length; i++) {
			sBuilder.append(cplan_ids[i]);
			if (i !=cplan_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Cplan WHERE cplan_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateCplan(Cplan cplan){
		Cplan _cplan = (Cplan)super.get(Cplan.class, cplan.getCplan_id());
		if (cplan.getCplan_year()!=0) {
			_cplan.setCplan_year(cplan.getCplan_year());
		}
		if (cplan.getCplan_year_half()!=0) {
			_cplan.setCplan_year_half(cplan.getCplan_year_half());
		}
		if (cplan.getClazz_id()!=0) {
			_cplan.setClazz_id(cplan.getClazz_id());
		}
		if (cplan.getCplan_week()!=0) {
			_cplan.setCplan_week(cplan.getCplan_week());
		}
		if (cplan.getCplan_lesson()!=0) {
			_cplan.setCplan_lesson(cplan.getCplan_lesson());
		}
		if (cplan.getCourse_id()!=0) {
			_cplan.setCourse_id(cplan.getCourse_id());
		}
		super.update(_cplan);
	}

	@SuppressWarnings("rawtypes")
	public Cplan getCplan(Cplan cplan){
		Cplan _cplan=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Cplan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = ? ");
			paramsList.add(cplan.getCplan_id());
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + cplan.getCourse_id() +" ");
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
			_cplan = (Cplan)list.get(0);
			Clazz clazz = (Clazz)super.get(Clazz.class, _cplan.getClazz_id());
			if (clazz!=null) {
				_cplan.setClazz_name(clazz.getClazz_name());
			}
			Course course = (Course)super.get(Course.class, _cplan.getCourse_id());
			if (course!=null) {
				_cplan.setCourse_name(course.getCourse_name());
			}
		}

		return _cplan;
	}

	@SuppressWarnings("rawtypes")
	public List<Cplan>  listCplans(Cplan cplan){
		List<Cplan> cplans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Cplan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = ? ");
			paramsList.add(cplan.getCplan_id());
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + cplan.getCourse_id() +" ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by clazz_id,cplan_week,cplan_lesson,cplan_id asc ");

		List list = null;
		if (cplan.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, cplan.getStart(), cplan.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			cplans = new ArrayList<Cplan>();
			for (Object object : list) {
				Cplan _cplan = (Cplan)object;
				Clazz clazz = (Clazz)super.get(Clazz.class, _cplan.getClazz_id());
				if (clazz!=null) {
					_cplan.setClazz_name(clazz.getClazz_name());
				}
				Course course = (Course)super.get(Course.class, _cplan.getCourse_id());
				if (course!=null) {
					_cplan.setCourse_name(course.getCourse_name());
				}
				cplans.add(_cplan);
			}
		}

		return cplans;
	}

	public int  listCplansCount(Cplan cplan){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Cplan WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (cplan.getCplan_id()!=0) {
			sBuilder.append(" and cplan_id = ? ");
			paramsList.add(cplan.getCplan_id());
		}
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and clazz_id = " + cplan.getClazz_id() +" ");
		}
		if (cplan.getCplan_week()!=0) {
			sBuilder.append(" and cplan_week = " + cplan.getCplan_week() +" ");
		}
		if (cplan.getCplan_lesson()!=0) {
			sBuilder.append(" and cplan_lesson = " + cplan.getCplan_lesson() +" ");
		}
		if (cplan.getCourse_id()!=0) {
			sBuilder.append(" and course_id = " + cplan.getCourse_id() +" ");
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
	
	@SuppressWarnings("unchecked")
	public List<Cplan> listCplansByClazz(Cplan cplan){
		List<Cplan> cplans = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("select l.lesson as cplan_lesson,t.week1_lesson,t.week2_lesson,t.week3_lesson,t.week4_lesson,t.week5_lesson ");
		sBuilder.append("  from lessons l left join ( ");
		sBuilder.append("select p.cplan_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=1 then c.course_name else '' end) separator '') week1_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=2 then c.course_name else '' end) separator '') week2_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=3 then c.course_name else '' end) separator '') week3_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=4 then c.course_name else '' end) separator '') week4_lesson, ");
		sBuilder.append("  GROUP_CONCAT((case when p.cplan_week=5 then c.course_name else '' end) separator '') week5_lesson ");
		sBuilder.append("  FROM cplan p join course c on p.course_id=c.course_id ");
		sBuilder.append("  WHERE 1=1 ");
		if (cplan.getCplan_year()!=0) {
			sBuilder.append(" and cplan_year = " + cplan.getCplan_year() +" ");
		}
		if (cplan.getCplan_year_half()!=0) {
			sBuilder.append(" and cplan_year_half = " + cplan.getCplan_year_half() +" ");
		}
		if (cplan.getClazz_id()!=0) {
			sBuilder.append(" and p.clazz_id = " + cplan.getClazz_id() +" ");
		}
		sBuilder.append("  GROUP by p.cplan_lesson ORDER by p.cplan_lesson) t on l.lesson=t.cplan_lesson");
		sBuilder.append("  ORDER by l.lesson ");
		
		String[] scalars = {"cplan_lesson","week1_lesson","week2_lesson","week3_lesson","week4_lesson","week5_lesson"};
		Type[] types = {Hibernate.INTEGER,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING};


		List<Object> list = super.executeQueryJavaBeanSql(sBuilder.toString(), cplan.getClass(), null, scalars, types);
		if (list != null && list.size() > 0) {
			cplans = new ArrayList<Cplan>();
			for (Object object : list) {
				cplans.add((Cplan)object);
			}
		}
		return cplans;
	}

}
