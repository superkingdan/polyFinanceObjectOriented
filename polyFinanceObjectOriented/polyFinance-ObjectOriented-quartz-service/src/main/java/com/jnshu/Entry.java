package com.jnshu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@MapperScan({"com.jnshu.dao","com.jnshu.dao2","com.jnshu.dao3","com.jnshu.service3"})
public class Entry {
    public static void main(String[] args) {
        SpringApplication.run(Entry.class,args);
    }
}
