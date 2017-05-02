package com.v.paramtest;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.v.test.HelloJob;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HelloJob2 implements Job {
	public static final String NUM_EXECUTIONS = "NumExecutions";
	public static final String EXECUTION_DELAY = "ExecutionDelay";

	public HelloJob2() {
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.err.println("---" + context.getJobDetail().getKey() + " executing.[" + new Date() + "]");

		JobDataMap map = context.getJobDetail().getJobDataMap();

		int executeCount = 0;
		if (map.containsKey(NUM_EXECUTIONS)) {
			executeCount = map.getInt(NUM_EXECUTIONS);
		}

		executeCount++;
		map.put(NUM_EXECUTIONS, executeCount);

		long delay = 5000l;
		if (map.containsKey(EXECUTION_DELAY)) {
			delay = map.getLong(EXECUTION_DELAY);
		}

		try {
			Thread.sleep(delay);
		} catch (Exception ignore) {
		}

		System.err.println("  -" + context.getJobDetail().getKey() + " complete (" + executeCount + ").");
	}
}
