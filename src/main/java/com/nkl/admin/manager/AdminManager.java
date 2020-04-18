package com.nkl.admin.manager;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nkl.admin.dao.ClazzDao;
import com.nkl.admin.dao.CourseDao;
import com.nkl.admin.dao.CplanDao;
import com.nkl.admin.dao.EvaluateDao;
import com.nkl.admin.dao.PlanDao;
import com.nkl.admin.dao.SScoreDao;
import com.nkl.admin.dao.ScoreDao;
import com.nkl.admin.dao.ScourceDao;
import com.nkl.admin.dao.UserDao;
import com.nkl.admin.domain.Canshu;
import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Course;
import com.nkl.admin.domain.Cplan;
import com.nkl.admin.domain.Evaluate;
import com.nkl.admin.domain.Plan;
import com.nkl.admin.domain.SScore;
import com.nkl.admin.domain.Score;
import com.nkl.admin.domain.Scource;
import com.nkl.admin.domain.User;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;

@Service
public class AdminManager {

	@Resource
	ClazzDao clazzDao;
	@Resource
	CourseDao courseDao;
	@Resource
	CplanDao cplanDao;
	@Resource
	EvaluateDao evaluateDao;
	@Resource
	PlanDao planDao;
	@Resource
	ScoreDao scoreDao;
	@Resource
	ScourceDao scourceDao;
	@Resource
	UserDao userDao;
	@Resource
	SScoreDao sscoreDao;

	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User> listUsers(User user, int[] sum) {

		if (sum != null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);

		return users;
	}

	/**
	 * @Title: queryUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User queryUser(User user) {

		User _user = userDao.getUser(user);

		return _user;
	}

	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void addUser(User user) {

		user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		userDao.addUser(user);

	}

	/**
	 * @Title: addUserBatch
	 * @Description: 添加用户
	 * @param List<Score>
	 * @return void
	 * @throws SQLException
	 */
	public void addUserBatch(List<User> users) throws SQLException {
		for (int i = 0; i < users.size(); i++) {
			userDao.addUser(users.get(i));
		}
	}

	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void updateUser(User user) {
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);

	}

	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void delUsers(User user) {

		userDao.delUsers(user.getIds().split(","));

	}

	/**
	 * @Title: listClazzs
	 * @Description: 班级查询
	 * @param clazz
	 * @return List<Clazz>
	 */
	public List<Clazz> listClazzs(Clazz clazz, int[] sum) {

		if (sum != null) {
			sum[0] = clazzDao.listClazzsCount(clazz);
		}
		List<Clazz> clazzs = clazzDao.listClazzs(clazz);

		return clazzs;
	}

	/**
	 * @Title: queryClazz
	 * @Description: 班级查询
	 * @param clazz
	 * @return Clazz
	 */
	public Clazz queryClazz(Clazz clazz) {

		Clazz _clazz = clazzDao.getClazz(clazz);

		return _clazz;
	}

	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @param clazz
	 * @return void
	 */
	public void addClazz(Clazz clazz) {

		clazzDao.addClazz(clazz);

	}

	/**
	 * @Title: updateClazz
	 * @Description: 更新班级信息
	 * @param clazz
	 * @return void
	 */
	public void updateClazz(Clazz clazz) {

		clazzDao.updateClazz(clazz);

	}

	/**
	 * @Title: delClazzs
	 * @Description: 删除班级信息
	 * @param clazz
	 * @return void
	 */
	public void delClazzs(Clazz clazz) {

		clazzDao.delClazzs(clazz.getIds().split(","));

	}

	/**
	 * @Title: listCourses
	 * @Description: 课程查询
	 * @param course
	 * @return List<Course>
	 */
	public List<Course> listCourses(Course course, int[] sum) {

		if (sum != null) {
			sum[0] = courseDao.listCoursesCount(course);
		}
		List<Course> courses = courseDao.listCourses(course);

		return courses;
	}

	public Map<Integer, Integer> listScourcesPerson(Course course) {

		Map<Integer, Integer> sumCountMap = courseDao.listScourcesPerson(course);

		return sumCountMap;
	}

	/**
	 * @Title: queryCourse
	 * @Description: 课程查询
	 * @param course
	 * @return Course
	 */
	public Course queryCourse(Course course) {

		Course _course = courseDao.getCourse(course);

		return _course;
	}

	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @param course
	 * @return void
	 */
	public void addCourse(Course course) {

		courseDao.addCourse(course);

	}

	/**
	 * @Title: updateCourse
	 * @Description: 更新课程信息
	 * @param course
	 * @return void
	 */
	public void updateCourse(Course course) {

		courseDao.updateCourse(course);

	}

	/**
	 * @Title: delCourses
	 * @Description: 删除课程信息
	 * @param course
	 * @return void
	 */
	public void delCourses(Course course) {

		courseDao.delCourses(course.getIds().split(","));

	}

	/**
	 * @Title: listPlans
	 * @Description: 教学计划查询
	 * @param plan
	 * @return List<Plan>
	 */
	public List<Plan> listPlans(Plan plan, int[] sum) {

		if (sum != null) {
			sum[0] = planDao.listPlansCount(plan);
		}
		List<Plan> plans = planDao.listPlans(plan);

		return plans;
	}

	/**
	 * @Title: queryPlan
	 * @Description: 教学计划查询
	 * @param plan
	 * @return Plan
	 */
	public Plan queryPlan(Plan plan) {

		Plan _plan = planDao.getPlan(plan);

		return _plan;
	}

	/**
	 * @Title: addPlan
	 * @Description: 添加教学计划
	 * @param plan
	 * @return void
	 */
	public void addPlan(Plan plan) {

		planDao.addPlan(plan);

	}

	/**
	 * @Title: updatePlan
	 * @Description: 更新教学计划信息
	 * @param plan
	 * @return void
	 */
	public void updatePlan(Plan plan) {

		planDao.updatePlan(plan);

	}

	/**
	 * @Title: delPlans
	 * @Description: 删除教学计划信息
	 * @param plan
	 * @return void
	 */
	public void delPlans(Plan plan) {

		planDao.delPlans(plan.getIds().split(","));

	}

	/**
	 * @Title: listCplans
	 * @Description: 班级课表查询
	 * @param cplan
	 * @return List<Cplan>
	 */
	public List<Cplan> listCplans(Cplan cplan, int[] sum) {

		if (sum != null) {
			sum[0] = cplanDao.listCplansCount(cplan);
		}
		List<Cplan> cplans = cplanDao.listCplans(cplan);

		return cplans;
	}

	/**
	 * @Title: listCplansByClazz
	 * @Description: 班级课表一周视图查询
	 * @param cplan
	 * @return List<Cplan>
	 */
	public List<Cplan> listCplansByClazz(Cplan cplan) {

		List<Cplan> cplans = cplanDao.listCplansByClazz(cplan);

		return cplans;
	}

	/**
	 * @Title: queryCplan
	 * @Description: 班级课表查询
	 * @param cplan
	 * @return Cplan
	 */
	public Cplan queryCplan(Cplan cplan) {

		Cplan _cplan = cplanDao.getCplan(cplan);

		return _cplan;
	}

	/**
	 * @Title: addCplan
	 * @Description: 添加班级课表
	 * @param cplan
	 * @return void
	 */
	public void addCplan(Cplan cplan) {

		cplanDao.addCplan(cplan);

	}

	/**
	 * @Title: updateCplan
	 * @Description: 更新班级课表信息
	 * @param cplan
	 * @return void
	 */
	public void updateCplan(Cplan cplan) {

		cplanDao.updateCplan(cplan);

	}

	/**
	 * @Title: delCplans
	 * @Description: 删除班级课表信息
	 * @param cplan
	 * @return void
	 */
	public void delCplans(Cplan cplan) {

		cplanDao.delCplans(cplan.getIds().split(","));

	}

	/**
	 * @Title: listScources
	 * @Description: 学生选课查询
	 * @param scource
	 * @return List<Scource>
	 */
	public List<Scource> listScources(Scource scource, int[] sum) {

		if (sum != null) {
			sum[0] = scourceDao.listScourcesCount(scource);
		}
		List<Scource> scources = scourceDao.listScources(scource);

		return scources;
	}

	/**
	 * @Title: queryScource
	 * @Description: 学生选课查询
	 * @param scource
	 * @return Scource
	 */
	public Scource queryScource(Scource scource) {

		Scource _scource = scourceDao.getScource(scource);

		return _scource;
	}

	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @param scource
	 * @return void
	 */
	public void addScource(Scource scource) {

		scourceDao.addScource(scource);

	}

	/**
	 * @Title: delScources
	 * @Description: 删除学生选课信息
	 * @param scource
	 * @return void
	 */
	public void delScources(Scource scource) {

		scourceDao.delScources(scource.getIds().split(","));

	}

	/**
	 * @Title: listScores
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listScores(Score score, int[] sum) {

		if (sum != null) {
			sum[0] = scoreDao.listScoresCount(score);
		}
		List<Score> scores = scoreDao.listScores(score);

		return scores;
	}

	/**
	 * @Title: listScoresSum
	 * @Description: 成绩查询
	 * @param score
	 * @return List<Score>
	 */
	public List<Score> listScoresSum(Score score, int[] sum) {

		if (sum != null) {
			sum[0] = scoreDao.listScoresSumCount(score);
		}
		List<Score> scores = scoreDao.listScoresSum(score);

		return scores;
	}

	/**
	 * @Title: queryScore
	 * @Description: 成绩查询
	 * @param score
	 * @return Score
	 */
	public Score queryScore(Score score) {

		Score _score = scoreDao.getScore(score);

		return _score;
	}

	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @param score
	 * @return void
	 */
	public void addScore(Score score) {

		scoreDao.addScore(score);

	}

	/**
	 * @Title: addScoreBatch
	 * @Description: 添加成绩
	 * @param List<Score>
	 * @return void
	 * @throws SQLException
	 */
	public void addScoreBatch(List<Score> scores) throws SQLException {
		for (int i = 0; i < scores.size(); i++) {
			scoreDao.importScore(scores.get(i));
		}
	}

	/**
	 * @Title: updateScore
	 * @Description: 更新成绩信息
	 * @param score
	 * @return void
	 */
	public void updateScore(Score score) {

		scoreDao.updateScore(score);

	}

	/**
	 * @Title: delScores
	 * @Description: 删除成绩信息
	 * @param score
	 * @return void
	 */
	public void delScores(Score score) {

		scoreDao.delScores(score.getIds().split(","));

	}

	/**
	 * @Title: listEvaluates
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate> listEvaluates(Evaluate evaluate, int[] sum) {

		if (sum != null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluates(evaluate);

		return evaluates;
	}

	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教务评定平均成绩查询
	 * @param evaluate
	 * @return List<Evaluate>
	 */
	public List<Evaluate> listEvaluatesSum(Evaluate evaluate, int[] sum) {

		if (sum != null) {
			sum[0] = evaluateDao.listEvaluatesCount(evaluate);
		}
		List<Evaluate> evaluates = evaluateDao.listEvaluatesSum(evaluate);

		return evaluates;
	}

	/**
	 * @Title: queryEvaluate
	 * @Description: 教务评定查询
	 * @param evaluate
	 * @return Evaluate
	 */
	public Evaluate queryEvaluate(Evaluate evaluate) {

		Evaluate _evaluate = evaluateDao.getEvaluate(evaluate);

		return _evaluate;
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 添加教务评定
	 * @param evaluate
	 * @return void
	 */
	public void addEvaluate(Evaluate evaluate) {

		evaluateDao.addEvaluate(evaluate);

	}

	/**
	 * @Title: updateEvaluate
	 * @Description: 更新教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public void updateEvaluate(Evaluate evaluate) {

		evaluateDao.updateEvaluate(evaluate);

	}

	/**
	 * @Title: delEvaluates
	 * @Description: 删除教务评定信息
	 * @param evaluate
	 * @return void
	 */
	public void delEvaluates(Evaluate evaluate) {

		evaluateDao.delEvaluates(evaluate.getIds().split(","));

	}

	/**
	 * @Title: listSScores
	 * @Description: 评测成绩查询
	 * @param sscore
	 * @return List<SScore>
	 */
	public List<SScore> listSScores(SScore sscore, int[] sum) {

		if (sum != null) {
			sum[0] = sscoreDao.listSScoresCount(sscore);
		}
		List<SScore> sscores = sscoreDao.listSScores(sscore);

		return sscores;
	}

	public List<SScore> listSScoresData1(SScore sscore) {

		List<SScore> sscores = sscoreDao.listSScoresData1(sscore);

		return sscores;
	}

	/**
	 * @Title: querySScore
	 * @Description: 评测成绩查询
	 * @param sscore
	 * @return SScore
	 */
	public SScore querySScore(SScore sscore) {

		SScore _sscore = sscoreDao.getSScore(sscore);

		return _sscore;
	}

	/**
	 * @Title: addSScore
	 * @Description: 添加评测成绩
	 * @param sscore
	 * @return void
	 */
	public void addSScore(SScore sscore) {

		sscoreDao.addSScore(sscore);

	}

	/**
	 * @Title: updateSScore
	 * @Description: 更新评测成绩信息
	 * @param sscore
	 * @return void
	 */
	public void updateSScore(SScore sscore) {

		sscoreDao.updateSScore(sscore);

	}

	/**
	 * @Title: updateSScore
	 * @Description: 更新flag -xf
	 * @param sscore
	 * @return void
	 */
	public void updateFlag(SScore sscore) {

		sscoreDao.updateFlag(sscore);

	}

	/**
	 * @Title: delSScores
	 * @Description: 删除评测成绩信息
	 * @param sscore
	 * @return void
	 */
	public void delSScores(SScore sscore) {

		sscoreDao.delSScores(sscore.getIds().split(","));

	}

	/**
	 * @Title: daoruchengji
	 * @Description: 导入成绩
	 * @param data1
	 * @return boolean
	 */
	public boolean daoruchengji(String data1) {
		User admin = (User) Param.getSession("admin");
		SScore sscore = new SScore();
		sscore.setTeacher_id(admin.getUser_id());
		try {
			File file = new File(data1);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			List<SScore> sscores = listSScoresData1(sscore);
			if (sscores != null && sscores.size() > 0) {
				for (int i = 0; i < sscores.size(); i++) {
					SScore item = sscores.get(i);
					fileWriter.write(item.getScore_value1() + " " + item.getScore_value2() + " "
							+ item.getScore_value3() + " " + item.getScore_value4() + " " + item.getScore_value5());
					if (i != sscores.size() - 1) {
						fileWriter.write("\r\n");
					}
				}
				fileWriter.flush();
				fileWriter.close();
			} else {
				fileWriter.close();
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Title:
	 * @Description: 参数导入
	 * @param data1
	 * @return boolean
	 */
	public boolean canshudaoru(Canshu canshu, String parameters) {
		try {
			File file = new File(parameters);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(canshu.getParam1());
			fileWriter.write("\r\n");
			fileWriter.write(canshu.getParam2());
			fileWriter.write("\r\n");
			fileWriter.write(canshu.getParam3());
			fileWriter.write("\r\n");
			fileWriter.write(canshu.getParam4());
			fileWriter.write("\r\n");
			fileWriter.write(canshu.getParam5());
			fileWriter.write("\r\n");
			fileWriter.write(canshu.getParam6());
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Title:
	 * @Description: 目标导入
	 * @param data1
	 * @return boolean
	 */
	public boolean mubiaodaoru(Canshu canshu, String parameters) {
		try {
			File file = new File(parameters);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(canshu.getParam1());
			fileWriter.write(" ");
			fileWriter.write(canshu.getParam2());
			fileWriter.write(" ");
			fileWriter.write(canshu.getParam3());
			fileWriter.write(" ");
			fileWriter.write(canshu.getParam4());
			fileWriter.write(" ");
			fileWriter.write(canshu.getParam5());
			for (int i = 6; i < 17; i++) {
				Field field = Canshu.class.getDeclaredField("param" + i);
				field.setAccessible(true);
				Object object = field.get(canshu);
				if (object == null) {
					break;
				} else {
					fileWriter.write(" ");
					fileWriter.write(object.toString());
				}
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
