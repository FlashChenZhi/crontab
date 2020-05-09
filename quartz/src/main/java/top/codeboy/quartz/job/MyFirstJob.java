package top.codeboy.quartz.job;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author AlexChen
 * @version 1.0.1
 * Description: The First Alipay Demo of Java Project
 * @date 2019/12/29 19:56
 */
@Component
public class MyFirstJob {

    //配置时需要指定方法名
    public void sayHello(){
        System.out.println("First Job say Hello："+new Date());
    }
}
