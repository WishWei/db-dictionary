package com.wish.dbdictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScans({@ComponentScan("com.wish.dbdictionary.controller"),
        @ComponentScan("com.wish.dbdictionary.service.impl")
})
@Slf4j
public class DbDictionaryApplication{

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(DbDictionaryApplication.class);

        application.run(args);

    }
}
