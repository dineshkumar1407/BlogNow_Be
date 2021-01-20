package com.dinesh.project.blognow;

import com.dinesh.project.blognow.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
//starts here
public class BlogNowApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogNowApplication.class, args);
    }

}
