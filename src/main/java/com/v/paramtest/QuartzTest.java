package com.v.paramtest;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.junit.Test;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
public class QuartzTest {
	public static SimpleTrigger trigger = null;

	public static void main(String[] args) {
		trigger = newTrigger()
	            .withIdentity("trigger2", "group1")
	            .startAt(new Date())
	            .withSchedule(simpleSchedule()
	                    .withIntervalInSeconds(2).withRepeatCount(5)
	                    .withMisfireHandlingInstructionNowWithExistingCount()) // set misfire instruction
	            .build();
		test1(trigger);
	}

	public static void test1(Trigger trigger) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = null;
			scheduler = schedulerFactory.getScheduler();
			JobDetail job = newJob(HelloJob2.class).withIdentity("job1", "group1").build();
			job.getJobDataMap().put("ExecutionDelay", 1000L);
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
