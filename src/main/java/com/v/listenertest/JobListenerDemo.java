package com.v.listenertest;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobListenerDemo implements JobListener {
	Logger _log = LoggerFactory.getLogger(JobListenerDemo.class);
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MyJobListener";
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		// TODO Auto-generated method stub
		_log.info(" Job 被触发了，此时Job 上的 execute() 方法将要被执行");
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		// TODO Auto-generated method stub
		_log.info("执行任务被否决了！");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		// TODO Auto-generated method stub
		_log.info("Job 的执行完成了,此方法被调用----"+context.getJobDetail().getKey());
	}

}
