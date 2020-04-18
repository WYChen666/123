package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.SScore;
import com.nkl.admin.domain.User;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class SScoreDao extends BaseDao {

	public void addSScore(SScore sscore) {
		super.add(sscore);
	}

	public void addSScoreBatch() {
		String sql = "insert into sscore(user_id,course_id,score_year,score_year_half,flag,piid,bf1,bf2) "
				+ " SELECT user_id,course_id,scource_year,scource_year_half,flag,piid,bf1,bf2 from scource "
				+ "  where (user_id,course_id,scource_year,scource_year_half,flag,piid,bf1,bf2) not in (SELECT user_id,course_id,score_year,score_year_half,flag,piid,bf1,bf2 from sscore)";
		Object[] params = null;
		super.executeUpdateSql(sql, params);
	}

	public void delSScore(Integer sscore_id) {
		super.del(SScore.class, sscore_id);
	}

	public void delSScores(String[] sscore_ids) {
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i < sscore_ids.length; i++) {
			sBuilder.append(sscore_ids[i]);
			if (i != sscore_ids.length - 1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM SScore WHERE sscore_id IN(" + sBuilder.toString() + ")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateSScore(SScore score) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sscore SET sscore_id = " + score.getSscore_id() + " ");
		sBuilder.append(" ,score_value1 = '" + score.getScore_value1() + "' ");
		sBuilder.append(" ,score_value2 = '" + score.getScore_value2() + "' ");
		sBuilder.append(" ,score_value3 = '" + score.getScore_value3() + "' ");
		sBuilder.append(" ,score_value4 = '" + score.getScore_value4() + "' ");
		sBuilder.append(" ,score_value5 = '" + score.getScore_value5() + "' ");
		sBuilder.append(" where sscore_id = " + score.getSscore_id() + " ");

		Object[] params = null;
		super.executeUpdateSql(sBuilder.toString(), params);
	}

	// -xf
	public void updateFlag(SScore score) {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("UPDATE sscore SET flag = " + score.getFlag() + " ");
		sBuilder.append(" where sscore_id = " + score.getSscore_id() + " ");

		Object[] params = null;
		super.executeUpdateSql(sBuilder.toString(), params);
	}

	@SuppressWarnings("rawtypes")
	public SScore getSScore(SScore score) {
		SScore _sscore = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM SScore s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getSscore_id() != 0) {
			sBuilder.append(" and sscore_id = ? ");
			paramsList.add(score.getSscore_id());
		}
		if (score.getUser_id() != 0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() + " ");
		}
		if (score.getCourse_id() != 0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() + " ");
		}
		if (score.getClazz_id() != 0) {
			sBuilder.append(" and u.clazz_id = " + score.getClazz_id() + " ");
		}
		if (score.getScore_year() != 0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() + " ");
		}
		if (score.getScore_year_half() != 0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() + " ");
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
			_sscore = (SScore) list.get(0);
			User user = (User) super.get(User.class, _sscore.getUser_id());
			if (user != null) {
				_sscore.setUser_name(user.getUser_name());
				_sscore.setUser_sex(user.getUser_sex());
				_sscore.setReal_name(user.getReal_name());

				Clazz clazz = (Clazz) super.get(Clazz.class, user.getClazz_id());
				if (clazz != null) {
					_sscore.setClazz_id(clazz.getClazz_id());
					_sscore.setClazz_name(clazz.getClazz_name());
				}
			}
			Course course = (Course) super.get(Course.class, _sscore.getCourse_id());
			if (course != null) {
				_sscore.setCourse_type(course.getCourse_type());
				_sscore.setCourse_name(course.getCourse_name());
			}
		}

		return _sscore;
	}

	@SuppressWarnings("rawtypes")
	public List<SScore> listSScores(SScore score) {
		List<SScore> sscores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM SScore s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getSscore_id() != 0) {
			sBuilder.append(" and sscore_id = ? ");
			paramsList.add(score.getSscore_id());
		}
		if (score.getUser_id() != 0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where user_name like '%" + score.getUser_name()
					+ "%') ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where real_name like '%" + score.getReal_name()
					+ "%') ");
		}
		if (score.getCourse_id() != 0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() + " ");
		}
		if (score.getCourse_type() != 0) {
			sBuilder.append(" and s.course_id in (select course_id from Course where course_type="
					+ score.getCourse_type() + ") ");
		}
		if (score.getClazz_id() != 0) {
			sBuilder.append(" and s.user_id in (select user_id from User where clazz_id=" + score.getClazz_id() + ") ");
		}
		if (score.getScore_year() != 0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() + " ");
		}
		if (score.getScore_year_half() != 0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() + " ");
		}
		if (score.getScore_valueMin() != 0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() + " ");
		}
		if (score.getScore_valueMax() != 0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() + " ");
		}
		/*
		 * if (score.getTeacher_id() != 0) { sBuilder.
		 * append(" and s.course_id in (select cc.course_id from Course cc where cc.teacher_id="
		 * + score.getTeacher_id() + ") "); }
		 */

		Object[] params = null;
		if (paramsList.size() > 0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by s.score_year desc,s.score_year_half desc,sscore_id asc ");

		List list = null;
		if (score.getStart() != -1) {
			list = super.findByPageHql(sBuilder.toString(), params, score.getStart(), score.getLimit());
		} else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			sscores = new ArrayList<SScore>();
			for (Object object : list) {
				SScore _sscore = (SScore) object;
				User user = (User) super.get(User.class, _sscore.getUser_id());
				if (user != null) {
					_sscore.setUser_name(user.getUser_name());
					_sscore.setUser_sex(user.getUser_sex());
					_sscore.setReal_name(user.getReal_name());

					Clazz clazz = (Clazz) super.get(Clazz.class, user.getClazz_id());
					if (clazz != null) {
						_sscore.setClazz_id(clazz.getClazz_id());
						_sscore.setClazz_name(clazz.getClazz_name());
					}
				}
				Course course = (Course) super.get(Course.class, _sscore.getCourse_id());
				if (course != null) {
					_sscore.setCourse_type(course.getCourse_type());
					_sscore.setCourse_name(course.getCourse_name());
				}
				sscores.add(_sscore);
			}
		}

		return sscores;
	}

	public int listSScoresCount(SScore score) {
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM SScore s WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (score.getUser_id() != 0) {
			sBuilder.append(" and s.user_id = " + score.getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(score.getUser_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where user_name like '%" + score.getUser_name()
					+ "%') ");
		}
		if (!StringUtil.isEmptyString(score.getReal_name())) {
			sBuilder.append(" and s.user_id in (select user_id from User where real_name like '%" + score.getReal_name()
					+ "%') ");
		}
		if (score.getCourse_id() != 0) {
			sBuilder.append(" and s.course_id = " + score.getCourse_id() + " ");
		}
		if (score.getCourse_type() != 0) {
			sBuilder.append(" and s.course_id in (select course_id from Course where course_type="
					+ score.getCourse_type() + ") ");
		}
		if (score.getClazz_id() != 0) {
			sBuilder.append(" and s.user_id in (select user_id from User where clazz_id=" + score.getClazz_id() + ") ");
		}
		if (score.getScore_year() != 0) {
			sBuilder.append(" and s.score_year = " + score.getScore_year() + " ");
		}
		if (score.getScore_year_half() != 0) {
			sBuilder.append(" and s.score_year_half = " + score.getScore_year_half() + " ");
		}
		if (score.getScore_valueMin() != 0) {
			sBuilder.append(" and s.score_value >= " + score.getScore_valueMin() + " ");
		}
		if (score.getScore_valueMax() != 0) {
			sBuilder.append(" and s.score_value <= " + score.getScore_valueMax() + " ");
		}
		if (score.getTeacher_id() != 0) {
			sBuilder.append(" and s.course_id in (select cc.course_id from Course cc where cc.teacher_id="
					+ score.getTeacher_id() + ") ");
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

	@SuppressWarnings("unchecked")
	public List<SScore> listSScoresData1(SScore score) {
		addSScoreBatch();
		List<SScore> scores = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(
				"SELECT IFNULL(score_value1,0) score_value1,IFNULL(score_value2,0) score_value2,IFNULL(score_value3,0) score_value3,IFNULL(score_value4,0) score_value4,IFNULL(score_value5,0) score_value5 FROM sscore s ");
		sBuilder.append("  join user u on s.user_id=u.user_id ");
		sBuilder.append("  join course co on co.course_id = s.course_id  ");
		sBuilder.append(" where 1=1 ");

		if (score.getTeacher_id() != 0) {
			sBuilder.append(" and co.teacher_id = " + score.getTeacher_id() + " ");
		}

		sBuilder.append(" and score_year=(SELECT max(score_year) from sscore) ");

		String[] scalars = { "score_value1", "score_value2", "score_value3", "score_value4", "score_value5" };
		Type[] types = { Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING, Hibernate.STRING };

		List<Object> list = super.executeQueryJavaBeanSql(sBuilder.toString(), score.getClass(), null, scalars, types);
		if (list != null && list.size() > 0) {
			scores = new ArrayList<SScore>();
			for (Object object : list) {
				scores.add((SScore) object);
			}
		}
		return scores;
	}

}
