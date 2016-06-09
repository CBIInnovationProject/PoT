package com.cybertrend.pot.service;

import java.text.ParseException;

import javax.servlet.http.HttpServlet;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

import com.cybertrend.pot.util.DBPropertyLooker;

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
		            
		         // define the com.cbi.vfree.job and tie it to our SendResponseToCatalist class
		            JobDetail job1 = new JobDetailImpl("job1","group1",null);

		            // Trigger the com.cbi.vfree.job to run now
		            Trigger trigger1 = new CronTriggerImpl("trigger1", "group1");
		            ((CronTriggerImpl) trigger1).setCronExpression(DBPropertyLooker.get("scheduler.time"));

		            // Tell quartz to schedule the com.cbi.vfree.job using our trigger
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