package com.nkl.admin.action;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.model.ActivityCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

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
import com.nkl.admin.manager.AdminManager;
import com.nkl.common.action.BaseAction;
import com.nkl.common.util.DateUtil;
import com.nkl.common.util.FindProjectPath;
import com.nkl.common.util.Param;

@Controller(value = "adminAction")
@Scope("prototype")
public class AdminAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	AdminManager adminManager;

	@Autowired
	ProcessEngine processEngine;

	// 抓取页面参数liuhaide
	String savePath;
	User paramsUser;
	Clazz paramsClazz;
	Course paramsCourse;
	Plan paramsPlan;
	Cplan paramsCplan;
	Scource paramsScource;
	Score paramsScore;
	Evaluate paramsEvaluate;
	SScore paramsSScore;
	Canshu paramsCanshu;
	String tip;
	String piid;

	/**
	 * @Title: daoruchengji
	 * @Description: 导入成绩
	 * @return String
	 */
	public String daoruchengji() {
		try {
			String savePath = getSavePath();
			String data1 = FindProjectPath.getRootPath(savePath + "\\data1.txt").replaceAll("%20", " ");
			boolean flag = adminManager.daoruchengji(data1);
			if (!flag) {
				setErrorReason("导入异常，请稍后重试！");
				return "error2";
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("导入异常，请稍后重试！");
			return "error2";
		}

		return "success";
	}

	/**
	 * @Title: canshudaoru
	 * @Description: 参数导入
	 * @return String
	 */
	public String canshudaoru() {
		try {
			String savePath = getSavePath();
			String parameters = FindProjectPath.getRootPath(savePath + "\\parameters.txt").replaceAll("%20", " ");
			boolean flag = adminManager.canshudaoru(paramsCanshu, parameters);
			if (!flag) {
				setErrorReason("导入异常，请稍后重试！");
				return "error2";
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("导入异常，请稍后重试！");
			return "error2";
		}

		return "success";
	}

	/**
	 * @Title: calc1
	 * @Description: 计算输出1
	 * @return String
	 */
//	public String calc1() {
//		try {
//			String savePath = getSavePath();
//			String data1 = FindProjectPath.getRootPath(savePath + "\\data1.txt").replaceAll("%20", " ");
//			;
//			String parameters = FindProjectPath.getRootPath(savePath + "\\parameters.txt").replaceAll("%20", " ");
//			;
//			String center1 = FindProjectPath.getRootPath(savePath + "\\center1.txt").replaceAll("%20", " ");
//			;
//			String matrix1 = FindProjectPath.getRootPath(savePath + "\\matrix1.txt").replaceAll("%20", " ");
//			;
//			CalcResult1.FILE_DATA_IN = data1;
//			CalcResult1.FILE_PAR = parameters;
//			CalcResult1.FILE_CENTER = center1;
//			CalcResult1.FILE_MATRIX = matrix1;
//			CalcResult1 Fcm_myself = new CalcResult1();
//			Fcm_myself.getPar();
//			Fcm_myself.runFcm_myself();
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorReason("计算异常，请稍后重试！");
//			return "error2";
//		}
//
//		return "success";
//	}

	/**
	 * @Title: mubiaodaoru
	 * @Description: 目标数据导入
	 * @return String
	 */
	public String mubiaodaoru() {
		try {
			String savePath = getSavePath();
			String parameters = FindProjectPath.getRootPath(savePath + "\\one-data.txt").replaceAll("%20", " ");
			boolean flag = adminManager.mubiaodaoru(paramsCanshu, parameters);
			if (!flag) {
				setErrorReason("导入异常，请稍后重试！");
				return "error2";
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("导入异常，请稍后重试！");
			return "error2";
		}

		return "success";
	}

	/**
	 * @Title: calc2
	 * @Description: 计算输出2
	 * @return String
	 */
//	public String calc2() {
//		try {
//			String savePath = getSavePath();
//			String one_data = FindProjectPath.getRootPath(savePath + "\\one-data.txt").replaceAll("%20", " ");
//			String center1 = FindProjectPath.getRootPath(savePath + "\\center1.txt").replaceAll("%20", " ");
//			String data_in = FindProjectPath.getRootPath(savePath + "\\data1.txt").replaceAll("%20", " ");
//			String matrix1 = FindProjectPath.getRootPath(savePath + "\\matrix1.txt").replaceAll("%20", " ");
//			CalcResult2 calcResult2 = new CalcResult2();
//			String result = calcResult2.getResult1(one_data, center1, data_in, matrix1);
//			setResult("result", result);
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorReason("计算异常，请稍后重试！");
//			return "error2";
//		}
//
//		return "success";
//	}

	/**
	 * @Title: listStuCourse
	 * @Description: 查询学生选课记录1
	 * @return String
	 */
	public String listStuCourse() {
		try {
			if (paramsCourse == null) {
				paramsCourse = new Course();
				paramsCourse.setScore_year(DateUtil.getYear(new Date()));
				paramsCourse.setScore_year_half((DateUtil.getMonth(new Date()) > 6 ? 2 : 1));
			}
			// 设置分页信息
			setPagination(paramsCourse);
			// 总的条数
			int[] sum = { 0 };
			// 查询课程列表
			paramsCourse.setCourse_type(2);
			List<Course> courses = adminManager.listCourses(paramsCourse, sum);

			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}

		return "stuCourseShow";
	}

	/**
	 * @Title: listStuCourse2
	 * @Description: 查询学生选课明细
	 * @return String
	 */
	public String listStuCourse2() {
		try {
			if (paramsScource == null) {
				paramsScource = new Scource();
			}
			// 设置分页信息
			setPagination(paramsScource);
			// 总的条数
			int[] sum = { 0 };
			// 查询学生选课列表
			// User admin = (User)Param.getSession("admin");
			// paramsScource.setUser_id(admin.getUser_id());
			List<Scource> scources = adminManager.listScources(paramsScource, sum);

			Param.setAttribute("scources", scources);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询学生选课异常", "main.jsp");
			return "infoTip";
		}

		return "stuCourseShow2";
	}

	/**
	 * @Title: listSScores
	 * @Description: 查询评测成绩
	 * @return String
	 */
	public String listSScores() {
		try {
			if (paramsSScore == null) {
				paramsSScore = new SScore();
				paramsSScore.setScore_year(DateUtil.getYear(new Date()));
				paramsSScore.setScore_year_half((DateUtil.getMonth(new Date()) > 6 ? 2 : 1));
			}
			// 设置分页信息
			paramsSScore.setStart(-1);
			// 身份限制
			User admin = (User) Param.getSession("admin");
			if (admin.getUser_type() == 1) {
				paramsSScore.setUser_id(admin.getUser_id());
			} else if (admin.getUser_type() == 2) {
				paramsSScore.setTeacher_id(admin.getUser_id());
			}
			// 查询评测成绩列表
			List<SScore> sscores = adminManager.listSScores(paramsSScore, null);
			Param.setAttribute("sscores", sscores);

		} catch (Exception e) {
			setErrorTip("查询评测成绩异常", "main.jsp");
			return "infoTip";
		}

		return "sscoreShow";
	}

	/**
	 * @Title: editSScore
	 * @Description: 编辑评测成绩
	 * @return String
	 */
	public String editSScore() {
		try {
			// 得到选修成绩
			SScore sscore = adminManager.querySScore(paramsSScore);
			Param.setAttribute("sscore", sscore);
		} catch (Exception e) {
			setErrorTip("查询评测成绩异常", "Admin_listSScores.action");
			return "infoTip";
		}

		return "sscoreEdit";
	}

	/**
	 * @Title: saveSScore
	 * @Description: 保存编辑评测成绩
	 * @return String
	 */
	public String saveSScore() {
		try {
			// 保存编辑评测成绩
			adminManager.updateSScore(paramsSScore);

		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("编辑失败");
			return "error2";
		}

		return "success";
	}

	/**
	 * @Title: saveSScore
	 * @Description: 更改flag -xf
	 * @return String
	 */
	public String passSScore() {
		try {
			// 保存编辑评测成绩
			adminManager.updateFlag(paramsSScore);

			String gotos = "";
			if (paramsSScore.getFlag() == 2) {
				gotos = "to 李老师审批";
			} else if (paramsSScore.getFlag() == 3) {
				gotos = "to 王老师审批";
			} else if (paramsSScore.getFlag() == 4) {
				gotos = "to end1";
			}

			processEngine.getExecutionService().signalExecutionById(paramsSScore.getPiid(), gotos);
			// jbpm end

			paramsSScore.setScore_year(DateUtil.getYear(new Date()));
			// 设置分页信息
			paramsSScore.setStart(-1);
			// 身份限制
			User admin = (User) Param.getSession("admin");
			if (admin.getUser_type() == 1) {
				paramsSScore.setUser_id(admin.getUser_id());
			} else if (admin.getUser_type() == 2) {
				paramsSScore.setTeacher_id(admin.getUser_id());
			}
			// 查询评测成绩列表
			List<SScore> sscores = adminManager.listSScores(paramsSScore, null);
			Param.setAttribute("sscores", sscores);

		} catch (Exception e) {
			e.printStackTrace();
			setErrorReason("通过失败");
			return "error2";
		}

		return "sscoreShow";
	}
public String bohui() {
	try {
		// 保存编辑评测成绩
		adminManager.updateFlag(paramsSScore);
		// jbpm begin

		String gotos = "to 驳回";
		if (paramsSScore.getFlag() == 3) {
			gotos = "to 驳回";
		}
//		} else if (paramsSScore.getFlag() == 3) {
//			gotos = "to 王老师审批";
//		} else if (paramsSScore.getFlag() == 4) {
//			gotos = "to end1";
//		}

		processEngine.getExecutionService().signalExecutionById(paramsSScore.getPiid(), gotos);
		// jbpm end

		paramsSScore.setScore_year(DateUtil.getYear(new Date()));
		// 设置分页信息
		paramsSScore.setStart(-1);
		// 身份限制
		User admin = (User) Param.getSession("admin");
		if (admin.getUser_type() == 1) {
			paramsSScore.setUser_id(admin.getUser_id());
		} else if (admin.getUser_type() == 2) {
			paramsSScore.setTeacher_id(admin.getUser_id());
		}
		// 查询评测成绩列表
		List<SScore> sscores = adminManager.listSScores(paramsSScore, null);
		Param.setAttribute("sscores", sscores);

	} catch (Exception e) {
		e.printStackTrace();
		setErrorReason("驳回失败");
		return "error2";
	}

	return "sscoreShow";
}
	/**
	 * @Title: delSScores
	 * @Description: 删除评测成绩
	 * @return String
	 */
	public String delSScores() {
		try {
			// 删除评测成绩
			adminManager.delSScores(paramsSScore);

			setSuccessTip("删除评测成绩成功", "Admin_listSScores.action");
		} catch (Exception e) {
			setErrorTip("删除评测成绩异常", "Admin_listSScores.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: saveAdmin
	 * @Description: 保存修改个人信息
	 * @return String
	 */
	public String saveAdmin() {
		try {
			// 验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			// 保存修改个人信息
			adminManager.updateUser(paramsUser);
			// 更新session
			User admin = new User();
			admin.setUser_id(paramsUser.getUser_id());
			admin = adminManager.queryUser(admin);
			Param.setSession("admin", admin);

			setSuccessTip("编辑成功", "modifyInfo.jsp");

		} catch (Exception e) {
			setErrorTip("编辑异常", "modifyInfo.jsp");
		}

		return "infoTip";
	}

	/**
	 * @Title: saveAdminPass
	 * @Description: 保存修改个人密码
	 * @return String
	 */
	public String saveAdminPass() {
		try {
			// 验证学生会话是否失效
			if (!validateAdmin()) {
				return "loginTip";
			}
			// 保存修改个人密码
			// paramsUser.setUser_pass(Md5.makeMd5(paramsUser.getUser_pass()));
			adminManager.updateUser(paramsUser);
			// 更新session
			User admin = (User) Param.getSession("admin");
			if (admin != null) {
				admin.setUser_pass(paramsUser.getUser_pass());
				Param.setSession("admin", admin);
			}

			setSuccessTip("修改成功", "modifyPwd.jsp");
		} catch (Exception e) {
			setErrorTip("修改异常", "modifyPwd.jsp");
		}

		return "infoTip";
	}

	/**
	 * @Title: queryQuestion
	 * @Description: 根据用户名查找密保问题
	 * @return String
	 */
	public String inputUserName() {
		return "inputUserName";
	}

	public String queryQuestion() {
		try {
			// 得到账号信息
			User user = adminManager.queryUser(paramsUser);
			if (user == null) {
				tip = "该用户名不存在!";
				Param.setAttribute("user_name", paramsUser.getUser_name());
				return "inputUserName";
			}
			Param.setAttribute("user", user);

		} catch (Exception e) {
			tip = "查找用户名异常!";
			Param.setAttribute("user_name", paramsUser.getUser_name());
			return "inputUserName";
		}

		return "inputUserAnswer";
	}

	/**
	 * @Title: validAnswer
	 * @Description: 验证密保问题
	 * @return String
	 */
	public String validAnswer() {
		try {
			// 得到账号信息
			String answer = paramsUser.getUser_answer();
			User user = adminManager.queryUser(paramsUser);
			if (!answer.equals(user.getUser_answer())) {
				tip = "密保答案错误!";
				Param.setAttribute("user_answer", answer);
				Param.setAttribute("user", user);
				return "inputUserAnswer";
			}
			Param.setAttribute("user", user);

		} catch (Exception e) {
			tip = "密保答案错误!";
			Param.setAttribute("user_answer", paramsUser.getUser_answer());
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "inputUserAnswer";
		}

		return "resetPass";
	}

	/**
	 * @Title: resetPass
	 * @Description: 重置密码
	 * @return String
	 */
	public String resetPass() {
		try {
			// 重置密码
			adminManager.updateUser(paramsUser);

		} catch (Exception e) {
			tip = "密码重置异常!";
			Param.setAttribute("user", adminManager.queryUser(paramsUser));
			return "resetPass";
		}
		setSuccessTip("密码重置成功", "login.jsp");
		return "infoTip";
	}

	/**
	 * @Title: listUsers
	 * @Description: 查询学生
	 * @return String
	 */
	public String listUsers() {
		try {
			if (paramsUser == null) {
				paramsUser = new User();
			}
			// 查看学生信息
			paramsUser.setUser_type(1);

			// 设置分页信息
			setPagination(paramsUser);
			// 总的条数
			int[] sum = { 0 };
			// 查询学生列表
			List<User> users = adminManager.listUsers(paramsUser, sum);

			Param.setAttribute("users", users);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询学生异常", "main.jsp");
			return "infoTip";
		}

		return "userShow";
	}

	/**
	 * @Title: queryCourse
	 * @Description: 查询课程
	 * @return String
	 */
	public String queryCourse() {
		try {
			if (paramsCourse == null) {
				paramsCourse = new Course();
			}
			// 限制为教师身份
			User admin = (User) Param.getSession("admin");

			paramsCourse.setUser_id(admin.getUser_id());
			paramsCourse.setStart(-1);
			// 查询课程列表
			List<Course> courses = adminManager.listCourses(paramsCourse, null);

			setResult("courses", courses);

		} catch (Exception e) {
			setErrorReason("查询课程信息失败，服务器异常！", e);
			return "error";
		}

		return "success";
	}

	/**
	 * @Title: queryStudent
	 * @Description: 查询学生
	 * @return String
	 */
	public String queryStudent() {
		try {
			if (paramsUser == null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(1);
			paramsUser.setStart(-1);
			// 查询学生列表
			List<User> users = adminManager.listUsers(paramsUser, null);

			setResult("users", users);

		} catch (Exception e) {
			setErrorReason("查询学生信息失败，服务器异常！", e);
			return "error";
		}

		return "success";
	}

	/**
	 * @Title: queryStudent2
	 * @Description: 查询学生（选修）
	 * @return String
	 */
	public String queryStudent2() {
		try {
			if (paramsScource == null) {
				paramsScource = new Scource();
			}
			paramsScource.setStart(-1);
			// 查询学生（选修）列表
			List<Scource> users = adminManager.listScources(paramsScource, null);

			setResult("users", users);

		} catch (Exception e) {
			setErrorReason("查询学生信息失败，服务器异常！", e);
			return "error";
		}

		return "success";
	}

	/**
	 * @Title: addUserShow
	 * @Description: 显示添加学生页面
	 * @return String
	 */
	public String addUserShow() {
		// 查询班级字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);

		return "userEdit";
	}

	/**
	 * @Title: addUser
	 * @Description: 添加学生
	 * @return String
	 */
	public String addUser() {
		try {
			// 检查学生是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user != null) {
				tip = "失败，该学号已经存在！";
				Param.setAttribute("user", paramsUser);

				// 查询班级字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);

				return "userEdit";
			}

			// 添加学生

			paramsUser.setReg_date(DateUtil.getDate(DateUtil.getCurDateTime()));
			paramsUser.setUser_type(1);
			adminManager.addUser(paramsUser);

			setSuccessTip("添加学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("添加学生异常", "Admin_listUsers.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: editUser
	 * @Description: 编辑学生
	 * @return String
	 */
	public String editUser() {
		try {
			// 得到学生
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);

			// 查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

		} catch (Exception e) {
			setErrorTip("查询学生异常", "Admin_listUsers.action");
			return "infoTip";
		}

		return "userEdit";
	}

	/**
	 * @Title: saveUser
	 * @Description: 保存编辑学生
	 * @return String
	 */
	public String saveUser() {
		try {
			// 保存编辑学生
			adminManager.updateUser(paramsUser);

			setSuccessTip("编辑成功", "Admin_listUsers.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("user", paramsUser);

			// 查询班级字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			return "userEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delUsers
	 * @Description: 删除学生
	 * @return String
	 */

	/**
	 * @Title: listTeachers
	 * @Description: 查询教师
	 * @return String
	 */
	public String listTeachers() {
		try {
			if (paramsUser == null) {
				paramsUser = new User();
			}
			paramsUser.setUser_type(2);

			// 设置分页信息
			setPagination(paramsUser);
			// 总的条数
			int[] sum = { 0 };
			// 查询教师列表
			List<User> users = adminManager.listUsers(paramsUser, sum);

			Param.setAttribute("users", users);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			e.printStackTrace();
			setErrorTip("查询教师异常", "main.jsp");
			return "infoTip";
		}

		return "teacherShow";
	}

	public String delUsers() {
		try {
			// 删除学生
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除学生成功", "Admin_listUsers.action");
		} catch (Exception e) {
			setErrorTip("删除学生异常", "Admin_listUsers.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: addTeacherShow
	 * @Description: 显示添加教师页面
	 * @return String
	 */
	public String addTeacherShow() {
		return "teacherEdit";
	}

	/**
	 * @Title: addTeacher
	 * @Description: 添加教师
	 * @return String
	 */
	public String addTeacher() {
		try {
			// 检查登录名是否存在
			User user = new User();
			user.setUser_name(paramsUser.getUser_name());
			user = adminManager.queryUser(user);
			if (user != null) {
				tip = "失败，该用户名已经存在！";
				Param.setAttribute("user", paramsUser);
				return "teacherEdit";
			}
			// 添加教师
			paramsUser.setUser_type(2);
			paramsUser.setReg_date(DateUtil.getDate(DateUtil.getCurDateTime()));
			adminManager.addUser(paramsUser);

			setSuccessTip("添加成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("添加教师异常", "Admin_listTeachers.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: editTeacher
	 * @Description: 编辑教师
	 * @return String
	 */
	public String editTeacher() {
		try {
			// 得到教师
			User user = adminManager.queryUser(paramsUser);
			Param.setAttribute("user", user);

		} catch (Exception e) {
			setErrorTip("查询教师异常", "Admin_listTeachers.action");
			return "infoTip";
		}

		return "teacherEdit";
	}

	/**
	 * @Title: saveTeacher
	 * @Description: 保存编辑教师
	 * @return String
	 */
	public String saveTeacher() {
		try {
			// 保存编辑教师
			adminManager.updateUser(paramsUser);

			setSuccessTip("编辑成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("user", paramsUser);
			return "userEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delTeachers
	 * @Description: 删除教师
	 * @return String
	 */
	public String delTeachers() {
		try {
			// 删除教师
			adminManager.delUsers(paramsUser);

			setSuccessTip("删除教师成功", "Admin_listTeachers.action");
		} catch (Exception e) {
			setErrorTip("删除教师异常", "Admin_listTeachers.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listClazzs
	 * @Description: 查询班级
	 * @return String
	 */
	public String listClazzs() {
		try {
			if (paramsClazz == null) {
				paramsClazz = new Clazz();
			}
			// 设置分页信息
			setPagination(paramsClazz);
			// 总的条数
			int[] sum = { 0 };
			// 查询班级列表
			List<Clazz> clazzs = adminManager.listClazzs(paramsClazz, sum);

			Param.setAttribute("clazzs", clazzs);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询班级异常", "main.jsp");
			return "infoTip";
		}

		return "clazzShow";
	}

	/**
	 * @Title: addClazzShow
	 * @Description: 显示添加班级页面
	 * @return String
	 */
	public String addClazzShow() {
		return "clazzEdit";
	}

	/**
	 * @Title: addClazz
	 * @Description: 添加班级
	 * @return String
	 */
	public String addClazz() {
		try {
			// 添加班级
			adminManager.addClazz(paramsClazz);

			setSuccessTip("添加成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("添加班级异常", "Admin_listClazzs.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: editClazz
	 * @Description: 编辑班级
	 * @return String
	 */
	public String editClazz() {
		try {
			// 得到班级
			Clazz clazz = adminManager.queryClazz(paramsClazz);
			Param.setAttribute("clazz", clazz);
		} catch (Exception e) {
			setErrorTip("查询班级异常", "Admin_listClazzs.action");
			return "infoTip";
		}

		return "clazzEdit";
	}

	/**
	 * @Title: saveClazz
	 * @Description: 保存编辑班级
	 * @return String
	 */
	public String saveClazz() {
		try {
			// 保存编辑班级
			adminManager.updateClazz(paramsClazz);

			setSuccessTip("编辑成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("clazz", paramsClazz);
			return "clazzEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delClazzs
	 * @Description: 删除班级
	 * @return String
	 */
	public String delClazzs() {
		try {
			// 删除班级
			adminManager.delClazzs(paramsClazz);

			setSuccessTip("删除班级成功", "Admin_listClazzs.action");
		} catch (Exception e) {
			setErrorTip("删除班级异常", "Admin_listClazzs.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listCourses
	 * @Description: 查询课程
	 * @return String
	 */
	public String listCourses() {
		try {
			if (paramsCourse == null) {
				paramsCourse = new Course();
			}
			// 设置分页信息
			setPagination(paramsCourse);
			// 总的条数
			int[] sum = { 0 };
			// 查询课程列表
			List<Course> courses = adminManager.listCourses(paramsCourse, sum);

			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}

		return "courseShow";
	}

	/**
	 * @Title: addCourseShow
	 * @Description: 显示添加课程页面
	 * @return String
	 */
	public String addCourseShow() {
		// 查询教师集合
		User user = new User();
		user.setUser_type(2);
		user.setStart(-1);
		List<User> teachers = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", teachers);

		return "courseEdit";
	}

	/**
	 * @Title: addCourse
	 * @Description: 添加课程
	 * @return String
	 */
	public String addCourse() {
		try {
			// 判断教师是否任教
			if (paramsCourse.getCourse_type() == 2) {
				Course course = new Course();
				course.setCourse_type(2);
				course.setTeacher_id(paramsCourse.getTeacher_id());
				course = adminManager.queryCourse(course);
				if (course != null) {
					setErrorTip("添加课程异常，每个教师只能教一门选修课", "Admin_listCourses.action");
					return "infoTip";
				}
			}

			// 添加课程
			adminManager.addCourse(paramsCourse);

			setSuccessTip("添加成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("添加课程异常", "Admin_listCourses.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: editCourse
	 * @Description: 编辑课程
	 * @return String
	 */
	public String editCourse() {
		try {
			// 得到课程
			Course course = adminManager.queryCourse(paramsCourse);
			Param.setAttribute("course", course);

			// 查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);

		} catch (Exception e) {
			setErrorTip("查询课程异常", "Admin_listCourses.action");
			return "infoTip";
		}

		return "courseEdit";
	}

	/**
	 * @Title: saveCourse
	 * @Description: 保存编辑课程
	 * @return String
	 */
	public String saveCourse() {
		try {
			// 判断教师是否任教
			if (paramsCourse.getCourse_type() == 2) {
				Course course = new Course();
				course.setCourse_type(2);
				course.setTeacher_id(paramsCourse.getTeacher_id());
				course = adminManager.queryCourse(course);
				if (course != null && course.getCourse_id() != paramsCourse.getCourse_id()) {
					setErrorTip("编辑课程异常，每个教师只能教一门选修课", "Admin_listCourses.action");
					return "infoTip";
				}
			}

			// 保存编辑课程
			adminManager.updateCourse(paramsCourse);

			setSuccessTip("编辑成功", "Admin_listCourses.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("course", paramsCourse);

			// 查询教师集合
			User user = new User();
			user.setUser_type(2);
			user.setStart(-1);
			List<User> teachers = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", teachers);

			return "courseEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delCourses
	 * @Description: 删除课程
	 * @return String
	 */
	public String delCourses() {
		try {
			// 删除课程
			adminManager.delCourses(paramsCourse);

			setSuccessTip("删除课程成功", "Admin_listCourses.action");
		} catch (Exception e) {
			setErrorTip("删除课程异常", "Admin_listCourses.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listPlans
	 * @Description: 查询教学计划
	 * @return String
	 */
	public String listPlans() {
		try {
			if (paramsPlan == null) {
				paramsPlan = new Plan();
			}
			// 设置分页信息
			setPagination(paramsPlan);
			// 总的条数
			int[] sum = { 0 };
			// 查询教学计划列表
			List<Plan> plans = adminManager.listPlans(paramsPlan, sum);

			Param.setAttribute("plans", plans);
			setTotalCount(sum[0]);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();

			course.setCourse_type(1);
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

		} catch (Exception e) {
			setErrorTip("查询教学计划异常", "main.jsp");
			return "infoTip";
		}

		return "planShow";
	}

	/**
	 * @Title: addPlanShow
	 * @Description: 显示添加教学计划页面
	 * @return String
	 */
	public String addPlanShow() {
		// 查询班级、课程字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);

		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(1);
		List<Course> courses = adminManager.listCourses(course, null);
		Param.setAttribute("courses", courses);

		// 查询教师字典
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("users", users);

		return "planEdit";
	}

	/**
	 * @Title: addPlan
	 * @Description: 添加教学计划
	 * @return String
	 */
	public String addPlan() {
		try {
			// 判断教学计划是否已经添加
			Plan plan = adminManager.queryPlan(paramsPlan);
			if (plan != null) {
				tip = "失败，该教师本次教学计划已经存在！";
				Param.setAttribute("plan", paramsPlan);

				// 查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);

				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);

				// 查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("users", users);

				return "planEdit";
			}

			// 添加教学计划
			adminManager.addPlan(paramsPlan);

			setSuccessTip("添加成功", "Admin_listPlans.action");
		} catch (Exception e) {
			setErrorTip("添加教学计划异常", "Admin_listPlans.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: editPlan
	 * @Description: 编辑教学计划
	 * @return String
	 */
	public String editPlan() {
		try {
			// 得到教学计划
			Plan plan = adminManager.queryPlan(paramsPlan);
			Param.setAttribute("plan", plan);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

			// 查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("users", users);

		} catch (Exception e) {
			setErrorTip("查询教学计划异常", "Admin_listPlans.action");
			return "infoTip";
		}

		return "planEdit";
	}

	/**
	 * @Title: savePlan
	 * @Description: 保存编辑教学计划
	 * @return String
	 */
	public String savePlan() {
		try {
			// 验证教学计划是否存在
			int plan_id = paramsPlan.getPlan_id();
			paramsPlan.setPlan_id(0);
			Plan plan = adminManager.queryPlan(paramsPlan);
			paramsPlan.setPlan_id(plan_id);
			if (plan != null && plan.getPlan_id() != plan_id) {
				tip = "失败，该教师本次教学计划已经存在！";
				Param.setAttribute("plan", paramsPlan);

				// 查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);

				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);

				// 查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("users", users);

				return "planEdit";
			}

			// 保存编辑教学计划
			adminManager.updatePlan(paramsPlan);

			setSuccessTip("编辑成功", "Admin_listPlans.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("plan", paramsPlan);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

			// 查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("users", users);

			return "planEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delPlans
	 * @Description: 删除教学计划
	 * @return String
	 */
	public String delPlans() {
		try {
			// 删除教学计划
			adminManager.delPlans(paramsPlan);

			setSuccessTip("删除教学计划成功", "Admin_listPlans.action");
		} catch (Exception e) {
			setErrorTip("删除教学计划异常", "Admin_listPlans.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listCplans
	 * @Description: 查询班级课表
	 * @return String
	 */
	public String listCplans() {
		try {
			if (paramsCplan == null) {
				paramsCplan = new Cplan();
			}
			// 设置分页信息
			setPagination(paramsCplan);
			// 总的条数
			int[] sum = { 0 };
			// 查询班级课表列表
			List<Cplan> cplans = adminManager.listCplans(paramsCplan, sum);

			Param.setAttribute("cplans", cplans);
			setTotalCount(sum[0]);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "main.jsp");
			return "infoTip";
		}

		return "cplanShow";
	}

	/**
	 * @Title: listCplansByClazz
	 * @Description: 查询班级课表一周视图
	 * @return String
	 */
	public String listCplansByClazzShow() {
		try {
			// 查询班级
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

		} catch (Exception e) {
			setErrorTip("查询班级课表一周视图异常", "main.jsp");
			return "infoTip";
		}

		return "cplanByClazzShow";
	}

	public String listCplansByClazz() {
		try {
			if (paramsCplan == null) {
				paramsCplan = new Cplan();
			}
			// 查询班级课表列表
			List<Cplan> cplans = adminManager.listCplansByClazz(paramsCplan);
			Param.setAttribute("cplans", cplans);

			// 查询班级
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);
		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "main.jsp");
			return "infoTip";
		}

		return "cplanByClazzShow";
	}

	/**
	 * @Title: addCplanShow
	 * @Description: 显示添加班级课表页面
	 * @return String
	 */
	public String addCplanShow() {
		// 查询班级、课程字典
		Clazz clazz = new Clazz();
		clazz.setStart(-1);
		List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
		Param.setAttribute("clazzs", clazzs);

		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(1);
		List<Course> courses = adminManager.listCourses(course, null);
		Param.setAttribute("courses", courses);

		return "cplanEdit";
	}

	/**
	 * @Title: addCplan
	 * @Description: 添加班级课表
	 * @return String
	 */
	public String addCplan() {
		try {
			// 判断班级课表是否已经添加
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			if (cplan != null) {
				tip = "失败，该班级本次班级课表已经存在！";
				Param.setAttribute("cplan", paramsCplan);

				// 查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);

				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);

				return "cplanEdit";
			}

			// 添加班级课表
			adminManager.addCplan(paramsCplan);

			setSuccessTip("添加成功", "Admin_listCplans.action");
		} catch (Exception e) {
			setErrorTip("添加班级课表异常", "Admin_listCplans.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: editCplan
	 * @Description: 编辑班级课表
	 * @return String
	 */
	public String editCplan() {
		try {
			// 得到班级课表
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			Param.setAttribute("cplan", cplan);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);
		} catch (Exception e) {
			setErrorTip("查询班级课表异常", "Admin_listCplans.action");
			return "infoTip";
		}

		return "cplanEdit";
	}

	/**
	 * @Title: saveCplan
	 * @Description: 保存编辑班级课表
	 * @return String
	 */
	public String saveCplan() {
		try {
			// 验证班级课表是否存在
			int cplan_id = paramsCplan.getCplan_id();
			paramsCplan.setCplan_id(0);
			Cplan cplan = adminManager.queryCplan(paramsCplan);
			paramsCplan.setCplan_id(cplan_id);
			if (cplan != null && cplan.getCplan_id() != cplan_id) {
				tip = "失败，该班级本次班级课表已经存在！";
				Param.setAttribute("cplan", paramsCplan);

				// 查询班级、课程字典
				Clazz clazz = new Clazz();
				clazz.setStart(-1);
				List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
				Param.setAttribute("clazzs", clazzs);

				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(1);
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);

				return "cplanEdit";
			}

			// 保存编辑班级课表
			adminManager.updateCplan(paramsCplan);

			setSuccessTip("编辑成功", "Admin_listCplans.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("cplan", paramsCplan);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			course.setCourse_type(1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

			return "cplanEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delCplans
	 * @Description: 删除班级课表
	 * @return String
	 */
	public String delCplans() {
		try {
			// 删除班级课表
			adminManager.delCplans(paramsCplan);

			setSuccessTip("删除班级课表成功", "Admin_listCplans.action");
		} catch (Exception e) {
			setErrorTip("删除班级课表异常", "Admin_listCplans.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listScources
	 * @Description: 查询学生选课
	 * @return String
	 */
	public String listScources() {
		try {
			if (paramsScource == null) {
				paramsScource = new Scource();
			}
			// 设置分页信息
			setPagination(paramsScource);
			// 总的条数
			int[] sum = { 0 };
			// 查询学生选课列表
			User admin = (User) Param.getSession("admin");
			paramsScource.setUser_id(admin.getUser_id());
			List<Scource> scources = adminManager.listScources(paramsScource, sum);

			Param.setAttribute("scources", scources);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询学生选课异常", "main.jsp");
			return "infoTip";
		}

		return "scourceShow";
	}

	/**
	 * @Title: addScourceShow
	 * @Description: 显示添加学生选课页面
	 * @return String
	 */
	public String addScourceShow() {
		try {
			if (paramsCourse == null) {
				paramsCourse = new Course();
			}
			// 设置分页信息
			setPagination(paramsCourse);
			// 总的条数
			int[] sum = { 0 };
			// 查询课程列表
			paramsCourse.setCourse_type(2);
			List<Course> courses = adminManager.listCourses(paramsCourse, sum);

			Param.setAttribute("courses", courses);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询课程异常", "main.jsp");
			return "infoTip";
		}

		return "scourceEdit";
	}

	/**
	 * @Title: addScource
	 * @Description: 添加学生选课
	 * @return String
	 */
	public String addScource() {
		try {
			// 判断学生选课是否已经添加
			Scource scource = adminManager.queryScource(paramsScource);
			if (scource != null) {
				setErrorTip("失败，该课程已经选修了！", "Admin_addScourceShow.action");
				return "infoTip";
			}
			// 判断人数是否超员
			Course course = new Course();
			course.setScore_year(paramsScource.getScource_year());
			course.setScore_year_half(paramsScource.getScource_year_half());
			Map<Integer, Integer> sumCountMap = adminManager.listScourcesPerson(course);
			if (sumCountMap.containsKey(paramsScource.getCourse_id())
					&& sumCountMap.get(paramsScource.getCourse_id()) >= 55) {
				setErrorTip("失败，每门课程最多只能55人选！", "Admin_addScourceShow.action");
				return "infoTip";
			}

			// jbpm begin 创建流程实例 -xf
			Map<String, String> variables = new HashMap<String, String>();
			variables.put("application", paramsScource.getUser_name());
			ProcessInstance processInstance = processEngine.getExecutionService().startProcessInstanceByKey("test",
					variables);
			String pDId = processInstance.getProcessDefinitionId(); // pDId:----test-1
			String pIid = processInstance.getId(); // pIid:----test.60001
			processEngine.getExecutionService().signalExecutionById(pIid, "to 张老师审批");
			// jbpm

			// 设置flag
			paramsScource.setFlag(1); // 1代表申请选修 待张老师审核
			paramsScource.setPiid(pIid);

			// end -xf

			// 添加学生选课
			adminManager.addScource(paramsScource);

			setSuccessTip("选修成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("选修异常", "Admin_addScourceShow.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: delScources
	 * @Description: 删除学生选课
	 * @return String
	 */
	public String delScources() {
		try {
			// 删除学生选课
			adminManager.delScources(paramsScource);

			setSuccessTip("删除学生选课成功", "Admin_listScources.action");
		} catch (Exception e) {
			setErrorTip("删除学生选课异常", "Admin_listScources.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listScores
	 * @Description: 查询成绩
	 * @return String
	 */
	public String listScores() {
		try {
			if (paramsScore == null) {
				paramsScore = new Score();
			}
			// 设置分页信息
			setPagination(paramsScore);
			// 总的条数
			int[] sum = { 0 };
			// 查询成绩列表
			User admin = (User) Param.getSession("admin");// 查询当前用户
			if (admin.getUser_type() == 2) {
				paramsScore.setTeacher_id(admin.getUser_id());// 设置教师为当前用户
			} else if (admin.getUser_type() == 1) {
				paramsScore.setUser_id(admin.getUser_id());// 设置学生为当前用户
			}

			List<Score> scores = adminManager.listScores(paramsScore, sum);

			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}

		return "scoreShow";
	}

	/**
	 * @Title: listScoresSum
	 * @Description: 查询成绩
	 * @return String
	 */
	public String listScoresSum() {
		try {
			if (paramsScore == null) {
				paramsScore = new Score();
			}
			// 设置分页信息
			setPagination(paramsScore);
			// 总的条数
			int[] sum = { 0 };
			// 查询成绩列表
			User admin = (User) Param.getSession("admin");// 查询当前用户
			if (admin.getUser_type() == 2) {
				paramsScore.setTeacher_id(admin.getUser_id());// 设置教师为当前用户
			} else if (admin.getUser_type() == 1) {
				paramsScore.setUser_id(admin.getUser_id());// 设置学生为当前用户
			}

			List<Score> scores = adminManager.listScoresSum(paramsScore, sum);

			Param.setAttribute("scores", scores);
			setTotalCount(sum[0]);

			// 查询班级、课程字典
			Clazz clazz = new Clazz();
			clazz.setStart(-1);
			List<Clazz> clazzs = adminManager.listClazzs(clazz, null);
			Param.setAttribute("clazzs", clazzs);

			Course course = new Course();
			course.setStart(-1);
			List<Course> courses = adminManager.listCourses(course, null);
			Param.setAttribute("courses", courses);

		} catch (Exception e) {
			setErrorTip("查询成绩异常", "main.jsp");
			return "infoTip";
		}

		return "scoreSumShow";
	}

	/**
	 * @Title: addScoreShow
	 * @Description: 显示添加成绩页面
	 * @return String
	 */
	public String addScoreShow() {
		return "scoreEdit";
	}

	/**
	 * @Title: addScoreSelectShow
	 * @Description: 添加选修成绩
	 * @return String
	 */
	public String addScoreSelectShow() {
		// 查询课程
		User admin = (User) Param.getSession("admin");
		Course course = new Course();
		course.setStart(-1);
		course.setCourse_type(2);// 类型为选修
		course.setTeacher_id(admin.getUser_id());// 设置教师ID
		List<Course> courses = adminManager.listCourses(course, null);
		if (courses == null || courses.size() == 0) {
			courses = new ArrayList<Course>();
		}
		Param.setAttribute("courses", courses);

		return "scoreSelectEdit";
	}

	/**
	 * @Title: addScore
	 * @Description: 添加成绩
	 * @return String
	 */
	public String addScore() {
		try {
			// 判断成绩是否已经添加
			Score score = adminManager.queryScore(paramsScore);
			if (score != null) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);

				return "scoreEdit";
			}

			// 添加成绩
			adminManager.addScore(paramsScore);

			setSuccessTip("添加成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("添加成绩异常", "Admin_listScores.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: addScoreSelect
	 * @Description: 添加选修成绩
	 * @return String
	 */
	public String addScoreSelect() {
		try {
			// 判断成绩是否已经添加
			Score score = adminManager.queryScore(paramsScore);
			if (score != null) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);

				return "scoreSelectEdit";
			}

			// 添加成绩
			adminManager.addScore(paramsScore);

			setSuccessTip("添加成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("添加成绩异常", "Admin_listScores.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: editScore
	 * @Description: 编辑成绩
	 * @return String
	 */
	public String editScore() {
		String returnPage = "scoreEdit";// 返回界面
		try {
			// 得到成绩
			Score score = adminManager.queryScore(paramsScore);
			Param.setAttribute("score", score);

			// 如果是选修课，查询课程字典
			if (score.getCourse_type() == 2) {
				User admin = (User) Param.getSession("admin");
				Course course = new Course();

				course.setTeacher_id(admin.getUser_id());// 设置教师ID
				course.setStart(-1);
				course.setCourse_type(2);// 类型为选修

				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);

				returnPage = "scoreSelectEdit";
			}

		} catch (Exception e) {
			setErrorTip("查询成绩异常", "Admin_listScores.action");
			return "infoTip";
		}

		return returnPage;
	}

	/**
	 * @Title: saveScore
	 * @Description: 保存编辑成绩
	 * @return String
	 */
	public String saveScore() {
		String returnPage = "scoreEdit";// 返回界面
		try {
			// 如果是选修课，修改返回界面
			if (paramsScore.getCourse_type() == 2) {
				returnPage = "scoreSelectEdit";
			}

			// 判断成绩是否已经添加
			int score_id = paramsScore.getScore_id();
			paramsScore.setScore_id(0);
			Score score = adminManager.queryScore(paramsScore);
			paramsScore.setScore_id(score_id);
			if (score != null && score.getScore_id() != score_id) {
				tip = "失败，该学生本次成绩已经存在！";
				Param.setAttribute("score", paramsScore);

				// 如果是选修课，查询课程字典
				if (paramsScore.getCourse_type() == 2) {
					User admin = (User) Param.getSession("admin");
					Course course = new Course();
					course.setStart(-1);
					course.setCourse_type(2);// 类型为选修
					course.setTeacher_id(admin.getUser_id());// 设置教师ID
					List<Course> courses = adminManager.listCourses(course, null);
					Param.setAttribute("courses", courses);
				}

				return returnPage;
			}
			// 保存编辑成绩
			adminManager.updateScore(paramsScore);

			setSuccessTip("编辑成功", "Admin_listScores.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("score", paramsScore);

			// 如果是选修课，查询课程字典
			if (paramsScore.getCourse_type() == 2) {
				User admin = (User) Param.getSession("admin");
				Course course = new Course();
				course.setStart(-1);
				course.setCourse_type(2);// 类型为选修
				course.setTeacher_id(admin.getUser_id());// 设置教师ID
				List<Course> courses = adminManager.listCourses(course, null);
				Param.setAttribute("courses", courses);
			}

			return returnPage;
		}

		return "infoTip";
	}

	/**
	 * @Title: delScores
	 * @Description: 删除成绩
	 * @return String
	 */
	public String delScores() {
		try {
			// 删除成绩
			adminManager.delScores(paramsScore);

			setSuccessTip("删除成绩成功", "Admin_listScores.action");
		} catch (Exception e) {
			setErrorTip("删除成绩异常", "Admin_listScores.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: listEvaluates
	 * @Description: 查询教师评教
	 * @return String
	 */
	public String listEvaluates() {
		try {
			if (paramsEvaluate == null) {
				paramsEvaluate = new Evaluate();
			}
			// 设置分页信息
			setPagination(paramsEvaluate);
			// 总的条数
			int[] sum = { 0 };
			// 查询教师评教列表
			User admin = (User) Param.getSession("admin");// 查询当前用户
			if (admin.getUser_type() == 1) {
				paramsEvaluate.setStudent_id(admin.getUser_id());// 设置学生为当前用户
			}

			List<Evaluate> evaluates = adminManager.listEvaluates(paramsEvaluate, sum);

			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);
		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}

		return "evaluateShow";
	}

	/**
	 * @Title: listEvaluatesSum
	 * @Description: 教学评教平均得分
	 * @return String
	 */
	public String listEvaluatesSum() {
		try {
			if (paramsEvaluate == null) {
				paramsEvaluate = new Evaluate();
			}
			// 设置分页信息
			setPagination(paramsEvaluate);
			// 总的条数
			int[] sum = { 0 };
			// 查询教师评教列表
			List<Evaluate> evaluates = adminManager.listEvaluatesSum(paramsEvaluate, sum);

			Param.setAttribute("evaluates", evaluates);
			setTotalCount(sum[0]);

		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "main.jsp");
			return "infoTip";
		}

		return "evaluateSumShow";
	}

	/**
	 * @Title: addEvaluateShow
	 * @Description: 显示添加教师评教页面
	 * @return String
	 */
	public String addEvaluateShow() {
		// 查询教师字典
		User user = new User();
		user.setStart(-1);
		user.setUser_type(2);
		List<User> users = adminManager.listUsers(user, null);
		Param.setAttribute("teachers", users);

		return "evaluateEdit";
	}

	/**
	 * @Title: addEvaluate
	 * @Description: 添加教师评教
	 * @return String
	 */
	public String addEvaluate() {
		try {
			// 判断教师评教是否已经添加
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			if (evaluate != null) {
				tip = "失败，本次教师评教已经存在！";
				Param.setAttribute("evaluate", paramsEvaluate);

				// 查询教师字典
				User user = new User();
				user.setStart(-1);
				user.setUser_type(2);
				List<User> users = adminManager.listUsers(user, null);
				Param.setAttribute("teachers", users);

				return "evaluateEdit";
			}

			// 添加教师评教
			adminManager.addEvaluate(paramsEvaluate);

			setSuccessTip("添加教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("添加教师评教异常", "Admin_listEvaluates.action");
			e.printStackTrace();
		}

		return "infoTip";
	}

	/**
	 * @Title: editEvaluate
	 * @Description: 编辑教师评教
	 * @return String
	 */
	public String editEvaluate() {
		try {
			// 得到教师评教
			Evaluate evaluate = adminManager.queryEvaluate(paramsEvaluate);
			Param.setAttribute("evaluate", evaluate);

			// 查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);

		} catch (Exception e) {
			setErrorTip("查询教师评教异常", "Admin_listEvaluates.action");
			return "infoTip";
		}

		return "evaluateEdit";
	}

	/**
	 * @Title: saveEvaluate
	 * @Description: 保存编辑教师评教
	 * @return String
	 */
	public String saveEvaluate() {
		try {
			// 保存编辑教师评教
			adminManager.updateEvaluate(paramsEvaluate);

			setSuccessTip("编辑成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			tip = "编辑失败";
			Param.setAttribute("evaluate", paramsEvaluate);

			// 查询教师字典
			User user = new User();
			user.setStart(-1);
			user.setUser_type(2);
			List<User> users = adminManager.listUsers(user, null);
			Param.setAttribute("teachers", users);

			return "evaluateEdit";
		}

		return "infoTip";
	}

	/**
	 * @Title: delEvaluates
	 * @Description: 删除教师评教
	 * @return String
	 */
	public String delEvaluates() {
		try {
			// 删除教师评教
			adminManager.delEvaluates(paramsEvaluate);

			setSuccessTip("删除教师评教成功", "Admin_listEvaluates.action");
		} catch (Exception e) {
			setErrorTip("删除教师评教异常", "Admin_listEvaluates.action");
		}

		return "infoTip";
	}

	/**
	 * @Title: validateAdmin
	 * @Description: admin登录验证
	 * @return boolean
	 */
	private boolean validateAdmin() {
		User admin = (User) Param.getSession("admin");
		if (admin != null) {
			return true;
		} else {
			return false;
		}
	}

	private void setErrorTip(String tip, String url) {
		Param.setAttribute("tipType", "error");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	private void setSuccessTip(String tip, String url) {
		Param.setAttribute("tipType", "success");
		Param.setAttribute("tip", tip);
		Param.setAttribute("url1", url);
		Param.setAttribute("value1", "确 定");
	}

	public User getParamsUser() {
		return paramsUser;
	}

	public void setParamsUser(User paramsUser) {
		this.paramsUser = paramsUser;
	}

	public Clazz getParamsClazz() {
		return paramsClazz;
	}

	public void setParamsClazz(Clazz paramsClazz) {
		this.paramsClazz = paramsClazz;
	}

	public Course getParamsCourse() {
		return paramsCourse;
	}

	public void setParamsCourse(Course paramsCourse) {
		this.paramsCourse = paramsCourse;
	}

	public Score getParamsScore() {
		return paramsScore;
	}

	public void setParamsScore(Score paramsScore) {
		this.paramsScore = paramsScore;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Plan getParamsPlan() {
		return paramsPlan;
	}

	public void setParamsPlan(Plan paramsPlan) {
		this.paramsPlan = paramsPlan;
	}

	public Cplan getParamsCplan() {
		return paramsCplan;
	}

	public void setParamsCplan(Cplan paramsCplan) {
		this.paramsCplan = paramsCplan;
	}

	public Scource getParamsScource() {
		return paramsScource;
	}

	public void setParamsScource(Scource paramsScource) {
		this.paramsScource = paramsScource;
	}

	public Evaluate getParamsEvaluate() {
		return paramsEvaluate;
	}

	public void setParamsEvaluate(Evaluate paramsEvaluate) {
		this.paramsEvaluate = paramsEvaluate;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public SScore getParamsSScore() {
		return paramsSScore;
	}

	public void setParamsSScore(SScore paramsSScore) {
		this.paramsSScore = paramsSScore;
	}

	public Canshu getParamsCanshu() {
		return paramsCanshu;
	}

	public void setParamsCanshu(Canshu paramsCanshu) {
		this.paramsCanshu = paramsCanshu;
	}

	public String getPiid() {
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	/**
	 * 小锋 获取图片流程
	 * 
	 * @param id
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String listindexpic() {
		try {
			RepositoryService repositoryService = processEngine.getRepositoryService();

			ExecutionService executionService = processEngine.getExecutionService();

			ProcessInstance processInstance = executionService.findProcessInstanceById(piid); // 根据ID获取流程实例
			Set<String> activityNames = processInstance.findActiveActivityNames(); // 获取实例执行到的当前节点的名称

			String processDefinitionId = processInstance.getProcessDefinitionId();

			System.out.println("processDefinitionId" + processDefinitionId); // test-5
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
					.processDefinitionId(processDefinitionId).uniqueResult();

			InputStream in = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
					"hello/test.png");

			/*
			 * ActivityCoordinates ac = repositoryService.getActivityCoordinates(
			 * processInstance.getProcessDefinitionId(), activityNames .iterator().next());
			 */

			Image i = ImageIO.read(in);
			BufferedImage img = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = img.createGraphics();
			g.drawImage(i, null, null);
			g.setColor(Color.RED);

			Iterator<String> iter = activityNames.iterator();
			while (iter.hasNext()) {
				String name = iter.next();
				ActivityCoordinates ac = repositoryService.getActivityCoordinates(processDefinitionId, name);
				if (ac != null) {
					g.drawRect(ac.getX(), ac.getY(), ac.getWidth(), ac.getHeight());
				}
			}
			// 禁止图像缓存。
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/png");
			ImageIO.write(img, "png", response.getOutputStream());

			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	/**
//	 * @Title: saveSScore
//	 * @Description: 更改flag -xf
//	 * @return String
//	 */
//	public String passSScore() {
//		try {
//			// 保存编辑评测成绩
//			adminManager.updateFlag(paramsSScore);
//			// jbpm begin
//	
//			String gotos = "";
//			if (paramsSScore.getFlag() == 2) {
//				gotos = "to 李老师审批";
//			} else if (paramsSScore.getFlag() == 3) {
//				gotos = "to 王老师审批";
//			} else if (paramsSScore.getFlag() == 4) {
//				gotos = "to end1";
//			}
//	
//			processEngine.getExecutionService().signalExecutionById(paramsSScore.getPiid(), gotos);
//			// jbpm end
//	
//			paramsSScore.setScore_year(DateUtil.getYear(new Date()));
//			// 设置分页信息
//			paramsSScore.setStart(-1);
//			// 身份限制
//			User admin = (User) Param.getSession("admin");
//			if (admin.getUser_type() == 1) {
//				paramsSScore.setUser_id(admin.getUser_id());
//			} else if (admin.getUser_type() == 2) {
//				paramsSScore.setTeacher_id(admin.getUser_id());
//			}
//			// 查询评测成绩列表
//			List<SScore> sscores = adminManager.listSScores(paramsSScore, null);
//			Param.setAttribute("sscores", sscores);
//	
//		} catch (Exception e) {
//			e.printStackTrace();
//			setErrorReason("通过失败");
//			return "error2";
//		}
//	
//		return "sscoreShow";
//	}

}
