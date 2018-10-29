package com.mukitech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.mukitech.*.mapper")
public class MarktingApplication 
{
	public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MarktingApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
