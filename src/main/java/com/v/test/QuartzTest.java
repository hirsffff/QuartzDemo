package com.v.test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.util.Date;

import org.junit.Test;
import org.quartz.JobDetail;

import static org.quartz.SimpleScheduleBuilder.*;

public class QuartzTest implements Runnable {
	public static Trigger trigger = null;
	public static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	public static Scheduler scheduler = null;

	public static void main(String[] args) {

		try {

			scheduler = schedulerFactory.getScheduler();
			Date runTime = new Date();
			// Trigger the job to run now, and then repeat every 40 seconds
			trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

			// and start it off
			scheduler.start();
			

		} catch (Exception se) {
			se.printStackTrace();
		}
		QuartzTest quartzTest = new QuartzTest();
		Thread thread = new Thread(quartzTest);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
			try {
				scheduler.scheduleJob(job, trigger);

				Thread.sleep(10000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
