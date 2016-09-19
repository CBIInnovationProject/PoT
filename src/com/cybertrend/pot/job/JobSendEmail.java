package com.cybertrend.pot.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobSendEmail implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String emailAddress = dataMap.getString("emailAddress");
		String contentUrl = dataMap.getString("contentUrl");
		String menuId = dataMap.getString("menuId");
		System.out.println(emailAddress+contentUrl+menuId);
	}

}
