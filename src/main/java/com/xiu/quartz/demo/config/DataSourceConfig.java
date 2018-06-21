package com.xiu.quartz.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

/**
 * @Auther 创建者: Tc李
 * @Date 创建时间: 2018-05-16  16:02:28
 * @Description 类描述: 数据源配置
 */

@Configuration
public class DataSourceConfig {

	@Bean
    public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl("jdbc:mysql://192.168.0.189:3306/dyd_task_v1.0?useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("Dyd_2018");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//	        <!-- 初始化连接大小 -->
		dataSource.setInitialSize(10);
//	        <!-- 连接池最大并发使用连接数量 -->
		dataSource.setMaxActive(5000);
//	        <!-- 连接池最小空闲 -->
		dataSource.setMinIdle(1);
//	        <!-- 获取连接最大等待时间 -->
		dataSource.setMaxWait(60000);
//	        <!-- 打开pscache功能  在mysql5.5以上版本支持 -->
		dataSource.setPoolPreparedStatements(true);
//	        <!-- 指定每个连接上的pscache的大小 -->
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(66);
		dataSource.setValidationQuery("select 1");
//	        <!-- 归还连接时执行validationQuery  ，检测是否有效，设置为true这样会降低性能 -->
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
//	        <!-- 申请链接的时间是否检测 -->
		dataSource.setTestWhileIdle(true);
//	        <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
//	        <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
		dataSource.setMinEvictableIdleTimeMillis(25200000);
//	        <!-- 打开超过时间限制是否回收功能 -->
		dataSource.removeAbandoned();
//	        <!-- 超过多长时间 1800秒，也就是30分钟 -->
		dataSource.setRemoveAbandonedTimeout(1800);
		return dataSource;
    }
}
