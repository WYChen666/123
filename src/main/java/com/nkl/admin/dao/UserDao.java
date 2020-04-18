package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class UserDao extends BaseDao {

	public void addUser(User user){
		super.add(user);
	}

	public void delUser(Integer user_id){
		super.del(User.class, user_id);
	}

	public void delUsers(String[] user_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <user_ids.length; i++) {
			sBuilder.append(user_ids[i]);
			if (i !=user_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM User WHERE user_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateUser(User user){
		User _user = (User)super.get(User.class, user.getUser_id());
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			_user.setUser_pass(user.getUser_pass());
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			_user.setReal_name(user.getReal_name());
		}
		if (user.getUser_sex()!=0) {
			_user.setUser_sex(user.getUser_sex());
		}
		if (user.getUser_age()!=0) {
			_user.setUser_age(user.getUser_age());
		}
		if (user.getClazz_id()!=0) {
			_user.setClazz_id(user.getClazz_id());
		}
		if (!StringUtil.isEmptyString(user.getUser_question())) {
			_user.setUser_question(user.getUser_question());
		}
		if (!StringUtil.isEmptyString(user.getUser_answer())) {
			_user.setUser_answer(user.getUser_answer());
		}
		super.update(_user);
	}

	@SuppressWarnings("rawtypes")
	public User getUser(User user){
		User _user=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id = ? ");
			paramsList.add(user.getUser_id());
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and user_name ='" + user.getUser_name() +"' ");
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
			_user = (User)list.get(0);
			Clazz clazz = (Clazz)super.get(Clazz.class, _user.getClazz_id());
			if (clazz!=null) {
				_user.setClazz_name(clazz.getClazz_name());
			}
		}

		return _user;
	}

	@SuppressWarnings("rawtypes")
	public List<User>  listUsers(User user){
		List<User> users = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM User u WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id = ? ");
			paramsList.add(user.getUser_id());
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and u.user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + user.getReal_name() +"%' ");
		}
		if (user.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id =" + user.getClazz_id() +" ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and u.user_type =" + user.getUser_type() +" ");
		}
		if (user.getCourse_id()!=0) {
			sBuilder.append(" and u.clazz_id in (select p.clazz_id from Plan p where p.course_id= " + user.getCourse_id());
			sBuilder.append(" and p.plan_year= " + user.getScore_year() +" and p.plan_year_half= " + user.getScore_year_half() +") ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" and u.user_type!=3 order by u.user_id asc ");

		List list = null;
		if (user.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, user.getStart(), user.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			users = new ArrayList<User>();
			for (Object object : list) {
				User _user = (User)object;
				Clazz clazz = (Clazz)super.get(Clazz.class, _user.getClazz_id());
				if (clazz!=null) {
					_user.setClazz_name(clazz.getClazz_name());
				}
				users.add(_user);
			}
		}

		return users;
	}

	public int  listUsersCount(User user){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM User u WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (user.getUser_id()!=0) {
			sBuilder.append(" and user_id = ? ");
			paramsList.add(user.getUser_id());
		}
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			sBuilder.append(" and u.user_pass ='" + user.getUser_pass() +"' ");
		}
		if (!StringUtil.isEmptyString(user.getUser_name())) {
			sBuilder.append(" and u.user_name like '%" + user.getUser_name() +"%' ");
		}
		if (!StringUtil.isEmptyString(user.getReal_name())) {
			sBuilder.append(" and u.real_name like '%" + user.getReal_name() +"%' ");
		}
		if (user.getClazz_id()!=0) {
			sBuilder.append(" and u.clazz_id =" + user.getClazz_id() +" ");
		}
		if (user.getUser_type()!=0) {
			sBuilder.append(" and u.user_type =" + user.getUser_type() +" ");
		}
		if (user.getCourse_id()!=0) {
			sBuilder.append(" and u.clazz_id in (select p.clazz_id from Plan p where p.course_id= " + user.getCourse_id());
			sBuilder.append(" and p.plan_year= " + user.getScore_year() +" and p.plan_year_half= " + user.getScore_year_half() +") ");
		}
		sBuilder.append(" and u.user_type!=3 ");

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
