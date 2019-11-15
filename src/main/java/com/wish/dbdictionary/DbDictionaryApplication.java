package com.wish.dbdictionary;

import com.wish.dbdictionary.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.wish.dbdictionary")
@Slf4j
public class DbDictionaryApplication implements CommandLineRunner {

    @Autowired
    private ExportService exportService;

    public static void main(String[] args) {
        SpringApplication.run(DbDictionaryApplication.class, args);
    }

    public void run(String... strings){
        exportService.doExport();
    }
}
