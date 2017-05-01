package com.v.test;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.junit.Test;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class QuartzTest2 {
	public static CronTrigger trigger = null;

	public static void main(String[] args) {

		/*
		 * trigger = newTrigger().withIdentity("trigger1", "group1")
		 * .withSchedule(cronSchedule("0/5 * * * * ?")).build(); //5秒运行一次任务
		 */
		/*
		 * trigger = newTrigger().withIdentity("trigger2",
		 * "group1").withSchedule(cronSchedule("10 0/1 * * * ?")).build();//
		 * Log---- 13:31:10,096 INFO - Hello World! - Mon May 01 13:31:10 CST
		 * 2017 在秒针为10秒的时候开始运行，1分钟运行一次
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger3", "group1")
		 * .withSchedule(cronSchedule("0 0/1 8-17 * * ?")) .build();
		 * 在8-17点运行，0秒开始每分钟运行一次
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger5", "group1")
		 * .withSchedule(cronSchedule("40 4 14 1,15 * ?")) .build();
		 * 每月1、15号14点4分40秒运行
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger5", "group1")
		 * .withSchedule(cronSchedule("0 8/1 14 1,15 * ?")) .build();
		 * 每月1、15号14点8分0秒开始运行，每分钟运行一次
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger6", "group1")
		 * .withSchedule(cronSchedule("0,30 * * ? * MON-FRI")) .build();
		 * 每周一至周五任何时间0秒开始30秒运行一次
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger7", "group1")
		 * .withSchedule(cronSchedule("0,30 * * ? * MON,SUN")) .build();
		 * 每周一、周日任何时间0秒开始30秒运行一次
		 */
		/*
		 * trigger = newTrigger() .withIdentity("trigger6", "group1")
		 * .withSchedule(cronSchedule("0,30 * * ? 5 MON-FRI")) .build();
		 * 5月每周一、周日任何时间0秒开始30秒运行一次
		 */
		/*  
		 * 参数说明：
		 * 	Seconds    0-59    , - * / 
		 *	Minutes    0-59    , - * / 
		 *	Hours    0-23    , - * / 
		 *	Day-of-month    1-31    , - * ? / L W 
		 *	Month    1-12 or JAN-DEC    , - * / 
		 *	Day-of-Week    1-7 or SUN-SAT    , - * ? / L # 
		 *	Year (Optional)    empty, 1970-2199    , - * / 
		 *	
		 */
		test1(trigger);
	}

	public static void test1(CronTrigger trigger) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = null;
			scheduler = schedulerFactory.getScheduler();
			JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
			scheduler.scheduleJob(job, trigger);
			scheduler.start();
		} catch (Exception se) {
			se.printStackTrace();
		}
	}

}
