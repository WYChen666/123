package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class CourseDao extends BaseDao {

	public void addCourse(Course course){
		super.add(course);
	}

	public void delCourse(Integer course_id){
		super.del(Course.class, course_id);
	}

	public void delCourses(String[] course_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <course_ids.length; i++) {
			sBuilder.append(course_ids[i]);
			if (i !=course_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Course WHERE course_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateCourse(Course course){
		Course _course = (Course)super.get(Course.class, course.getCourse_id());
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			_course.setCourse_name(course.getCourse_name());
		}
		if (course.getCourse_type()!=0) {
			_course.setCourse_type(course.getCourse_type());
		}
		if (course.getTeacher_id()!=0) {
			_course.setTeacher_id(course.getTeacher_id());
		}
		if (!StringUtil.isEmptyString(course.getNote())) {
			_course.setNote(course.getNote());
		}
		super.update(_course);
	}

	@SuppressWarnings("rawtypes")
	public Course getCourse(Course course){
		Course _course=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Course WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = ? ");
			paramsList.add(course.getCourse_id());
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" and course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + course.getTeacher_id() +" ");
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
			_course = (Course)list.get(0);
			User user = (User)super.get(User.class, course.getTeacher_id());
			if (user!=null) {
				_course.setTeacher_name(user.getReal_name());
			}
		}

		return _course;
	}

	@SuppressWarnings("rawtypes")
	public List<Course>  listCourses(Course course){
		List<Course> courses = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Course WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = ? ");
			paramsList.add(course.getCourse_id());
		}
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			sBuilder.append(" and course_name like '%" + course.getCourse_name() +"%' ");
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" and course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + course.getTeacher_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getTeacher_name())) {
			sBuilder.append(" and teacher_id in (select user_id from User where real_name like '%" + course.getTeacher_name() +"%') ");
		}
		if (course.getUser_id()!=0) {
			sBuilder.append(" and course_id in (select course_id from Plan where user_id = " + course.getUser_id() 
					+" and plan_year=" + course.getScore_year()+" and plan_year_half=" + course.getScore_year_half() +") ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by course_id asc ");

		List list = null;
		if (course.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, course.getStart(), course.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			Map<Integer,Integer> sumCountMap = listScourcesPerson(course);
			courses = new ArrayList<Course>();
			for (Object object : list) {
				Course _course = (Course)object;
				User user = (User)super.get(User.class, course.getTeacher_id());
				if (user!=null) {
					_course.setTeacher_name(user.getReal_name());
				}
				if (sumCountMap.containsKey(_course.getCourse_id())) {
					_course.setUser_count(sumCountMap.get(_course.getCourse_id()));
				}
				courses.add(_course);
			}
		}

		return courses;
	}

	public int  listCoursesCount(Course course){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Course WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (course.getCourse_id()!=0) {
			sBuilder.append(" and course_id = ? ");
			paramsList.add(course.getCourse_id());
		}
		if (!StringUtil.isEmptyString(course.getCourse_name())) {
			sBuilder.append(" and course_name like '%" + course.getCourse_name() +"%' ");
		}
		if (course.getCourse_type()!=0) {
			sBuilder.append(" and course_type = " + course.getCourse_type() +" ");
		}
		if (course.getTeacher_id()!=0) {
			sBuilder.append(" and teacher_id = " + course.getTeacher_id() +" ");
		}
		if (!StringUtil.isEmptyString(course.getTeacher_name())) {
			sBuilder.append(" and teacher_id in (select user_id from User where real_name like '%" + course.getTeacher_name() +"%') ");
		}
		if (course.getUser_id()!=0) {
			sBuilder.append(" and course_id in (select course_id from Plan where user_id = " + course.getUser_id() 
					+" and plan_year=" + course.getScore_year()+" and plan_year_half=" + course.getScore_year_half() +") ");
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
	public Map<Integer,Integer>  listScourcesPerson(Course scource){
		Map<Integer,Integer> scources = new HashMap<Integer, Integer>();
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT p.course_id,count(0) user_count FROM scource p ");
		sBuilder.append("  join user u on p.user_id=u.user_id ");
		sBuilder.append("  WHERE 1=1 ");

		if (scource.getScore_year()!=0) {
			sBuilder.append(" and scource_year = " + scource.getScore_year() +" ");
		}
		if (scource.getScore_year_half()!=0) {
			sBuilder.append(" and scource_year_half = " + scource.getScore_year_half() +" ");
		}
		sBuilder.append(" group by p.course_id ");

		List<Map<String, Object>> list = super.executeQueryMapSql(sBuilder.toString(), null);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> object : list) {
				scources.put(Integer.parseInt(object.get("course_id").toString()), Integer.parseInt(object.get("user_count").toString()));
			}
		}
		return scources;
	}

}
