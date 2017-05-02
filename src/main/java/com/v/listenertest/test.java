package com.v.listenertest;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import com.v.paramtest.HelloJob2;

public class test {
	public static SimpleTrigger trigger1,trigger2 = null;

	public static void main(String[] args) {
		trigger1 = newTrigger().withIdentity("trigger1", "group1").startAt(new Date()).withSchedule(simpleSchedule()
				.withIntervalInSeconds(20).withRepeatCount(5).withMisfireHandlingInstructionNowWithExistingCount())
				.build();
		trigger2 = newTrigger().withIdentity("trigger2", "group2").startAt(new Date()).withSchedule(simpleSchedule()
				.withIntervalInSeconds(20).withRepeatCount(5).withMisfireHandlingInstructionNowWithExistingCount())
				.build();
		test1(trigger1,trigger2);
	}

	public static void test1(Trigger trigger1,Trigger trigger2) {
		try {
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = null;
			scheduler = schedulerFactory.getScheduler();
			JobDetail job1 = newJob(HelloJob2.class).withIdentity("job1", "group1").build();
			JobDetail job2 = newJob(HelloJob2.class).withIdentity("job2", "group2").build();
			TriggerListenerDemo lisntener = new TriggerListenerDemo();
			JobListenerDemo jobListener = new JobListenerDemo();
//			直接添加为全局监听器
//			scheduler.getListenerManager().addTriggerListener(lisntener);
			Matcher<TriggerKey> matcher1 = KeyMatcher.keyEquals(trigger1.getKey());//局部监听
			scheduler.getListenerManager().addTriggerListener(lisntener, matcher1);
			Matcher<JobKey> matcher2 = KeyMatcher.keyEquals(job1.getKey());//局部监听
			scheduler.getListenerManager().addJobListener(jobListener, matcher2);
			job1.getJobDataMap().put("ExecutionDelay", 1000L);
			job2.getJobDataMap().put("ExecutionDelay", 1000L);
			scheduler.scheduleJob(job1, trigger1);
			scheduler.scheduleJob(job2, trigger2);
			scheduler.start();
		} catch (Exception se) {
			se.printStackTrace();
		}
	}
}
