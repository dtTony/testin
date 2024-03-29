package com.test.testin;

import java.util.Properties;
 
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
 
import com.github.pagehelper.PageHelper;
 
@SpringBootApplication
@MapperScan("com.test.testin.mapper") // 告诉Mapper所在的包名
public class Springdemo1Application implements EmbeddedServletContainerCustomizer {
 
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		configurableEmbeddedServletContainer.setPort(8080);// 修改端口号
	}
 
	public static void main(String[] args) {
		SpringApplication.run(Springdemo1Application.class, args);
	}
 
	@Bean
	public PageHelper pageHelper() {// 配置myBatis的分页插件pageHelper
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}
 
}
