package com.cybertrend.cpot.service;

import java.text.ParseException;

import javax.servlet.http.HttpServlet;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.cybertrend.cpot.job.JobSendEmail;

public class SchedullerService extends HttpServlet {
	public SchedullerService() {
		class RunScheduller implements Runnable {

			@Override
			public void run() {
				try {
		            // Grab the Scheduler instance from the Factory
		            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		            // and start it off
		            scheduler.start();
		            
		         // define the com.cybertrend.cpot.job and tie it to our SendResponseToCatalist class
		            JobDetail job1 = new JobDetailImpl("job1","group1",JobSendEmail.class);

		            // Trigger the 
		            Trigger trigger1 = new CronTriggerImpl("trigger1", "group1");
		            ((CronTriggerImpl) trigger1).setCronExpression("0 0 12 ? * TUE *");

		            // Tell quartz to schedule the com.cybertrend.cpot.job using our trigger
		            scheduler.scheduleJob(job1, trigger1);

		        } catch (SchedulerException se) {
		            se.printStackTrace();
		        } catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		Thread tr = new Thread(new RunScheduller());
		tr.start();
	}
}