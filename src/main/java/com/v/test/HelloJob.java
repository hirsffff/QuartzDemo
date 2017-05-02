package com.v.test;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job{
	Logger _log = LoggerFactory.getLogger(HelloJob.class);
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		_log.info("Hello World! - " + context.getJobDetail().getKey()+"["+ new Date()+"]");
		System.err.println("  -" + context.getJobDetail().getKey() + ".");
		
	}

}
