package top.codeboy.quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author AlexChen
 * @version 1.0.1
 * Description: The First Alipay Demo of Java Project
 * @date 2019/12/29 19:57
 */
public class MySecondJob extends QuartzJobBean {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Second Job say Halo: "+name+new Date());
    }
}
