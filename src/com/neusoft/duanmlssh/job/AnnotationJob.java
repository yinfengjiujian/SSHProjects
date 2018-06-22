package com.neusoft.duanmlssh.job;

import java.util.Date;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neusoft.duanmlssh.comment.DateUtils;

@Component
@Lazy(false)
public class AnnotationJob {

	@Scheduled(cron="* * 16 * * ?")
	public void executeJob(){
		System.out.println("AnnotationJob:定时任务调用》》》》》"+  DateUtils.dataFormate(new Date()) +"++++++++++++++=======================***&&&&&&&&&&&");
	}
	
}
