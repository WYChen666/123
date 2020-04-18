package com.nkl.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.Configuration;
import org.jbpm.api.Deployment;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryTask;
import org.jbpm.api.task.Task;
import org.junit.Test;

public class TestController {

	private ProcessEngine processEngine = new Configuration().setResource("jbpm.cfg.xml").buildProcessEngine();

	@Test
	public void createSchema() {
		new org.hibernate.cfg.Configuration().configure("jdpm/hibernate.cfg.xml").buildSessionFactory();
	}

	// 部署流程定义 开始!!!
	@Test
	public void deployProcessDefinition() {
		processEngine.getRepositoryService()//
				.createDeployment()//
				.addResourceFromClasspath("hello/test.jpdl.xml")//
				.addResourceFromClasspath("hello/test.png")//
				.deploy();
	}

	// 启动流程实例
	@Test
	public void startProcessInstance() {
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("application", "小美");
		processEngine.getExecutionService().startProcessInstanceByKey("test", variables);
	}

	// 获取流程定义processDefinition
	@Test
	public void getDefinition() {
		List<Deployment> list = processEngine.getRepositoryService().createDeploymentQuery().list();
		System.out.println("-------------------------------------------------------------------");
		for (Deployment deployment : list) {
			System.out.println("Deployment Id=" + deployment.getId());
			System.out.println("Deployment Name=" + deployment.getName());
			System.out.println("Deployment Version=" + deployment.getState());
		}
		System.out.println("-------------------------------------------------------------------");

		List<ProcessDefinition> processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery()
				.list();
		System.out.println("-------------------------------------------------------------------");
		for (ProcessDefinition processDefinition2 : processDefinition) {

			System.out.println("processDefinitionDeploymentId =:" + processDefinition2.getDeploymentId());
			System.out.println("processDefinitionDescription =:" + processDefinition2.getDescription());
			System.out.println("processDefinitionId =:" + processDefinition2.getId());
			System.out.println("processDefinitionKey =:" + processDefinition2.getKey());
		}
		System.out.println("-------------------------------------------------------------------");

	}

	//// 获取流程实例processInstace
	@Test
	public void getInstance() {
		List<ProcessInstance> list = processEngine.getExecutionService().createProcessInstanceQuery().list();
		for (ProcessInstance processInstance : list) {
			System.out.println(processInstance.getProcessDefinitionId() + ":::" + processInstance.getId());
		}
		ProcessInstance uniqueResult = processEngine.getExecutionService().createProcessInstanceQuery()
				.processDefinitionId("test.190001").uniqueResult();
		System.out.println("999999" + uniqueResult.getId());
	}

	@Test
	public void findMyTaskList() {
		// 查询任务
		String userId = "小美";
		// List<Task> list = processEngine.getTaskService().findPersonalTasks(userId);
		List<Task> list = processEngine.getTaskService().createTaskQuery().assignee(userId).list();

		// 显示任务
		System.out.println("========= 【" + userId + "】的未办理的任务列表 =========");
		for (Task task : list) {
			System.out.println("id=" + task.getId()//
					+ ", name=" + task.getName()//
					+ ", assignee=" + task.getAssignee()//
					+ ", createTime=" + task.getCreateTime() + ", ExecutionId=" + task.getExecutionId());
		}
	}

	@Test
	public void findMyHisTaskList() {
		// 查询历史任务
		String userId = "小美";
		// List<Task> list = processEngine.getTaskService().findPersonalTasks(userId);
		List<HistoryTask> list = processEngine.getHistoryService().createHistoryTaskQuery().assignee(userId)
				.executionId("test.360001").list();

		// 显示任务
		System.out.println("========= 【" + userId + "】的历史任务列表 =========");
		for (HistoryTask task : list) {
			System.out.println("id=" + task.getId()//
					+ ", name=" + task.getOutcome()//
					+ ", assignee=" + task.getAssignee()//
					+ ", createTime=" + task.getCreateTime() + ", ExecutionId=" + task.getExecutionId());
		}
	}

	// 办理任务(完成任务)
	@Test
	public void completeTask() {
		String taskId = "360003";
		processEngine.getTaskService().completeTask(taskId);
	}

	// 和办理任务类似
	@Test
	public void completeExecution() {
		// 准备流程变量
		String taskId = "380001";
		HashMap<String, Object> variables = new HashMap<String, Object>();
		variables.put("ddd", "1");
		processEngine.getTaskService().completeTask(taskId, "驳回", variables);
	}

	// 办理任务类似
	@Test
	public void completeExecution1() {
		// 准备流程变量

		processEngine.getExecutionService().signalExecutionById("test.40001", "审批通过");
		// processEngine.getExecutionService().signalExecutionById("test.40001", "to
		// end1");
		// processEngine.getExecutionService().signalExecutionById("test.40001", "to
		// end1");
	}

	/**
	 * 先获取流程变成参数 再办理任务
	 */
	@Test
	public void getVarCompleteTask() {
		String taskId = "80001";
		String day = (String) processEngine.getTaskService().getVariable(taskId, "day");
		System.out.println(day);

	}

	/**
	 * 开始新的流程 张三 申请请假 -> 李四同意 ->王二麻子不同意
	 */
	@Test
	public void testzhangsan() {

	}

	/**
	 * 查询历史任务
	 */
	@Test
	public void QueryHisTask() {
		List<HistoryTask> list = processEngine.getHistoryService().createHistoryTaskQuery().state(Task.STATE_COMPLETED)
				.executionId("test.80001").list();
		for (HistoryTask historyTask : list) {
			System.out.println("-----------" + historyTask.getId());
		}
		System.out.println("-----------");
	}

}
