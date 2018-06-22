package com.neusoft.duanmlssh.job;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.neusoft.duanmlssh.comment.DateUtils;

public class SendMessageJob extends QuartzJobBean{

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		System.out.println("SendMessageJob:定时任务调用》》》》》"+  DateUtils.dataFormate(new Date()) +"++++++++++++++=======================***&&&&&&&&&&&");
	}

}
