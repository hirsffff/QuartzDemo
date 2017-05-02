package com.v.listenertest;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TriggerListenerDemo implements TriggerListener{
	Logger _log = LoggerFactory.getLogger(TriggerListenerDemo.class);
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MyTriggerListener";
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		_log.info(" Trigger 被触发了，此时Job 上的 execute() 方法将要被执行");
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		//返回false表示执行任务
		_log.info("拒绝执行任务！");
		return true;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub
		_log.info("当前触发错过了！");
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub
		_log.info("Trigger 被触发并且完成了 Job 的执行,此方法被调用----"+trigger.getKey());
	}

}
