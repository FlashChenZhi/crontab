package top.codeboy.quartz.config;

import org.quartz.JobDataMap;
import org.quartz.SchedulerConfigException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.*;
import top.codeboy.quartz.job.MySecondJob;

import java.util.Date;

/**
 * @author AlexChen
 * @version 1.0.1
 * Description: 1.配置JobDetail（制定定时任务） ——> 2.配置触发器(何时执行任务)
 * @date 2019/12/29 20:02
 */
@Configuration
public class QuartzConfig {
    /**
     * 步骤1：配置JobDetail定时任务(方式一：暂不支持传参)
     * @return
     */
    @Bean
    MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(){
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("myFirstJob");
        bean.setTargetMethod("sayHello");
        return bean;
    }

    /**
     * 步骤一：配置JobDetail定时任务(方式二)
     * @return
     */
    @Bean
    JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(MySecondJob.class);
        JobDataMap map = new JobDataMap();
        bean.setJobDataMap(map);
        return bean;
    }

    /**
     * 步骤2：设定执行定时任务(方式一：简单配置触发器)
     * @return
     */
    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean(){
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(methodInvokingJobDetailFactoryBean().getObject());
        //设置任务开始执行时间
        bean.setStartTime(new Date());
        //设置重复时间间隔
        bean.setRepeatInterval(2000);
        //设置重复次数(默认无限循环)
        bean.setRepeatCount(3);
        return bean;
    }

    /**
     * 步骤二：设定执行定时任务(方式二：Cron表达式配置触发器)
     * @return
     */
    @Bean
    CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        //设置Cron表达式(秒* 分* 时* 日* 月* 周？)
        bean.setCronExpression("* * * * * ?");
        return bean;
    }

    /**
     * 步骤三：启动定时任务触发器(简单配置触发器 + Cron表达式配置触发器)
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //配置触发器
        bean.setTriggers(simpleTriggerFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
        return bean;
    }

}
