package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class ScourceDao extends BaseDao {

	public void addScource(Scource scource) {
		super.add(scource);
	}

	public void delScource(Integer scource_id) {
		super.del(Scource.class, scource_id);
	}

	public void delScources(String[] scource_ids) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < scource_ids.length; i++) {
			sBuilder.append(scource_ids[i]);
			if (i != scource_ids.length - 1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Scource WHERE scource_id IN(" + sBuilder.toString() + ")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateScource(Scource scource) {
		Scource _scource = (Scource) super.get(Scource.class, scource.getScource_id());
		if (scource.getScource_year() != 0) {
			_scource.setScource_year(scource.getScource_year());
		}
		if (scource.getScource_year_half() != 0) {
			_scource.setScource_year_half(scource.getScource_year_half());
		}
		if (scource.getCourse_id() != 0) {
			_scource.setCourse_id(scource.getCourse_id());
		}
		if (scource.getUser_id() != 0) {
			_scource.setUser_id(scource.getUser_id());
		}
		super.update(_scource);
	}

	@SuppressWarnings("rawtypes")
	public Scource getScource(Scource scource) {
		Scource _scource = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Scource p WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (scource.getScource_id() != 0) {
			sBuilder.append(" and scource_id = ? ");
			paramsList.add(scource.getScource_id());
		}
		if (scource.getScource_year() != 0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() + " ");
		}
		if (scource.getScource_year_half() != 0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() + " ");
		}
		if (scource.getCourse_id() != 0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() + " ");
		}
		if (scource.getUser_id() != 0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() + " ");
		}

		Object[] params = null;
		if (paramsList.size() > 0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_scource = (Scource) list.get(0);
			Course course = (Course) super.get(Course.class, _scource.getCourse_id());
			if (course != null) {
				_scource.setCourse_name(course.getCourse_name());
				User user = (User) super.get(User.class, course.getUser_id());
				if (user != null) {
					_scource.setTeacher_name(user.getReal_name());
				}
			}
			User user = (User) super.get(User.class, _scource.getUser_id());
			if (user != null) {
				_scource.setUser_name(user.getUser_name());
				_scource.setReal_name(user.getReal_name());

				Clazz clazz = (Clazz) super.get(Clazz.class, user.getClazz_id());
				if (clazz != null) {
					_scource.setClazz_name(clazz.getClazz_name());
				}
			}
		}

		return _scource;
	}

	@SuppressWarnings("rawtypes")
	public List<Scource> listScources(Scource scource) {
		List<Scource> scources = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Scource p WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (scource.getScource_id() != 0) {
			sBuilder.append(" and scource_id = ? ");
			paramsList.add(scource.getScource_id());
		}
		if (scource.getScource_year() != 0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() + " ");
		}
		if (scource.getScource_year_half() != 0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() + " ");
		}
		if (scource.getCourse_id() != 0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() + " ");
		}
		if (!StringUtil.isEmptyString(scource.getCourse_name())) {
			sBuilder.append(" and p.course_id in (select course_id from Course where course_name like '%"
					+ scource.getCourse_name() + "%') ");
		}
		if (scource.getUser_id() != 0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(scource.getUser_name())) {
			sBuilder.append(" and p.user_id in (select user_id from User where user_name like '%"
					+ scource.getUser_name() + "%') ");
		}
		if (!StringUtil.isEmptyString(scource.getReal_name())) {
			sBuilder.append(" and p.user_id in (select user_id from User where real_name like '%"
					+ scource.getReal_name() + "%') ");
		}
		if (scource.getPiid() != null) {
			sBuilder.append(" and p.piid = " + scource.getPiid() + " ");
		}
		if (scource.getBf1() != null) {
			sBuilder.append(" and p.bf1 = " + scource.getBf1() + " ");
		}
		if (scource.getBf2() != null) {
			sBuilder.append(" and p.bf2 = " + scource.getBf2() + " ");
		}

		Object[] params = null;
		if (paramsList.size() > 0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by scource_id asc ");

		List list = null;
		if (scource.getStart() != -1) {
			list = super.findByPageHql(sBuilder.toString(), params, scource.getStart(), scource.getLimit());
		} else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			scources = new ArrayList<Scource>();
			for (Object object : list) {
				Scource _scource = (Scource) object;
				Course course = (Course) super.get(Course.class, _scource.getCourse_id());
				if (course != null) {
					_scource.setCourse_name(course.getCourse_name());
					User user = (User) super.get(User.class, course.getUser_id());
					if (user != null) {
						_scource.setTeacher_name(user.getReal_name());
					}
				}
				User user = (User) super.get(User.class, _scource.getUser_id());
				if (user != null) {
					_scource.setUser_name(user.getUser_name());
					_scource.setReal_name(user.getReal_name());

					Clazz clazz = (Clazz) super.get(Clazz.class, user.getClazz_id());
					if (clazz != null) {
						_scource.setClazz_name(clazz.getClazz_name());
					}
				}
				scources.add(_scource);
			}
		}

		return scources;
	}

	public int listScourcesCount(Scource scource) {
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Scource p WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (scource.getScource_id() != 0) {
			sBuilder.append(" and scource_id = ? ");
			paramsList.add(scource.getScource_id());
		}
		if (scource.getScource_year() != 0) {
			sBuilder.append(" and scource_year = " + scource.getScource_year() + " ");
		}
		if (scource.getScource_year_half() != 0) {
			sBuilder.append(" and scource_year_half = " + scource.getScource_year_half() + " ");
		}
		if (scource.getCourse_id() != 0) {
			sBuilder.append(" and p.course_id = " + scource.getCourse_id() + " ");
		}
		if (!StringUtil.isEmptyString(scource.getCourse_name())) {
			sBuilder.append(" and p.course_id in (select course_id from Course where course_name like '%"
					+ scource.getCourse_name() + "%') ");
		}
		if (scource.getUser_id() != 0) {
			sBuilder.append(" and p.user_id = " + scource.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(scource.getUser_name())) {
			sBuilder.append(" and p.user_id in (select user_id from User where user_name like '%"
					+ scource.getUser_name() + "%') ");
		}
		if (!StringUtil.isEmptyString(scource.getReal_name())) {
			sBuilder.append(" and p.user_id in (select user_id from User where real_name like '%"
					+ scource.getReal_name() + "%') ");
		}
		Object[] params = null;
		if (paramsList.size() > 0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long) super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int) count;
		return sum;
	}

}
