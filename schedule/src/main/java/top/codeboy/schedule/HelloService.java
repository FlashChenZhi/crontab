package top.codeboy.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AlexChen
 * @version 1.0.1
 * Description: The First Alipay Demo of Java Project
 * @date 2019/12/29 19:36
 */
@Service
public class HelloService {

    /**
     * 前面任务的结束时间与后面任务的开始时间间隔2秒(前面任务结束以后)
     */
//    @Scheduled(fixedDelay = 2000)
//    public void fixedDelay(){
//        System.out.println("fixedDelay>>>"+new Date());
//    }

    /**
     * 两次定时任务开始的间隔时间为2秒(无论前后任务是否执行完毕)
     */
    @Scheduled(fixedRate = 2000)
    public void fixedRate(){
//        System.out.println("fixedRate>>>"+new Date());
    }

    /**
     * Cron表达式(*分别表示任意分钟 小时 日期 月份 ; ？周)
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void cron(){
        System.out.println("cron>>>"+new Date());
    }
}
