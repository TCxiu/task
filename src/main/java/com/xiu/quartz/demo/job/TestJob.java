package com.xiu.quartz.demo.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import java.util.Random;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018/6/20 13:31
 * @Description 类描述:
 */

public class TestJob extends QuartzJobBean {
    private Random random = new Random();

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        System.out.println(random.nextLong());
    }
}
